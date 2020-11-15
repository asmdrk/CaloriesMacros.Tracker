package ui;


import model.Food;
import model.FoodTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MainFrame extends JFrame {
    private static final String JSON_STORE = "./data/foodtracker.json";
    private FoodTracker ft;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    URL soundbyte = new File("src/main/Sounds/beep-07.wav").toURI().toURL();
    java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);


    public MainFrame(String title) throws MalformedURLException {
        super(title);
        ft = new FoodTracker();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        //layout manager
        setLayout(new BorderLayout());

        //create components
        JTextArea ta = new JTextArea();
        JButton buttonsave = new JButton("Save");
        JButton buttonload = new JButton("Load");
        JButton buttontotal = new JButton("View Totals");


        DetailsPanel detailsPanel = new DetailsPanel();
        detailsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        detailsPanel.addDetailListener(new DetailListener() {
            public void detailEventOccured(DetailEvent event) {
                String text = event.getText();
                Food f = event.getFood();
                try {
                    ft.addFood(f);
                    ta.append(text + "\n");
                    clip.play();
                } catch (NumberFormatException nfe) {
                    ta.append("Nutritional Values must be integers");
                }

            }
        });

        buttontotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = "Total Calories: " + ft.getTotalCalories() + "\n"
                        + "Total Fat:" + ft.getTotalFat() + "\n"
                        + "Total Carbs" + ft.getTotalCarbs() + "\n"
                        + "Total Protein" + ft.getTotalProtein() + "\n";

                ta.append(text);
                clip.play();

            }

        });

        buttonsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(ft);
                    jsonWriter.close();
                    ta.append("Saved all the nutritional value and totals to " + JSON_STORE);
                    clip.play();

                } catch (FileNotFoundException f) {
                    System.out.println("Unable to write to file: " + JSON_STORE);
                }
            }
        });

        buttonload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ft = jsonReader.read();
                    ta.append("Loaded nutritional info and totals from " + JSON_STORE + "\n");
                    clip.play();
                    for (Food f : ft.getArray()) {
                        ta.append("Food Name: " + f.getName() + ", Calories: " + f.getCals()
                                + ", Fat: " + f.getFat()
                                + ", Carbohydrates: " + f.getCarbs() + ", Protein: " + f.getProtein() + "\n");
                    }
                } catch (IOException f) {
                    ta.append("Unable to read from file: " + JSON_STORE);
                }
            }
        });

        //add to components to pane
        Container c = getContentPane();

        c.add(detailsPanel, BorderLayout.PAGE_END);
        c.add(buttonsave, BorderLayout.BEFORE_LINE_BEGINS);
        c.add(buttonload, BorderLayout.AFTER_LINE_ENDS);
        c.add(ta, BorderLayout.CENTER);
        c.add(buttontotal, BorderLayout.NORTH);



        java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
        clip.play();

    }


}
