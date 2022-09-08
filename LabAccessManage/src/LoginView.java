import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginView extends JFrame {

    private JPanel contentPane;
    private JTextField nameText;
    private JTextField pwdText;

    private UserDao userDao = new UserDao();

    /**
     * Create the frame.
     */
    public LoginView() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 445, 300);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel labName = new JLabel("Name：");
        labName.setBounds(90, 50, 80, 15);
        contentPane.add(labName);

        nameText = new JTextField();
        nameText.setBounds(150, 45, 160, 20);
        contentPane.add(nameText);
        nameText.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Password：");
        lblNewLabel_1.setBounds(90, 95, 80, 15);
        contentPane.add(lblNewLabel_1);

        pwdText = new JTextField();
        pwdText.setBounds(150, 90, 160, 20);
        contentPane.add(pwdText);
        pwdText.setColumns(10);

        //Login
        JButton saveBtn = new JButton("Login");
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameText.getText();
                String pwd = pwdText.getText();
                if (name == null || "".equals(name)) {
                    JOptionPane.showMessageDialog(contentPane, "Please enter name", "Tip", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (pwd == null || "".equals(pwd)) {
                    JOptionPane.showMessageDialog(contentPane, "Please enter password", "Tip", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                User user = userDao.getUserByNameAndPwd(name, pwd);
                if (user == null) {
                    JOptionPane.showMessageDialog(contentPane, "User does not exist", "Tip", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int type = user.getType();
                if (type == 0) {
                    //show admin page
                    LogHistoryPage frame = new LogHistoryPage();
                    frame.setVisible(true);
                } else if (type == 1) {
                    //show teacher page
                    LogHistoryPage frame = new LogHistoryPage();
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Login fail.", "Tip", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });
        saveBtn.setBounds(150, 140, 75, 25);
        contentPane.add(saveBtn);

        //Cancel
        JButton cancleBtn = new JButton("Cancel");
        cancleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeView frame = new HomeView();
                frame.setVisible(true);
                dispose();
            }
        });
        cancleBtn.setBounds(240, 140, 75, 25);
        contentPane.add(cancleBtn);
    }

}
