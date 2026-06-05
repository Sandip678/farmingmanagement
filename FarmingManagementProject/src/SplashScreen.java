import javax.swing.*;
import java.awt.*;

public class SplashScreen  extends JFrame implements  Runnable {

    public SplashScreen(){

        setTitle("Farming Management");
        setLayout(null);
        setSize(800,400);
        setLocation(350,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(getClass().getResource("images/farmer3.png"));
         Image icon1 = icon.getImage().getScaledInstance(800, 400, Image.SCALE_DEFAULT);
         ImageIcon icon2 = new ImageIcon(icon1);
         JLabel imageLabel = new JLabel(icon2);
         imageLabel.setBounds(0,0,800, 400);
         add(imageLabel);



        setVisible(true);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            dispose();
            new Login().setVisible(true);
        }
    }
}
