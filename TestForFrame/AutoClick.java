package AutoClick.TestForFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AutoClick extends JFrame implements ActionListener {

    //Vẽ frame
    //https://stackoverflow.com/questions/14274480/static-final-long-serialversionuid-1l
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtN;
    private JTextField txtInterval;
    private JProgressBar progressBar;
    private JButton btnStart;
    private JLabel lblByTabs;

    public static void main(String[] args) {

        //https://phocode.com/java/javaswing/java-swing-vi-du-mo-dau/
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AutoClick frame = new AutoClick();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AutoClick() {
        setUndecorated(true);
        setTitle("Auto Click Demo Vũ Thắng");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 294, 139);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Settings Demo by Vũ Thắng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 11, 168, 86);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNumberOfClicks = new JLabel("Number Of Click");
        lblNumberOfClicks.setBounds(21, 21, 94, 20);
        panel.add(lblNumberOfClicks);

        JLabel lblInterval = new JLabel("Interval"); // Giãn cách time click
        lblInterval.setBounds(21, 52, 94, 19);
        panel.add(lblInterval);

        txtN = new JTextField();
        txtN.setText("70");
        txtN.setBounds(119, 21, 33, 20);
        panel.add(txtN);
        txtN.setColumns(10);

        txtInterval = new JTextField();
        txtInterval.setText("100");
        txtInterval.setBounds(119, 51, 33, 20);
        panel.add(txtInterval);

        btnStart = new JButton("Start");
        btnStart.addActionListener(this);
        btnStart.setActionCommand("Start");
        btnStart.setBounds(188, 16, 89, 23);
        contentPane.add(btnStart);

        progressBar = new JProgressBar();
        progressBar.setString("Please wait...");
        progressBar.setStringPainted(true);
        progressBar.setBounds(187, 77, 90, 19);
        setProgressState(true);
        contentPane.add(progressBar);

        lblByTabs = new JLabel("-Tabs5894"); //Kiểu text
        lblByTabs.setForeground(Color.GRAY);
        lblByTabs.setFont(new Font("Tahoma", Font.PLAIN, 8));
        lblByTabs.setHorizontalAlignment(SwingConstants.TRAILING);
        lblByTabs.setBounds(188, 83, 89, 14);
        contentPane.add(lblByTabs);
    }

    void setProgressState(final boolean done) {
        SwingUtilities.invokeLater(new Runnable() {
            // Ẩn frame 2 khi start
            //Trục tọa độ ẩn = done
            @Override
            public void run() {
                progressBar.setVisible(!done);
                progressBar.setIndeterminate(!done);

                btnStart.setEnabled(done);
                txtInterval.setEditable(done);
                txtN.setEditable(done);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Start")) {
            try {
                Start click = new Start(AutoClick.this,
                        Integer.parseInt("0" + txtN.getText().trim()),
                        Integer.parseInt("0" + txtInterval.getText().trim()));

                JOptionPane.showMessageDialog(null, "Auto Click will start in 3sec.", "Warning", JOptionPane.WARNING_MESSAGE);
                ExecutorService executor = Executors.newCachedThreadPool();
                executor.execute(click);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid Input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Auto Click!");
        }
    }
}
