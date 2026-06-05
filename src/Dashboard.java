import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {

    String userName;
    JTextField acres;  // Corrected variable name to match its use

    JComboBox<String> cropDropdown;
    JButton logoutButton, submitButton;

    public Dashboard(String userName) {
        this.userName = userName; // Assign userName
        setLayout(null);
        setTitle("Farmer Management");
        setSize(850, 400);
        setLocation(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set image in scale parameters
        ImageIcon icon = new ImageIcon(getClass().getResource("images/farmer2.png"));
        Image icon2 = icon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon icon3 = new ImageIcon(icon2);
        JLabel imageLabel = new JLabel(icon3);
        imageLabel.setBounds(0, 50, 300, 300);
        add(imageLabel);

        JLabel heading = new JLabel(userName+", Welcome To Farmer Management");
        heading.setBounds(280, 50, 520, 30);
        heading.setFont(new Font("Railway", Font.BOLD, 24));
        add(heading);

        JLabel line = new JLabel("Dear farmers, welcome to our Farmer Management System.");
        line.setBounds(330, 95, 600, 18);
        line.setFont(new Font("Railway", Font.PLAIN, 14));
        add(line);

        JLabel line1 = new JLabel("Here, you can get all details from sowing to harvesting.");
        line1.setBounds(345, 115, 500, 18);
        line1.setFont(new Font("Railway", Font.PLAIN, 14));
        add(line1);

        JLabel line2 = new JLabel("Just enter the crop name and the acreage to get started.");
        line2.setBounds(335, 135, 500, 18);
        line2.setFont(new Font("Railway", Font.PLAIN, 14));
        add(line2);

        JLabel cropLabel = new JLabel("Select Crop:");
        cropLabel.setBounds(250, 200, 150, 22);
        cropLabel.setFont(new Font("Railway", Font.PLAIN, 20));
        add(cropLabel);

        // Crop Dropdown
        String[] crops = {"", "Wheat", "Rice", "Corn", "Soybean", "Sugarcane"};
        cropDropdown = new JComboBox<>(crops);
        cropDropdown.setBounds(370, 200, 150, 25);
        add(cropDropdown);

        JLabel acresLabel = new JLabel("Acreage:");
        acresLabel.setBounds(560, 200, 150, 22);
        acresLabel.setFont(new Font("Railway", Font.PLAIN, 20));
        add(acresLabel);

        acres = new JTextField(); // Initialize the acres variable
        acres.setBounds(680, 200, 80, 25);
        add(acres);

        submitButton = new JButton("Submit");
        submitButton.setBounds(370, 275, 150, 30);
        submitButton.setBackground(new Color(0, 128, 0));
        submitButton.setForeground(new Color(255, 255, 255));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCrop = (String) cropDropdown.getSelectedItem();
                String enteredAcres = acres.getText();

                if (selectedCrop == null || selectedCrop.isEmpty() || enteredAcres.isEmpty()) {
                    JOptionPane.showMessageDialog(Dashboard.this, "Please select a crop and enter the number of acres.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    new Result(selectedCrop, enteredAcres, userName);
                    dispose();
                }
            }
        });
        add(submitButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(550, 275, 150, 30);
        logoutButton.setBackground(new Color(0, 128, 0));
        logoutButton.setForeground(new Color(255, 255, 255));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();  // Assuming you have a Login class for the login page
                dispose();    // Close the current dashboard window
            }
        });
        add(logoutButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard("");
    }
}
