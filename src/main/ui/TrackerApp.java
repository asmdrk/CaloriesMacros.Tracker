package ui;


import model.Food;
import model.FoodTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Food tracking application
//Note: The structure of this class is based on the BankTeller project provided to us on edx.
public class TrackerApp {
    private static final String JSON_STORE = "./data/foodtracker.json";
    private FoodTracker ft;
    private Scanner input;
    private int calorieLimit = 2000;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the tracking application.
    public TrackerApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTracker();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTracker() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddFood();
        } else if (command.equals("c")) {
            doSetCalories();
        } else if (command.equals("t")) {
            doTotals();
        } else if (command.equals("s")) {
            doSave();
        } else if (command.equals("l")) {
            doLoad();
        } else {
            System.out.println("Invalid Command");
        }

    }

    private void doSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(ft);
            jsonWriter.close();
            System.out.println("Saved all the nutritional value and totals to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void doLoad() {
        try {
            ft = jsonReader.read();
            System.out.println("Loaded nutritional info and totals from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    //EFFECTS: Display menu and options to user.
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tcurrent calorie limit:" + calorieLimit);
        System.out.println("\ta -> add food item");
        System.out.println("\tc -> change calorie limit");
        System.out.println("\tt -> view the total nutritional info of food items");
        System.out.println("\ts -> save currently entered nutritional values to file");
        System.out.println("\tl -> load nutritional values from file");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: constructs a food with given specifications and adds it to foodTracker, then checks if calorie limit
    //has been reached. If it has been reached it alerts the user and tells them how much they have exceeded the limit.
    // Otherwise it displays the amount of calories left. If any any of the nutritional information is negative,
    // prints an error message.
    private void doAddFood() {
        Food f;
        System.out.println("Enter name of item, calories, fat content, carbohydrate content and protein content.");
        String name = input.next();
        int cals = input.nextInt();
        int fat = input.nextInt();
        int carbs = input.nextInt();
        int protein = input.nextInt();
        if (cals < 0 || fat < 0 || carbs < 0 || protein < 0) {
            System.out.println("ERROR: Nutritional Info cannot have negative values.");
        } else {
            f = new Food(name, cals, fat, carbs, protein);
            ft.addFood(f);
            if (ft.getTotalCalories() >= calorieLimit) {
                System.out.println("Calorie Limit Reached! You are "
                        + (ft.getTotalCalories() - calorieLimit) + " calories over the limit");
            } else {
                System.out.println("Calories Remaining:" + (calorieLimit - ft.getTotalCalories()));
            }
        }
    }

    //EFFECTS: Prints out the total nutritional info of all the food in foodTracker.
    private void doTotals() {
        System.out.println("Total Calories-" + ft.getTotalCalories());
        System.out.println("Total Fat-" + ft.getTotalFat());
        System.out.println("Total Carbohydrates-" + ft.getTotalCarbs());
        System.out.println("Total Protein-" + ft.getTotalProtein());

    }

    // REQUIRES: limit cannot be negative
    //MODIFIES: calorieLimit
    //EFFECTS: If limit entered is positive, set calorieLimit to that value, otherwise print a warning message.
    private void doSetCalories() {
        System.out.println("enter new calorie limit");
        int limit = input.nextInt();
        if (limit <= 0) {
            System.out.println("WARNING: calorie limit cannot be negative or zero");
        } else {
            calorieLimit = limit;
        }
    }


    // EFFECTS: initializes accounts
    private void init() {
        ft = new FoodTracker();
        input = new Scanner(System.in);
    }


}
