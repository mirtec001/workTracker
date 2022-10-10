import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class StartStopPanel extends JPanel implements ActionListener {

    private Date start = new Date();
    private final JTextField customerName;
    private final JTextArea notes;
    private final JTextArea history;
    private String historyBlocks;
    private DiskOperations diskOperations = new DiskOperations();

    public StartStopPanel() {
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Box headerBox = new Box(BoxLayout.X_AXIS);
        JLabel header = new JLabel("Job Start / Stop");
        Font headerFont = new Font("Arial", Font.PLAIN, 32);
        header.setFont(headerFont);
        headerBox.setBorder(new EmptyBorder(20, 20, 20, 20));
        headerBox.add(header);
        add(headerBox);

        Box customerBox = new Box(BoxLayout.X_AXIS);
        JLabel customerNameLabel = new JLabel("Customer Name", JLabel.TRAILING);
        customerBox.add(customerNameLabel);

        customerName = new JTextField(100);

        customerNameLabel.setLabelFor(customerName);

        customerBox.add(customerName);
        add(customerBox);

        Box buttonBox = new Box(BoxLayout.X_AXIS);
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        buttonBox.add(startButton);
        buttonBox.add(stopButton);

        startButton.addActionListener(this);
        stopButton.addActionListener(this);

        add(buttonBox);
        notes = new JTextArea(10, 100);
        notes.setLineWrap(true);

        JScrollPane jspNotes = new JScrollPane(notes);
        jspNotes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(jspNotes);

        JLabel historyLabel = new JLabel("History", JLabel.TRAILING);
        add(historyLabel);



        history = new JTextArea(20, 100);
        history.setLineWrap(false);
        history.setEnabled(false);

        JScrollPane jspHistory = new JScrollPane(history);
        jspHistory.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        historyLabel.setLabelFor(history);
        setupHistoryBlock();
        add(jspHistory);
    }

    private void setupHistoryBlock() {
        diskOperations.createFileIfNotExists("history.txt");
        historyBlocks = diskOperations.readText("history.txt");
        history.setText(historyBlocks);
    }

    public void actionPerformed(ActionEvent e) {

        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("Start")) {
            start = new Date();
            System.out.println(start);
        }
        if (e.getActionCommand().equals("Stop")) {
            Date stop = new Date();
            diskOperations.writeToDisk("history.txt", "Start: " + start + " Stop: " + stop, diskOperations.readText("history.txt"));
            diskOperations.writeToDisk("history.txt", "Time spent: " + formatTime(start, stop), diskOperations.readText("history.txt"));
            diskOperations.writeToDisk("history.txt", "Customer Name: " + customerName.getText(), diskOperations.readText("history.txt"));
            diskOperations.writeToDisk("history.txt", "Notes: " + notes.getText(), diskOperations.readText("history.txt"));
//            System.out.println("Start: " + start + " Stop: " + stop);
//            System.out.println("Time spent: " + (stop.getTime() - start.getTime()));
//            System.out.println("Customer Name: " + customerName.getText());
//            System.out.println("Notes: " + notes.getText());
            String text = diskOperations.readText("history.txt");
            history.setText(text);
        }
    }

    private String formatTime(Date start, Date stop) {
        long diff = stop.getTime() - start.getTime();
        long seconds = TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS);

        long mins = Math.floorDiv(seconds, 60);
        long secs = seconds - (mins * 60);

        return mins + ":" + secs;
    }
}
