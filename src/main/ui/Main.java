package ui;

import java.io.FileNotFoundException;

//runs the TrackerApp
public class Main {
    public static void main(String[] args) {
        try {
            new TrackerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Could not run: file not found");
        }

    }
}
