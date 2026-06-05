import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Result extends JFrame {
    private String acres;

    public Result(String crop, String acres, String userName) {
        this.acres = acres;
        setLayout(null);
        setTitle("Farmer Management");
        setSize(800, 700);
        setLocation(350, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 300, 500);
        panel.setBackground(new Color(255, 255, 255));
        add(panel);

        // Left side image
        ImageIcon icon = new ImageIcon(getClass().getResource("images/farmer4.png"));
        Image icon2 = icon.getImage().getScaledInstance(150, 450, Image.SCALE_DEFAULT);
        ImageIcon icon3 = new ImageIcon(icon2);
        JLabel imageLabel = new JLabel(icon3); 
        imageLabel.setBounds(0, 0, 150, 450);
        panel.add(imageLabel);

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBounds(315, 10, 450, 40);
        titlePanel.setBackground(new Color(0, 73, 9));
        add(titlePanel);

        JLabel title = new JLabel(userName+", WelCome To Farmer Management");
        title.setFont(new Font("Railway", Font.BOLD, 20));
        title.setForeground(new Color(255, 255, 255));
        titlePanel.add(title);

        // Selected crop label
        JLabel setCrop = new JLabel("Selected Crop: ");
        setCrop.setBounds(325, 90, 150, 25);
        setCrop.setFont(new Font("Railway", Font.PLAIN, 20));
        add(setCrop);

        JLabel cropName = new JLabel(crop);
        cropName.setBounds(460, 90, 100, 25);
        cropName.setFont(new Font("Arial", Font.PLAIN, 20));
        add(cropName);

        // Acreage label
        JLabel setAcreage = new JLabel("Acreage: ");
        setAcreage.setBounds(595, 90, 150, 25);
        setAcreage.setFont(new Font("Railway", Font.PLAIN, 20));
        add(setAcreage);

        JLabel acresLabel = new JLabel(acres + " acres");
        acresLabel.setBounds(678, 90, 75, 25);
        acresLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(acresLabel);

        // Divider line
        JPanel line1 = new JPanel();
        line1.setBounds(315, 150, 450, 2);
        line1.setBackground(new Color(0, 73, 9));
        add(line1);

        // Fetching details
        double seedCost = fetchCropDetails(crop);
        double totalFertilizerCost = fetchFertilizerDetails(crop);
        double totalInsecticideCost = fetchInsecticideDetails(crop);
        double totalEstimatedCost = seedCost + totalFertilizerCost + totalInsecticideCost;

        // Displaying results
        int baseYPosition = 152; // Starting Y position for results
        baseYPosition = addResultBox(baseYPosition, "Seed Cost: " + seedCost);

        // Displaying fertilizers and insecticides
        baseYPosition = displayFertilizers(crop, baseYPosition);
        baseYPosition = displayInsecticides(crop, baseYPosition);

        // Total cost panel
        JPanel totalCostPanel = new JPanel();
        totalCostPanel.setBounds(315, baseYPosition + 20, 450, 30); // Set below the last result box
        totalCostPanel.setBackground(new Color(0, 73, 9)); // Dark green background
        add(totalCostPanel);

        JLabel totalCostLabel = new JLabel("Total Estimated Cost: " + totalEstimatedCost);
        totalCostLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalCostLabel.setForeground(Color.WHITE); // White text
        totalCostPanel.add(totalCostLabel);

        JPanel backPannel = new JPanel();
        backPannel.setBounds(0,500,300,200);
        backPannel.setBackground(new Color(255,255,255));
        add(backPannel);

        // Back to Dashboard button
        JButton backButton = new JButton("Back to Dashboard");
        backButton.setBounds(25, 125, 275, 50);
        backButton.setBackground(new Color(0,73,9));
        backButton.setForeground(new Color(255,255,255));
        backButton.setFont(new Font("Railway",Font.BOLD,28));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Dashboard(userName).setVisible(true); // Pass the userName to Dashboard
                dispose();
            }
        });
        backPannel.add(backButton);

        setVisible(true);
    }

    private int addResultBox(int yPosition, String resultText) {
        JPanel box = new JPanel();
        box.setBounds(315, yPosition, 450, 30);
        box.setBackground(new Color(255, 255, 255));
        JLabel resultLabel = new JLabel(resultText);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        box.add(resultLabel);
        add(box);

        JPanel line = new JPanel();
        line.setBounds(315, yPosition + 30, 450, 2);
        line.setBackground(new Color(0, 73, 9));
        add(line);

        return yPosition + 50; // Return the next Y position
    }

    private int displayFertilizers(String crop, int yPosition) {
        Connection conn = Con.getConnection();
        if (conn != null) {
            String sql = "SELECT fertilizer_name, cost_per_acre FROM fertilizers WHERE crop_name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, crop);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    String fertilizerName = rs.getString("fertilizer_name");
                    double costPerAcre = rs.getDouble("cost_per_acre");
                    yPosition = addResultBox(yPosition, "Fertilizer: " + fertilizerName + " - Cost per Acre: " + costPerAcre);
                }
            } catch (SQLException e) {
                showErrorDialog(e);
            }
        }
        return yPosition;
    }

    private int displayInsecticides(String crop, int yPosition) {
        Connection conn = Con.getConnection();
        if (conn != null) {
            String sql = "SELECT insecticide_name, cost_per_acre FROM insecticides WHERE crop_name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, crop);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    String insecticideName = rs.getString("insecticide_name");
                    double costPerAcre = rs.getDouble("cost_per_acre");
                    yPosition = addResultBox(yPosition, "Insecticide: " + insecticideName + " - Cost per Acre: " + costPerAcre);
                }
            } catch (SQLException e) {
                showErrorDialog(e);
            }
        }
        return yPosition;
    }

    private double fetchCropDetails(String crop) {
        double totalSeedCost = 0;
        Connection conn = Con.getConnection();
        if (conn != null) {
            String sql = "SELECT * FROM crop_details WHERE crop_name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, crop);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    double seedCost = rs.getDouble("seed_cost_per_acre");
                    totalSeedCost = seedCost * Double.parseDouble(acres);
                }
            } catch (SQLException e) {
                showErrorDialog(e);
            }
        }
        return totalSeedCost;
    }

    private double fetchFertilizerDetails(String crop) {
        double totalFertilizerCost = 0;
        Connection conn = Con.getConnection();
        if (conn != null) {
            String sql = "SELECT * FROM fertilizers WHERE crop_name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, crop);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    double costPerAcre = rs.getDouble("cost_per_acre");
                    totalFertilizerCost += costPerAcre * Double.parseDouble(acres);
                }
            } catch (SQLException e) {
                showErrorDialog(e);
            }
        }
        return totalFertilizerCost;
    }

    private double fetchInsecticideDetails(String crop) {
        double totalInsecticideCost = 0;
        Connection conn = Con.getConnection();
        if (conn != null) {
            String sql = "SELECT * FROM insecticides WHERE crop_name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, crop);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    double costPerAcre = rs.getDouble("cost_per_acre");
                    totalInsecticideCost += costPerAcre * Double.parseDouble(acres);
                }
            } catch (SQLException e) {
                showErrorDialog(e);
            }
        }
        return totalInsecticideCost;
    }

    private void showErrorDialog(SQLException e) {
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
