import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class DailyStatus extends JPanel implements ActionListener, KeyListener {

    private String DAILYLOG = "daily.log";

    private DiskOperations diskOps = new DiskOperations();
    private JTextArea output = new JTextArea();
    private JTextField entry = new JTextField();
    private JButton submit = new JButton("Submit");
    private String text = "";

    private Logger logger = new Logger();

    public DailyStatus() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        entry.addKeyListener(this);
        submit.addActionListener(this);

        text = diskOps.readText(DAILYLOG);

        output.setColumns(200);
        output.setRows(50);
        output.setText(text);

        JPanel entryPanel = new JPanel();
        entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.X_AXIS));
        entryPanel.setPreferredSize(new Dimension(this.getWidth(), 50));
        entryPanel.add(entry);
        entryPanel.add(submit);

        this.add(entryPanel);
        this.add(output);
    }


    public void actionPerformed(ActionEvent e) {
        diskOps.writeToDisk(DAILYLOG, entry.getText(), text);
        text = diskOps.readText(DAILYLOG);
        output.setText(text);
        entry.setText("");
        logger.writeToLog("actionPerformed completed");
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER) {
            diskOps.writeToDisk(DAILYLOG, entry.getText(), text);
            text = diskOps.readText(DAILYLOG);
            output.setText(text);
            entry.setText("");
            logger.writeToLog("keyTyped");
        }
    }
}
