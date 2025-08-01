package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.GearRoom;
import model.Member;
import model.Trip;

/*
 * Panel that handles view trip screen
 */
public class TripPanel extends JPanel {
    private static final int LABEL_WIDTH = 5;
    private Trip trip;
    private GUI gui;
    private GearRoom gearRoom;
    private Member currentMember;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JLabel committedList;
    private JLabel goingList;
    private JLabel interestedList;
    private JButton interest;
    private JButton commit;
    private JButton going;
    private JButton back;

    // EFFECTS: Displays the view trip screen
    TripPanel(Trip trip, GUI gui) {
        this.trip = trip;
        this.gui = gui;
        this.gearRoom = gui.getGearRoom();
        this.currentMember = gui.getCurrentMember();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(gbl);
        makeLabels();
        makeButtons();
    }

    // MODIFIES: this
    // EFFECTS: initializes buttons
    private void makeButtons() {
        interest = makeButton("Register Interest", 0, 1);
        commit = makeButton("Commit", 1, 1);
        if (currentMember.getLogInState().equals("leader")) {
            going = makeButton("Going", 2, 1);
            going.addActionListener(new GoingListener());
        }
        back = makeButton("Back", 3, 1);
        back.addActionListener(new BackListener());
        interest.addActionListener(new InterestListener());
        commit.addActionListener(new CommitListener());

    }

    // MODIFIES: this
    // EFFECTS: initializes labels
    private void makeLabels() {
        makeLabel(trip.getName() + "\t Days: " + String.valueOf(trip.getStartDate()) + " - "
                + String.valueOf(trip.getEndDate()), 0, 0);
        makeLabel("Gear List: " + String.join(", ", trip.getGearList()), 0, 2);
        goingList = makeLabel("Going: " + getString(trip.getGoing()), 0, 3);
        committedList = makeLabel("Committed: " + getString(trip.getCommitted()), 0, 4);
        interestedList = makeLabel("Interested: " + getString(trip.getInterested()), 0, 5);
    }

    //EFFECTS: Converts a List<Member> into a printable string
    private String getString(List<Member> memberList) {
        List<String> memberNames = new ArrayList<String>();
        for (Member member : memberList) {
            memberNames.add(member.getName());
        }
        String listString = String.join(", ", memberNames);
        return listString;
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

    // MODIFIES: this
    // EFFECTS: returns a JLabel with test at position x and y
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
    // EFFECTS: Updates label texts to reflect changes to the underlying trip model
    public void updateLabels() {
        goingList.setText("Going: " + getString(trip.getGoing()));
        committedList.setText("Committed: " + getString(trip.getCommitted()));
        interestedList.setText("Interested: " + getString(trip.getInterested()));
    }


    // Handles back button press
    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gui.viewAgenda();
        }

    }

    // Handles register interest button press
    private class InterestListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            currentMember.registerInterested(trip);
            makeLabels();
            updateLabels();
            updateUI();
        }

    }

    // EFFECTS: Handles commit button press
    private class CommitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String myGearString = JOptionPane.showInputDialog("What Gear do you currently own"
                    + "(insert comma and space between lowerspace gear names)");
            String[] myGear = myGearString.split(", ");
            for (String gear : myGear) {
                currentMember.addToMyGear(gear);
            }
            List<String> unrentableGear = currentMember.registerCommitted(trip, gearRoom);
            if (unrentableGear.isEmpty()) {
                JOptionPane.showMessageDialog(TripPanel.this,
                        "Congratulations, you have been registered as committed on the trip\n");
            } else {
                JOptionPane.showMessageDialog(back,
                        "Congratulations, you have been registered as committed on the trip\n"
                                + "However, you must arrange for the following gear yourself: \n"
                                + String.join(", ", unrentableGear));
            }
            updateLabels();
            updateUI();
        }

    }

    // EFFECTS: Handles going button press
    private class GoingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("What is the name of the member you would like to add to going");
            trip.addToGoing(new Member(name));
            updateLabels();
            updateUI();
        }

    }

}
