import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;

public class Register extends JFrame {

    // Global variables
    private JTextField nameField;
    private JDateChooser dateChooser;
    private JRadioButton male, female;
    private JTextField numberField, emailField, irrigationField, passwordField, confirmPasswordField;

    public Register() {
        setLayout(null);
        setTitle("Farmer Management");
        setSize(500, 570);
        setLocation(450, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Set image in scale parameters
        ImageIcon icon = new ImageIcon(getClass().getResource("images/wheat.png"));
        Image icon2 = icon.getImage().getScaledInstance(70, 500, Image.SCALE_DEFAULT);
        ImageIcon icon3 = new ImageIcon(icon2);
        JLabel imageLabel = new JLabel(icon3);
        imageLabel.setBounds(0, 10, 70, 500);
        add(imageLabel);

        JLabel heading = new JLabel("Registration Form");
        heading.setBounds(100, 20, 400, 34);
        heading.setFont(new Font("Railway", Font.BOLD, 32));
        add(heading);

        // Name field
        JLabel name = new JLabel("Name : ");
        name.setBounds(70, 100, 100, 18);
        name.setFont(new Font("Railway", Font.BOLD, 16));
        add(name);
        nameField = new JTextField();
        nameField.setBounds(230, 100, 180, 20);
        add(nameField);

        // Date of Birth field
        JLabel birthdate = new JLabel("Date of Birth  : ");
        birthdate.setBounds(70, 140, 150, 18);
        birthdate.setFont(new Font("Railway", Font.BOLD, 16));
        add(birthdate);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(230, 140, 180, 20);
        add(dateChooser);

        // Gender field
        JLabel gender = new JLabel("Gender  : ");
        gender.setBounds(70, 180, 150, 18);
        gender.setFont(new Font("Railway", Font.BOLD, 16));
        add(gender);
        male = new JRadioButton("Male");
        male.setBounds(230, 180, 80, 18);
        add(male);
        female = new JRadioButton("Female");
        female.setBounds(320, 180, 80, 18);
        add(female);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        // Mobile Number field
        JLabel number = new JLabel("Mobile Number  : ");
        number.setBounds(70, 220, 150, 18);
        number.setFont(new Font("Railway", Font.BOLD, 16));
        add(number);
        numberField = new JTextField();
        numberField.setBounds(230, 220, 180, 20);
        add(numberField);

        // Email field
        JLabel email = new JLabel("Email Address  : ");
        email.setBounds(70, 260, 150, 18);
        email.setFont(new Font("Railway", Font.BOLD, 16));
        add(email);
        emailField = new JTextField();
        emailField.setBounds(230, 260, 180, 20);
        add(emailField);

        // Irrigation Facilities field
        JLabel irrigation = new JLabel("Irrigation Facilities  : ");
        irrigation.setBounds(70, 300, 200, 18);
        irrigation.setFont(new Font("Railway", Font.BOLD, 16));
        add(irrigation);
        irrigationField = new JTextField();
        irrigationField.setBounds(230, 300, 180, 20);
        add(irrigationField);

        // Password field
        JLabel password = new JLabel("Password  : ");
        password.setBounds(70, 340, 100, 18);
        password.setFont(new Font("Railway", Font.BOLD, 16));
        add(password);
        passwordField = new JTextField();
        passwordField.setBounds(230, 340, 180, 20);
        add(passwordField);


        // Confirm Password field
        JLabel confirmPassword = new JLabel("Confirm Password  : ");
        confirmPassword.setBounds(70, 380, 200, 18);
        confirmPassword.setFont(new Font("Railway", Font.BOLD, 16));
        add(confirmPassword);
        confirmPasswordField = new JTextField();
        confirmPasswordField.setBounds(230, 380, 180, 20);
        add(confirmPasswordField);

        // Register button
        JButton register = new JButton("Register");
        register.setBounds(110, 430, 250, 25);
        register.setForeground(new Color(255, 255, 255));
        register.setBackground(new Color(0, 128, 0));
        add(register);

        // Text for login prompt
        JLabel text1 = new JLabel("If you are already registered, please");
        text1.setBounds(70, 475, 400, 18);
        text1.setFont(new Font("Arials", Font.PLAIN, 14));
        add(text1);

        JLabel text2 = new JLabel("Login Here");
        text2.setBounds(305, 473, 150, 20);
        text2.setFont(new Font("Arials", Font.BOLD, 17));
        text2.setForeground(new Color(0, 40, 255));
        add(text2);

        text2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Redirect to Login.java
                new Login(); // Assuming you have a Login class
                dispose(); // Close current Register window
            }
        });

        // Register button action
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    storeInDatabase();
                }
            }
        });

        setVisible(true);
    }

    // Method to validate input fields
    private boolean validateFields() {
        if (nameField.getText().isEmpty() || numberField.getText().isEmpty() ||
                emailField.getText().isEmpty() || irrigationField.getText().isEmpty() ||
                passwordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty() ||
                dateChooser.getDate() == null || (!male.isSelected() && !female.isSelected())) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Method to store data in the database
    private void storeInDatabase() {
        String name = nameField.getText();
        // Convert the date to the correct format (yyyy-MM-dd)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dob = sdf.format(dateChooser.getDate());
        String gender = male.isSelected() ? "Male" : "Female";
        String mobile = numberField.getText();
        String email = emailField.getText();
        String irrigation = irrigationField.getText();
        String password = passwordField.getText();

        // Database connection
        Connection conn = Con.getConnection();
        //try (conn) {
            String sql = "INSERT INTO users (name, dob, gender, mobile, email, irrigation, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try //(PreparedStatement pstmt = conn.prepareStatement(sql))

            {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setString(2, dob);
                pstmt.setString(3, gender);
                pstmt.setString(4, mobile);
                pstmt.setString(5, email);
                pstmt.setString(6, irrigation);
                pstmt.setString(7, password);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close registration window
                new Login(); // Open login window after successful registration
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
//        } catch (SQLException e) {
//            //JOptionPane.showMessageDialog(this, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        new Register();
    }

}
