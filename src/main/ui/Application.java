package ui;

import javax.swing.*;
import java.net.MalformedURLException;

public class Application {
    public static void main(String[] args) {
        try {
            JFrame frame = new MainFrame("FoodTracker");


            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    frame.setSize(800, 600);

                }
            });


        } catch (MalformedURLException e) {
            System.out.println("error with sound file");
        }
    }
}