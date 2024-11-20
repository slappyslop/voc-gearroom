package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import model.Gear;
import model.GearRoom;
import model.Member;
import persistence.JsonGearRoomReader;
import persistence.JsonGearRoomWriter;

/*
 * Panel that handles the gear screen
 */
public class GearPanel extends JPanel {
    private static final int LABEL_WIDTH = 3;
    private static final String GEARROOM_JSON_STORE = "./data/testReaderGeneralGearRoom.json";
    private GUI gui;
    private Member currentMember;
    private GearRoom gearRoom;
    private JButton addGear;
    private JButton saveGearRoom;
    private JButton loadGearRoom;
    private JButton logOut;
    private JLabel messageLabel;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JsonGearRoomReader jsonGearRoomReader;
    private JsonGearRoomWriter jsonGearRoomWriter;

    //REQUIRES: m != null
    //EFFECTS: Displays the gear view screen
    public GearPanel(Member m, GUI gui) {
        this.gui = gui;
        currentMember = m;
        gearRoom = new GearRoom();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(gbl);
        makeLabel("Welcome Gear Master " + m.getName() + "!", 0, 0);
        messageLabel = makeLabel(null, 3, 0);
        displayGearRoom();
        makeButtons();

    }


    // MODIFIES: this
    // EFFECTS: Creates a JLabel with test at position x and y
    private JLabel makeLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = LABEL_WIDTH;
        gbc.fill = gbc.HORIZONTAL;
        add(label, gbc);
        return label;
    }

    // MODIFIES: this
    // EFFECTS: initializes buttons
    private void makeButtons() {
       loadGearRoom = makeButton("Load Gear Room", 0, 1);
       saveGearRoom = makeButton("Save Gear Room", 1, 1);
       addGear = makeButton("Add Gear", 2, 1);
       logOut = makeButton("Log out", 3, 1);
       logOut.addActionListener(new LogOutListener());
       loadGearRoom.addActionListener(new LoadGearRoomListener());
       saveGearRoom.addActionListener(new SaveGearRoomListener());
    }

    // MODIFIES: this
    // EFFECTS: Returns A Jbutton with text at given coordinates
    private JButton makeButton(String name, int x, int y) {
        JButton button = new JButton(name);
        gbc.gridwidth = 1;
        gbc.gridx = x;
        gbc.gridy = y;
        add(button, gbc);
        return button;
    }


    //EFFECTS: Displays a JList of gear names
    private JList displayGearRoom() {
        String[] columnNames = {"Gear Name", "Reserved on"};
        List<Gear> gears = gearRoom.getGearRoom();
        List<String> gearNames = getGearNames(gears);
        JList<String> list = new JList(gearNames.toArray());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        add(list, gbc);
        return list;
        
    }

    private List<String> getGearNames(List<Gear> gears) {
        List<String> gearNames = new ArrayList<>();
        for (Gear gear : gears) {
            gearNames.add(gear.getName());
        }
        return gearNames;
    }


    // Handles LoadGearRoom button click
    private class LoadGearRoomListener implements ActionListener { 
        @Override
        public void actionPerformed(ActionEvent e) {
            jsonGearRoomReader = new JsonGearRoomReader(GEARROOM_JSON_STORE);
            try {  
                gearRoom = jsonGearRoomReader.read();
                messageLabel.setText("Successfully read from" + GEARROOM_JSON_STORE + "!");
                displayGearRoom();
            } catch (IOException e1) {
                messageLabel.setText("Unable to read from" + GEARROOM_JSON_STORE + "!");
               
            }

        }

    }

    // Handles SaveGearRoom button click
    private class SaveGearRoomListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            jsonGearRoomWriter = new JsonGearRoomWriter(GEARROOM_JSON_STORE);
            try {  
                jsonGearRoomWriter.open();
                jsonGearRoomWriter.write(gearRoom);
                jsonGearRoomWriter.close();
                messageLabel.setText("Successfully wrote to" + GEARROOM_JSON_STORE + "!");
            } catch (IOException e1) {
                messageLabel.setText("Unable to write to" + GEARROOM_JSON_STORE + "!");
               
            }
        }

    }

    // Handles AddGear button click
    private class AddGearListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }

    }

    // Handles LogOut button click
    private class LogOutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            currentMember = null;
            gui.logOut();
        }

    }
}
