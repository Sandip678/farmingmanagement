import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class DummyResult extends JFrame {
    private String acres;

    public DummyResult() {

        this.acres = acres;
        setLayout(null);  // Layout changed to BorderLayout for simplicity
        setTitle("Farmer Management");
        setSize(800, 700);
        setLocation(350, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBounds(0,0,300,700);
        panel.setBackground(new Color(255,255,255));
        add(panel);

        ImageIcon icon = new ImageIcon(getClass().getResource("images/farmer4.png"));
        Image icon2 = icon.getImage().getScaledInstance(150, 450, Image.SCALE_DEFAULT);
        ImageIcon icon3 = new ImageIcon(icon2);
        JLabel imageLabel = new JLabel(icon3);
        imageLabel.setBounds(0, 0, 150, 450);
        panel.add(imageLabel);

        JPanel titlePanel = new JPanel();
        titlePanel.setBounds(315,10,450,55);
        titlePanel.setBackground(new Color(0, 73, 9));
        add(titlePanel);

        JLabel title = new JLabel("Farmer Management - Result");
        title.setBounds(10,45,430,34);
        title.setFont(new Font("Railway",Font.BOLD,30));
        title.setForeground(new Color(255,255,255));
        titlePanel.add(title);

        JLabel setCrop = new JLabel("Selected Crop: ");
        setCrop.setBounds(325,90,150,25);
        setCrop.setFont(new Font("Railway",Font.PLAIN,20));
        add(setCrop);

        JPanel cropPanel = new JPanel();
        cropPanel.setBounds(460,90,100,30);
        cropPanel.setBackground(new Color(255,255,255));
        add(cropPanel);

        JLabel cropName = new JLabel("Wheat");
        cropName.setBounds(5,0,90,25);
        cropName.setFont(new Font("Arials",Font.PLAIN,20));
        cropPanel.add(cropName);

        JLabel setAcreage  = new JLabel("Acreage: ");
        setAcreage.setBounds(595,90,150,25);
        setAcreage.setFont(new Font("Railway",Font.PLAIN,20));
        add(setAcreage);

        JPanel acrePanel = new JPanel();
        acrePanel.setBounds(678,90,75,30);
        acrePanel.setBackground(new Color(255,255,255));
        add(acrePanel);

        JPanel line1 = new JPanel();
        line1.setBounds(315, 150,450,2);
        line1.setBackground(new Color(0, 73, 9));
        add(line1);

        JPanel box1 = new JPanel();
        box1.setBounds(315,152,450,30);
        box1.setBackground(new Color(255,255,255));
        add(box1);

        JPanel line2 = new JPanel();
        line2.setBounds(315, 192,450,2);
        line2.setBackground(new Color(0, 73, 9));
        add(line2);

        JPanel box2 = new JPanel();
        box2.setBounds(315,194,450,30);
        box2.setBackground(new Color(255,255,255));
        add(box2);

        JPanel line3 = new JPanel();
        line3.setBounds(315, 234,450,2);
        line3.setBackground(new Color(0, 73, 9));
        add(line3);

        JPanel box3 = new JPanel();
        box3.setBounds(315,236,450,30);
        box3.setBackground(new Color(255,255,255));
        add(box3);

        JPanel line4 = new JPanel();
        line4.setBounds(315, 276,450,2);
        line4.setBackground(new Color(0, 73, 9));
        add(line4);

        JPanel box4 = new JPanel();
        box4.setBounds(315,278,450,30);
        box4.setBackground(new Color(255,255,255));
        add(box4);

        JPanel line5 = new JPanel();
        line5.setBounds(315, 318,450,2);
        line5.setBackground(new Color(0, 73, 9));
        add(line5);

        JPanel box5 = new JPanel();
        box5.setBounds(315,320,450,30);
        box5.setBackground(new Color(255,255,255));
        add(box5);

        setVisible(true);
    }
}