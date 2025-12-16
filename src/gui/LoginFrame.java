package gui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtUserId;
    private JComboBox<String> cmbRole;
    private JButton btnLogin;

    public LoginFrame() {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 180);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        txtUserId = new JTextField();
        cmbRole = new JComboBox<>(new String[] { "Patient", "Clinician", "AdminStaff" });
        btnLogin = new JButton("Login");

        panel.add(new JLabel("User ID:"));
        panel.add(txtUserId);
        panel.add(new JLabel("Role:"));
        panel.add(cmbRole);

        JPanel buttons = new JPanel();
        buttons.add(btnLogin);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.SOUTH);
    }

    public JTextField getTxtUserId() { return txtUserId; }
    public JComboBox<String> getCmbRole() { return cmbRole; }
    public JButton getBtnLogin() { return btnLogin; }
}
