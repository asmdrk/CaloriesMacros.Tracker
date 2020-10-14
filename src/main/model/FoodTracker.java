package model;

import java.util.ArrayList;

public class FoodTracker {
    private ArrayList<Food> foodArray;
    int totalCalories;
    int totalFat;
    int totalCarbs;
    int totalProtein;


    public FoodTracker() {
        foodArray = new ArrayList<Food>();
        totalCalories = 0;
        totalFat = 0;
        totalCarbs = 0;
        totalProtein = 0;
    }

    public void addFood(Food f) {
        foodArray.add(f);
        addTotal(f);

    }


    public Boolean removeFood(Food f) {
        if (foodArray.contains(f)) {
            foodArray.remove(f);
            removeTotal(f);
            return true;
        }
        return false;
    }


    private void addTotal(Food f) {
        totalCalories = totalCalories + f.getCals();
        totalFat = totalFat + f.getFat();
        totalCarbs = totalCarbs + f.getCarbs();
        totalProtein = totalProtein + f.getProtein();
    }


    private void removeTotal(Food f) {
        totalCalories = totalCalories - f.getCals();
        totalFat = totalFat - f.getFat();
        totalCarbs = totalCarbs - f.getCarbs();
        totalProtein = totalProtein - f.getProtein();
    }



    public int getTotalCalories() {
        return totalCalories;
    }

    public int getTotalFat() {
        return totalFat;
    }

    public int getTotalCarbs() {
        return totalCarbs;
    }

    public int getTotalProtein() {
        return totalProtein;
    }



}



