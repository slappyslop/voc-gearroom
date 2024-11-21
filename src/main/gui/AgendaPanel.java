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


import model.GearRoom;
import model.Member;
import model.Trip;
import model.TripAgenda;
import persistence.JsonGearRoomReader;
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
    public AgendaPanel(Member m, GUI gui, GearRoom gr) {
        this.gearRoom = gr;
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
        }
        viewSelectedTrip = makeButton("View Selected Trip", 3, 1);
        logOut = makeButton("Log Out", 4, 1);
        logOut.addActionListener(new LogOutListener());
        loadTripAgenda.addActionListener(new LoadTripAgendaListener());
    
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

    

}
