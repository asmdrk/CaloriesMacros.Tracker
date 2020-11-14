package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private DetailsPanel detailsPanel;

    public MainFrame(String title) {
        super(title);

        //layout manager
        setLayout(new BorderLayout());

        //create components
        JTextArea ta = new JTextArea();
        JButton buttonsave = new JButton("Save");
        JButton buttonload = new JButton("Load");
        JButton buttonadd = new JButton("Add Food Item");
        JButton buttontotal = new JButton("View Totals");


        detailsPanel = new DetailsPanel();

        detailsPanel.addDetailListener(new DetailListener() {
            public void detailEventOccured(DetailEvent event) {
                String text = event.getText();

                ta.append(text + "\n");
            }
        });

        //add to components to pane
        Container c = getContentPane();

        c.add(detailsPanel, BorderLayout.PAGE_END);
        c.add(buttonsave, BorderLayout.BEFORE_LINE_BEGINS);
        c.add(buttonload, BorderLayout.AFTER_LINE_ENDS);
        c.add(ta, BorderLayout.CENTER);
        c.add(buttontotal, BorderLayout.NORTH);


    }


}
