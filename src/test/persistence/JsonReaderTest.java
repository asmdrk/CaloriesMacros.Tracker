package persistence;

import model.FoodTracker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FoodTracker ft = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFoodTracker() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyFoodTracker.json");
        try {
            FoodTracker ft = reader.read();
            assertEquals(0, ft.getArraySize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFoodTracker() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFoodTracker.json");
        try {
            FoodTracker ft = reader.read();
            assertEquals(2, ft.getArraySize());
            assertEquals("apple", ft.getArray().get(0).getName());
            assertEquals("custard", ft.getArray().get(1).getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
