package ui;


import model.Food;
import model.FoodTracker;

import java.util.Scanner;

public class TrackerApp {
    private FoodTracker ft;
    private Scanner input;
    private int calorieLimit = 2000;

    public TrackerApp() {
        runTracker();
    }

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


    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddFood();
        } else if (command.equals("s")) {
            doSetCalories();
        } else if (command.equals("t")) {
            doTotals();
        } else {
            System.out.println("Invalid Command");
        }

    }



    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tcurrent calorie limit:" + calorieLimit);
        System.out.println("\ta -> add food item");
        System.out.println("\ts -> set calorie limit");
        System.out.println("\tt -> view the total nutritional info of food items");
        System.out.println("\tq -> quit");
    }



    private void doAddFood() {
        Food f;
        System.out.println("Enter name of item, calories, fat content, carbohydrate content and protein content.");
        String name = input.next();
        int cals = input.nextInt();
        int fat = input.nextInt();
        int carbs = input.nextInt();
        int protein = input.nextInt();
        f = new Food(name, cals, fat, carbs, protein);
        ft.addFood(f);
        if (ft.getTotalCalories() >= calorieLimit) {
            System.out.println("Calorie Limit Reached!");
        } else {
            System.out.println("Calories Remaining:" + (calorieLimit - ft.getTotalCalories()));
        }
    }


    private void doTotals() {
        System.out.println("Total Calories-" + ft.getTotalCalories());
        System.out.println("Total Fat-" + ft.getTotalFat());
        System.out.println("Total Carbohydrates-" + ft.getTotalCarbs());
        System.out.println("Total Protein-" + ft.getTotalProtein());

    }

    private void doSetCalories() {
        System.out.println("enter new calorie limit");
        int limit = input.nextInt();
        calorieLimit = limit;
    }


    private void init() {
        ft = new FoodTracker();
        input = new Scanner(System.in);
    }


}
