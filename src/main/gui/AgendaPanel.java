package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JOptionPane;


import model.GearRoom;
import model.Member;
import model.Trip;
import model.TripAgenda;
import persistence.JsonTripAgendaReader;
import persistence.JsonTripAgendaWriter;

/*
 * Panel that handles the trip agenda screen
 */
public class AgendaPanel extends JPanel {
    private static final int LABEL_WIDTH = 3;
    private static final String TRIPAGENDA_JSON_STORE = "./data/tripagenda.json";
    private Member currentMember;
    private GUI gui;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JsonTripAgendaReader jsonTripAgendaReader;
    private JsonTripAgendaWriter jsonTripAgendaWriter;
    private JList<String> tripAgendaDisplay;
    private DefaultListModel<String> agendaNames;
    private TripAgenda agenda;
    private GearRoom gearRoom;
    private JLabel messageLabel;
    private JButton loadTripAgenda;
    private JButton saveTripAgenda;
    private JButton addTrip;
    private JButton viewSelectedTrip;
    private JButton logOut;
    
    //REQUIRES: m != null
    //EFFECTS: Displays the agenda view screen
    public AgendaPanel(Member m, GUI gui) {
        this.gearRoom = gui.getGearRoom();
        this.agenda = gui.getAgenda();
        this.gui = gui;
        currentMember = m;
        agenda = new TripAgenda();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        agendaNames = new DefaultListModel<>();
        setLayout(gbl);
        makeLabel("Welcome " + currentMember.getLogInState()+ " " + currentMember.getName() +"!", 0, 0);
        messageLabel = makeLabel(null, 3, 0);
        tripAgendaDisplay =  displayTripAgenda();
        makeButtons();
    }

    //EFFECTS: Displays a JList of trip names
    private JList<String> displayTripAgenda() {
        JList<String> list = new JList<String>();
        updateAgendaModel();
        list.setModel(agendaNames);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        add(list, gbc);
        return list;
    }

    // EFFECTS: updates the tripNames list model
    private void updateAgendaModel() {
        agendaNames.clear();
        for (Trip t : agenda.getTrips()) {
            agendaNames.addElement(t.getName());
        }
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
        loadTripAgenda = makeButton("Load Trip Agenda", 0, 1);
        saveTripAgenda = makeButton("Save Trip Agenda", 1, 1);
        if (currentMember.getLogInState() == "leader") {
            addTrip = makeButton("Add Trip", 2, 1);
            addTrip.addActionListener(new AddTripListener());
        }
        viewSelectedTrip = makeButton("View Selected Trip", 3, 1);
        logOut = makeButton("Log Out", 4, 1);
        logOut.addActionListener(new LogOutListener());
        loadTripAgenda.addActionListener(new LoadTripAgendaListener());
        saveTripAgenda.addActionListener(new SaveTripAgendaListener());
        viewSelectedTrip.addActionListener(new ViewTripListener());
    
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



    // Handles LogOut button click
    private class LogOutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            currentMember = null;
            gui.logOut();
        }

    }

    // Handles loadTripAgenda button click
    private class LoadTripAgendaListener implements ActionListener { 
        @Override
        public void actionPerformed(ActionEvent e) {
            jsonTripAgendaReader = new JsonTripAgendaReader(TRIPAGENDA_JSON_STORE, gearRoom);
            try {  
                agenda = jsonTripAgendaReader.read();
                updateAgendaModel();
                messageLabel.setText("Successfully read from" + TRIPAGENDA_JSON_STORE + "!");
                
            } catch (IOException e1) {
                messageLabel.setText("Unable to read from" + TRIPAGENDA_JSON_STORE + "!");
               
            }

        }

    }

    // Handles saveTripAgenda button click
    private class SaveTripAgendaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            jsonTripAgendaWriter = new JsonTripAgendaWriter(TRIPAGENDA_JSON_STORE);
            try {  
                jsonTripAgendaWriter.open();
                jsonTripAgendaWriter.write(agenda);
                jsonTripAgendaWriter.close();
                messageLabel.setText("Successfully wrote to" + TRIPAGENDA_JSON_STORE + "!");
            } catch (FileNotFoundException e1) {
                messageLabel.setText("Unable to write to" + TRIPAGENDA_JSON_STORE + "!");
               
            }
        }
    }

    // Handles AddTrip button click
    private class AddTripListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog(gui, "Trip Name:", null);
            String gearList = JOptionPane.showInputDialog(gui, "Gear List: (insert comma and space between lowerspace gear names)", null);
            String[] gl = gearList.split(", ");
            int startDate = Integer.valueOf(JOptionPane.showInputDialog("Start date (integer):"));
            int endDate = Integer.valueOf(JOptionPane.showInputDialog("End Date (integer >= start)"));
            Trip t = new Trip(Arrays.asList(gl));
            t.setName(name);
            t.setStartDate(startDate);
            t.setEndDate(endDate);
            t.addToGoing(currentMember);
            agenda.addTrip(t);
            agendaNames.addElement(name);
            
        }
    }

    // Handles viewTrip button click
    private class ViewTripListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Trip t = agenda.getTrips().get(tripAgendaDisplay.getSelectedIndex());
            gui.viewTrip(t);
        }

    }

    

}
