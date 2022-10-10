import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class StartStopPanel extends JPanel implements ActionListener {

    private Date start;
    private Date stop;

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

        JTextField customerName = new JTextField(25);
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

        JTextArea notes = new JTextArea(10, 100);
        notes.setLineWrap(true);
        add(notes);

        JLabel historyLabel = new JLabel("History", JLabel.TRAILING);
        add(historyLabel);

        JTextArea history = new JTextArea(20, 100);
        history.setLineWrap(false);
        history.setEnabled(false);

        historyLabel.setLabelFor(history);

        add(history);

    }

    public void actionPerformed(ActionEvent e) {

        System.out.println(e.getActionCommand());
        if (e.getActionCommand() == "Start") {
            System.out.println("Yeah, do it again.");
        }
        if (e.getActionCommand() == "Stop") {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss");
            System.out.println(dtf.format(now));
        }
    }
}
