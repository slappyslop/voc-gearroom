package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

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
    private static final String GEARROOM_JSON_STORE = "./data/gearroom.json";
    private GUI gui;
    private Member currentMember;
    private GearRoom gearRoom;
    private DefaultListModel<String> gearNames;
    private JButton addGear;
    private JButton saveGearRoom;
    private JButton loadGearRoom;
    private JButton logOut;
    private JButton removeSelected;
    private JLabel messageLabel;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JsonGearRoomReader jsonGearRoomReader;
    private JsonGearRoomWriter jsonGearRoomWriter;
    private JList<String> gearRoomDisplay;

    //REQUIRES: m != null
    //EFFECTS: Displays the gear view screen
    public GearPanel(Member m, GUI gui, GearRoom gr) {
        this.gearRoom = gr;
        this.gui = gui;
        currentMember = m;
        gearRoom = new GearRoom();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        jsonGearRoomWriter = new JsonGearRoomWriter(GEARROOM_JSON_STORE);
        gearNames = new DefaultListModel<String>();
        setLayout(gbl);
        makeLabel("Welcome Gear Master " + currentMember.getName() + "!", 0, 0);
        messageLabel = makeLabel(null, 3, 0);
        gearRoomDisplay = displayGearRoom();
        makeButtons();

    }


    // MODIFIES: this
    // EFFECTS: Creates a JLabel with test at position x and y
    private JLabel makeLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = LABEL_WIDTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(label, gbc);
        return label;
    }

    // MODIFIES: this
    // EFFECTS: initializes buttons
    private void makeButtons() {
       loadGearRoom = makeButton("Load Gear Room", 0, 1);
       saveGearRoom = makeButton("Save Gear Room", 1, 1);
       addGear = makeButton("Add Gear", 2, 1);
       removeSelected = makeButton("Remove selected", 3, 1);
       logOut = makeButton("Log out", 4, 1);
       logOut.addActionListener(new LogOutListener());
       loadGearRoom.addActionListener(new LoadGearRoomListener());
       saveGearRoom.addActionListener(new SaveGearRoomListener());
       addGear.addActionListener(new AddGearListener());
       removeSelected.addActionListener(new RemoveListener());
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
    private JList<String> displayGearRoom() {
        JList<String> list = new JList<String>();
        updateGearModel();
        list.setModel(gearNames);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        add(list, gbc);
        return list;
        
    }

    // EFFECTS: updates the gearNames list model
    private void updateGearModel() {
        gearNames.clear();
        for (Gear gear : gearRoom.getGearRoom()) {
            gearNames.addElement(gear.getName());
        }
    }


    // Handles LoadGearRoom button click
    private class LoadGearRoomListener implements ActionListener { 
        @Override
        public void actionPerformed(ActionEvent e) {
            jsonGearRoomReader = new JsonGearRoomReader(GEARROOM_JSON_STORE);
            try {  
                gearRoom = jsonGearRoomReader.read();
                updateGearModel();
                messageLabel.setText("Successfully read from" + GEARROOM_JSON_STORE + "!");
                
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
            } catch (FileNotFoundException e1) {
                messageLabel.setText("Unable to write to" + GEARROOM_JSON_STORE + "!");
               
            }
        }

    }

    // Handles AddGear button click
    private class AddGearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog(gui, "Gear Name:", null);
            Gear g = new Gear(name);
            gearRoom.addGear(g);
            gearNames.addElement(name);
            
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

    private class RemoveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int gearIndex = gearRoomDisplay.getSelectedIndex();
            gearNames.remove(gearIndex);
            gearRoom.getGearRoom().remove(gearIndex);
        }
        
    }
}
