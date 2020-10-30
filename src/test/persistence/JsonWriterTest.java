package persistence;

import model.Food;
import model.FoodTracker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            FoodTracker ft = new FoodTracker();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTracker() {
        try {
            FoodTracker ft = new FoodTracker();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFoodTracker.json");
            writer.open();
            writer.write(ft);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFoodTracker.json");
            ft = reader.read();
            assertEquals(0, ft.getArraySize());
            assertEquals(0, ft.getTotalProtein()+
                    ft.getTotalCalories()+ ft.getTotalCarbs()+ft.getTotalFat());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            FoodTracker ft = new FoodTracker();
            ft.addFood(new Food("apple", 88, 0, 11, 2));
            ft.addFood(new Food("custard", 122, 4, 18, 4));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(ft);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            ft = reader.read();
            assertEquals(2, ft.getArraySize());
            assertEquals("apple", ft.getArray().get(0).getName());
            assertEquals("custard", ft.getArray().get(1).getName());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
