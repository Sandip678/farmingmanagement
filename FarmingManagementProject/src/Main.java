//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        // Create the Result page with the user's name
        //new Login();
        //new DummyResult( );
        SplashScreen splashScreen =new SplashScreen();
        Thread startThread = new Thread(splashScreen);

        startThread.start();


    }



    }
