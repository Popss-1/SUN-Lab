import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends JFrame {

    private JPanel contentPane;
    private JTextField idInputText;

    private UserDao userDao = new UserDao();
    private LogDao logDao = new LogDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomeView frame = new HomeView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public HomeView() {
        setTitle("Home");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 445, 300);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("ID：");
        lblNewLabel.setBounds(115, 125, 45, 15);
        contentPane.add(lblNewLabel);

        idInputText = new JTextField();
        idInputText.setBounds(150, 120, 160, 20);
        contentPane.add(idInputText);
        idInputText.setColumns(10);

        //open the door, The student ID number and the timestamp will be sent to the system and saved to a database.
        JButton openBtn = new JButton("Open");
        openBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idInput = idInputText.getText();
                if (idInput == null || "".equals(idInput)) {
                    JOptionPane.showMessageDialog(contentPane, "Please enter id", "Tip", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                User user = userDao.getById(Integer.parseInt(idInput));

                if (user == null) {
                    JOptionPane.showMessageDialog(contentPane, "no access", "Tip", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(contentPane, "open success", "Tip", JOptionPane.INFORMATION_MESSAGE);
                    boolean success = logDao.saveLog(new AccessLog(user.getId(), user.getName()));
                    if (success) {
                        System.out.println("save success: " + user.getId() + "  " + user.getName());
                    } else {
                        System.out.println("save fail: " + user.getId() + "  " + user.getName());
                    }
                }
            }
        });

        openBtn.setBounds(320, 120, 75, 25);
        contentPane.add(openBtn);

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginView frame = new LoginView();
                frame.setVisible(true);
                HomeView.this.dispose();
            }
        });
        loginBtn.setBounds(320, 200, 75, 25);
        contentPane.add(loginBtn);
    }

}
