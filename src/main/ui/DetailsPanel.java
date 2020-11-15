package ui;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailsPanel extends JPanel {

    private EventListenerList listenerList = new EventListenerList();

    public DetailsPanel() {
        JLabel nameLabel = new JLabel("Food Name:");
        JLabel calLabel = new JLabel("Calories:");
        JLabel fatLabel = new JLabel("Fat:");
        JLabel proteinLabel = new JLabel("Protein:");
        JLabel carbLabel = new JLabel("Carbohydrates:");

        final JTextField nameField = new JTextField(10);
        final JTextField calField = new JTextField(10);
        final JTextField fatField = new JTextField(10);
        final JTextField proteinField = new JTextField(10);
        final JTextField carbField = new JTextField(10);

        JButton addBtn = new JButton("Add Food Item");

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String cal = calField.getText();
                String fat = fatField.getText();
                String carbs = carbField.getText();
                String protein = proteinField.getText();



                fireDetailEvent(new DetailEvent(this, name, cal, fat, carbs, protein));
            }
        });

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        //First Column
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        add(nameLabel, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(calLabel, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        add(fatLabel, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        add(carbLabel, gc);

        gc.gridx = 0;
        gc.gridy = 4;
        add(proteinLabel, gc);

        //second column
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 0;
        add(nameField, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(calField, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        add(fatField, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        add(carbField, gc);

        gc.gridx = 1;
        gc.gridy = 4;
        add(proteinField, gc);

        //final row
        gc.weighty = 10;

        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.gridx = 0;
        gc.gridy = 5;
        add(addBtn, gc);
    }

    public void fireDetailEvent(DetailEvent event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i <  listeners.length; i += 2) {
            if (listeners[i] == DetailListener.class) {
                ((DetailListener)listeners[i + 1]).detailEventOccured(event);
            }
        }
    }

    public void addDetailListener(DetailListener dl) {
        listenerList.add(DetailListener.class, dl);

    }

    public void removeDetailListener(DetailListener dl) {
        listenerList.remove(DetailListener.class, dl);


    }
}
