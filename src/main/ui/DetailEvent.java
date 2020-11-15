package ui;

import model.Food;

import java.util.EventObject;

public class DetailEvent extends EventObject {

    private String name;
    private String cal;
    private String carbs;
    private String fat;
    private String protein;
    private String text;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public DetailEvent(Object source, String name, String cal, String fat, String carbs, String protein) {
        super(source);

        this.name = name;
        this.cal = cal;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
        text = "Food Name: " + name + ", Calories: " + cal
                + ", Fat: " + fat + ", Carbohydrates: " + carbs + ", Protein: " + protein;
    }

    public String getText() {
        return text;
    }

    public Food getFood() {
        Food f = new Food(name, Integer.parseInt(cal), Integer.parseInt(fat),
                Integer.parseInt(carbs), Integer.parseInt(protein));
        return f;
    }
}
