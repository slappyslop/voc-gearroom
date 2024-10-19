package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Test;

import model.Gear;
import model.GearRoom;
import persistence.JsonReader;

/*
 * Taken from JSONSERIALIZATIONDEMO
 *  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonReaderTest extends JsonGearTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GearRoom gr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            GearRoom gr = reader.read();
            assertTrue(gr.getGearRoom().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            GearRoom gr = reader.read();
            List<Gear> gears = gr.getGearRoom();
            List<Integer> g1Integers = new ArrayList<>();
            List<Integer> g2Integers = new ArrayList<>();
            assertEquals(2, gears.size());
            checkGear("skis", g1Integers, gears.get(0));
            checkGear("boots", g2Integers, gears.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

