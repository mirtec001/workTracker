import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


public class StartStopPanel extends JPanel implements ActionListener {

    private Date start = new Date();
    private final JTextField customerName;
    private final JTextArea notes;


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

        customerName = new JTextField(25);
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
        if (e.getActionCommand().equals("Start")) {
            start = new Date();
            System.out.println(start);
        }
        if (e.getActionCommand().equals("Stop")) {
            Date stop = new Date();
            System.out.println("Start: " + start + " Stop: " + stop);
            System.out.println("Time spent: " + (stop.getTime() - start.getTime()));
            System.out.println("Customer Name: " + customerName.getText());
            System.out.println("Notes: " + notes.getText());

        }
    }
}
