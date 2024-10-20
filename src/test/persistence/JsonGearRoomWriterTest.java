package persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.GearRoom;
import model.Gear;


import static org.junit.jupiter.api.Assertions.*;
/*
 * Taken from JSONSERIALIZATIONDEMO
 *  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
class JsonGearRoomWriterTest extends JsonGearTest {
    private GearRoom gr;
    private Gear g1;
    private Gear g2;

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
            JsonGearRoomWriter writer = new JsonGearRoomWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            JsonGearRoomWriter writer = new JsonGearRoomWriter("./data/testWriterEmptyGearRoom.json");
            writer.open();
            writer.write(gr);
            writer.close();

            JsonGearRoomReader reader = new JsonGearRoomReader("./data/testWriterEmptyGearRoom.json");
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
            JsonGearRoomWriter writer = new JsonGearRoomWriter("./data/testWriterGeneralGearRoom.json");
            writer.open();
            writer.write(gr);
            writer.close();

            JsonGearRoomReader reader = new JsonGearRoomReader("./data/testWriterGeneralGearRoom.json");
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