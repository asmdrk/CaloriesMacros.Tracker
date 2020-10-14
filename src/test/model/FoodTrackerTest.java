package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTrackerTest {
    Food apple;
    Food pizzaSlice;
    Food pasta;
    FoodTracker ft;
    @BeforeEach
    public void setup() {


        apple = new Food("Apple", 95, 0, 25, 1);
        pizzaSlice = new Food("Pizza", 285, 10, 36, 12);
        pasta = new Food("Pasta", 131,4,17,5);
        ft = new FoodTracker();
    }

    @Test
    public void addFoodOnceTest() {
        ft.addFood(apple);
        assertEquals(95, ft.getTotalCalories());
        assertEquals(0, ft.getTotalFat());
        assertEquals(25, ft.getTotalCarbs());
        assertEquals(1, ft.getTotalProtein());
    }

    @Test
    public void addFoodTwiceTest() {
        ft.addFood(apple);
        ft.addFood(pizzaSlice);
        assertEquals(380, ft.getTotalCalories());
        assertEquals(10, ft.getTotalFat());
        assertEquals(61, ft.getTotalCarbs());
        assertEquals(13, ft.getTotalProtein());
    }


    @Test
    public void removeFoodFalseTest() {
        ft.addFood(apple);
        ft.addFood(pasta);
        assertFalse(ft.removeFood(pizzaSlice));
    }

    @Test
    public void removeFoodTrueTest() {
        ft.addFood(apple);
        ft.addFood(pasta);
        assertTrue(ft.removeFood(apple));
        assertEquals(131, ft.getTotalCalories());
        assertEquals(4, ft.getTotalFat());
        assertEquals(17, ft.getTotalCarbs());
        assertEquals(5, ft.getTotalProtein());
    }

    @Test
    public void removeFoodTwiceTest() {
        ft.addFood(apple);
        ft.addFood(pasta);
        assertTrue(ft.removeFood(apple));
        assertTrue(ft.removeFood(pasta));
        assertEquals(0, ft.getTotalCalories());
        assertEquals(0, ft.getTotalFat());
        assertEquals(0, ft.getTotalCarbs());
        assertEquals(0, ft.getTotalProtein());
    }





}