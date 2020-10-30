package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;

//foodTracker represents a list of food items with their name and nutritional information,
//along with the total nutritional info of all the items in the list.
public class FoodTracker implements Writeable {
    private ArrayList<Food> foodArray;
    int totalCalories;
    int totalFat;
    int totalCarbs;
    int totalProtein;

    /*
     *EFFECTS: Construct foodTracker with empty array list, and values of 0 for all the totals.(as no food has been
     * added).
     */
    public FoodTracker() {
        foodArray = new ArrayList<Food>();
        totalCalories = 0;
        totalFat = 0;
        totalCarbs = 0;
        totalProtein = 0;
    }

    /*MODIFIES: this
     *EFFECTS: Adds food f to foodArray, and adds its nutritional info to the totals.
     */
    public void addFood(Food f) {
        foodArray.add(f);
        addTotal(f);

    }

    /*MODIFIES: this
     *EFFECTS: Checks if food f is in foodArray, if true then removes the food item and returns true and updates total
     * values, otherwise returns false.
     */
    public Boolean removeFood(Food f) {
        if (foodArray.contains(f)) {
            foodArray.remove(f);
            removeTotal(f);
            return true;
        }
        return false;
    }

    /*MODIFIES: this
     *EFFECTS: Addz nutritional info of food to total nutritional info of foodTracker.
     */
    private void addTotal(Food f) {
        totalCalories = totalCalories + f.getCals();
        totalFat = totalFat + f.getFat();
        totalCarbs = totalCarbs + f.getCarbs();
        totalProtein = totalProtein + f.getProtein();
    }

    /*MODIFIES: this
     *EFFECTS: Removes nutritional info of food from total nutritional info of foodTracker.
     */
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




    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("list", foodToJson());
        return json;

    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray foodToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food f : foodArray) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }
}



