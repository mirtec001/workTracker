import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Main implements ActionListener {

    private final JFrame frame = new JFrame("Pete's Work Tracker");

    public Main() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {

        DailyLogPanel dailyLogPanel = new DailyLogPanel();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        JMenuItem summaryPanel = new JMenuItem("Panel 1");
        JMenuItem daily = new JMenuItem("Daily Log");
        JMenuItem startStopPanel = new JMenuItem("Start Stop");

        summaryPanel.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        summaryPanel.getAccessibleContext().setAccessibleDescription(
                "Switch to summary panel");

        daily.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        daily.getAccessibleContext().setAccessibleDescription(
                "Switch to daily panel");

        startStopPanel.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_3, ActionEvent.ALT_MASK));
        startStopPanel.getAccessibleContext().setAccessibleDescription(
                "Switch to start stop pane");

        summaryPanel.addActionListener(this);
        daily.addActionListener(this);
        startStopPanel.addActionListener(this);

        fileMenu.add(summaryPanel);
        fileMenu.add(daily);
        fileMenu.add(startStopPanel);


        frame.add(dailyLogPanel);
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

        if (e.getActionCommand().equals("Panel_1")) {
            frame.getContentPane().removeAll();
            frame.setContentPane(new DailyLogPanel());
            frame.revalidate();
        } else if (e.getActionCommand().equals("Start Stop")) {
            frame.getContentPane().removeAll();
            frame.setContentPane(new StartStopPanel());
            frame.revalidate();
        } else if (e.getActionCommand().equals("Daily Log")) {
            frame.getContentPane().removeAll();
            frame.setContentPane(new DailyStatus());
            frame.revalidate();
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new Main());
    }
}