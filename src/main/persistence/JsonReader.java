package persistence;

import model.Food;
import model.FoodTracker;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads FoodTracker from JSON data stored in file
// NOTE: This class, along with all other classes in the persistence package and the tests for the persistence
// package are based heavily on the JsonSerializationDemo application provided to us.
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    // EFFECTS: reads FoodTracker from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FoodTracker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFoodTracker(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses FoodTracker from JSON object and returns it
    private FoodTracker parseFoodTracker(JSONObject jsonObject) {
        FoodTracker ft = new FoodTracker();
        addFoods(ft, jsonObject);
        return ft;
    }

    // MODIFIES: ft
    // EFFECTS: parses food from JSON object and adds them to FoodTracker
    private void addFoods(FoodTracker ft, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(ft, nextFood);
        }
    }

    // MODIFIES: ft
    // EFFECTS: parses food from JSON object and adds it to Foodtracker
    private void addFood(FoodTracker ft, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int cals = jsonObject.getInt("cals");
        int protein = jsonObject.getInt("protein");
        int fat = jsonObject.getInt("fat");
        int carbs = jsonObject.getInt("carbs");
        Food food = new Food(name, cals, fat, carbs, protein);
        ft.addFood(food);
    }


}
