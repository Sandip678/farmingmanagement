import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {

    JPasswordField passwordfield;

    public Login() {
        setLayout(null);
        setTitle("Farmer Management");
        setSize(800, 400);
        setLocation(350, 200);

        // Set image in scale parameters
        ImageIcon icon = new ImageIcon(getClass().getResource("images/farmer.png"));
        Image icon2 = icon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon icon3 = new ImageIcon(icon2);
        JLabel imageLabel = new JLabel(icon3);
        imageLabel.setBounds(50, 10, 300, 300);
        add(imageLabel);

        JLabel heading = new JLabel("Farmer Management");
        heading.setBounds(325, 15, 400, 45);
        heading.setFont(new Font("Railway", Font.BOLD, 38));
        add(heading);

        JLabel login = new JLabel("(Login Page)");
        login.setBounds(425, 80, 300, 35);
        login.setFont(new Font("Railway", Font.PLAIN, 30));
        add(login);

        JLabel name = new JLabel("Name : ");
        name.setBounds(330, 140, 150, 25);
        name.setFont(new Font("Railway", Font.PLAIN, 20));
        add(name);

        JTextField namefield = new JTextField();
        namefield.setBounds(450, 140, 250, 25);
        add(namefield);

        JLabel password = new JLabel("Password : ");
        password.setBounds(330, 190, 150, 25);
        password.setFont(new Font("Railway", Font.PLAIN, 20));
        add(password);

        passwordfield = new JPasswordField();
        passwordfield.setBounds(450, 190, 250, 25);
        add(passwordfield);

        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(451, 216, 150, 15);
        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passwordfield.setEchoChar((char) 0);
                } else {
                    passwordfield.setEchoChar('*');
                }
            }
        });
        add(showPassword);

        JButton loginbtn = new JButton("Login");
        loginbtn.setBounds(450, 245, 250, 30);
        loginbtn.setBackground(new Color(0, 128, 0));
        loginbtn.setForeground(new Color(255, 255, 255));
        add(loginbtn);

        JLabel text1 = new JLabel(" Don't have an account? Please ");
        text1.setBounds(330, 295, 250, 20);
        text1.setFont(new Font("Railway", Font.PLAIN, 16));
        add(text1);

        JLabel text2 = new JLabel(" Register here ");
        text2.setBounds(550, 295, 150, 22);
        text2.setFont(new Font("Railway", Font.PLAIN, 18));
        text2.setForeground(new Color(0, 62, 250, 255));
        text2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Register();
                dispose(); // Close current Register window
            }
        });
        add(text2);

        // Login button action
        loginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = namefield.getText();
                String password = new String(passwordfield.getPassword());
                authenticateUser(username, password);
            }
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Method to authenticate user
    private void authenticateUser(String username, String password) {
        // Database connection
        Connection conn = Con.getConnection();
        if (conn != null) {
            String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Here you can redirect to the main application window after successful login
                    dispose(); // Close login window
                    new Dashboard(username); // Open Dashboard window after successful registration
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
               // JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Unable to connect to the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
