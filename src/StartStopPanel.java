import javax.swing.*;
import java.awt.*;


public class StartStopPanel extends JPanel{
    public StartStopPanel() {
        super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel header = new JLabel("Job Start / Stop");
        Font headerFont = new Font("Arial", Font.PLAIN, 32);

        header.setFont(headerFont);
        add(header);

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
}
