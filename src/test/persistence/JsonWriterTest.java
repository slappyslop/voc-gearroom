package persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.GearRoom;
import model.Gear;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*
 * Taken from JSONSERIALIZATIONDEMO
 *  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
class JsonWriterTest extends JsonGearTest {
    GearRoom gr;
    Gear g1;
    Gear g2;

    @BeforeEach
    void runBefore(){
        gr= new GearRoom();
        g1 = new Gear("shoes");
        g2 = new Gear("pants");
        g1.reserve(1, 3);
        g1.reserve(5, 5);
    }
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGearRoom.json");
            writer.open();
            writer.write(gr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGearRoom.json");
            gr = reader.read();
            assertEquals(0, gr.getGearRoom().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            
            gr.addGear(g1);
            gr.addGear(g2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGearRoom.json");
            writer.open();
            writer.write(gr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGearRoom.json");
            gr = reader.read();
            List<Gear> gearRoom = gr.getGearRoom();
            List<Integer> g1reservations = new ArrayList<>();
            List<Integer> g2reservations = new ArrayList<>();
            g1reservations.add(1);
            g1reservations.add(2);
            g1reservations.add(3);
            g1reservations.add(5);

            assertEquals(2, gearRoom.size());
            checkGear("shoes", g1reservations, gearRoom.get(0));
            checkGear("pants", g2reservations, gearRoom.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}