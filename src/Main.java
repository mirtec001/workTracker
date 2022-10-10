import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class Main implements ActionListener {

    private JFrame frame = new JFrame("Pete's Work Tracker");

    public Main() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {

        DailyLogPanel dailyLogPanel = new DailyLogPanel();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("_File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        JMenuItem filePanel1 = new JMenuItem("Panel_1");
        JMenuItem filePanel2 = new JMenuItem("Panel_2");
        filePanel1.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        filePanel1.getAccessibleContext().setAccessibleDescription(
                "Switch to panel 1");

        filePanel2.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        filePanel2.getAccessibleContext().setAccessibleDescription(
                "Switch to panel 1");

        filePanel1.addActionListener(this);
        filePanel2.addActionListener(this);

        fileMenu.add(filePanel1);
        fileMenu.add(filePanel2);


        frame.add(dailyLogPanel);
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Panel_1"){
            frame.getContentPane().removeAll();
            frame.setContentPane(new DailyLogPanel());
            frame.revalidate();
        } else if (e.getActionCommand() == "Panel_2") {
            frame.getContentPane().removeAll();
            frame.setContentPane(new StartStopPanel());
            frame.revalidate();
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}