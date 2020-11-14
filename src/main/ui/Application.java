package ui;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        JFrame frame = new MainFrame("FoodTracker");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setSize(800,600);
            }
        });


    }
}