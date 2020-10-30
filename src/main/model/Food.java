package model;

import org.json.JSONObject;
import persistence.Writeable;

// Food represents food with its name,
// calories, and ,fat, carb, protein content in grams.
public class Food implements Writeable {
    private String name;
    private int cals;
    private int fat;
    private int carbs;
    private int protein;


    /*REQUIRES: name should have non-zero length, calories, fat, carbs, protein cannot be negative.
     *EFFECTS: Construct food with name set to foodName; calories set to cal; fat set to f; carbs set to c; protein
     *set to p.
     */
    public Food(String foodName, int cal, int f, int c, int p) {
        name = foodName;
        cals = cal;
        fat = f;
        carbs = c;
        protein = p;
    }

    public String getName() {
        return name;
    }


    public int getCals() {
        return cals;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public int getProtein() {
        return protein;
    }


    //EFFECTS: saves food class as a Json Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("cals", cals);
        json.put("fat", fat);
        json.put("carbs", carbs);
        json.put("protein", protein);
        return json;
    }
}

