package ui;

import model.Gear;
import model.GearRoom;
import model.Member;
import model.Trip;
import model.TripAgenda;
import persistence.JsonGearRoomReader;
import persistence.JsonGearRoomWriter;
import persistence.JsonTripAgendaReader;
import persistence.JsonTripAgendaWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Runs Trip management
 * Very inspired by TellerApp
 * https://github.students.cs.ubc.ca/CPSC210/TellerApp
 * Some code taken from 
 * JSONSERIALIZATIONDEMO
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 * 
 */
public class TripApp {
    private static final String GEARROOM_JSON_STORE = "./data/gearroom.json";
    private static final String TRIPAGENDA_JSON_STORE = "./data/tripagenda.json";
    private Scanner input;
    private TripAgenda tripAgenda;
    private GearRoom gearRoom;
    private Member currentMember;
    private List<String> glski;
    private List<String> glcamp;
    private List<String> glhike;
    private JsonGearRoomWriter jsonGearRoomWriter;
    private JsonGearRoomReader jsonGearRoomReader;
    private JsonTripAgendaReader jsonTripAgendaReader;
    private JsonTripAgendaWriter jsonTripAgendaWriter;

    // EFFECTS: Creates an instance of the UI
    public TripApp() {
        glski = new ArrayList<>();
        glcamp = new ArrayList<>();
        glhike = new ArrayList<>();
        init();
        runTripApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTripApp() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            logIn();
            displayLeaderOrMember();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processLogIn(command);
            }

        }

        System.out.println("Goodbye !");

    }

    // EFFECTS: displays a screen where users get to choose under what role they
    // want to log in
    private void displayLeaderOrMember() {
        System.out.print("\nHello " + currentMember.getName());
        System.out.println("\nWould you like to log in as a leader, member, or gearmaster?");
        System.out.println("\tl -> Leader");
        System.out.println("\tm -> Member");
        System.out.println("\tg -> Gearmaster");
        System.out.println("\tq -> quit");

    }

    // EFFECTS: Provides a main menu for use by trip leaders
    private void mainMenuLeader() {
        String command;

        System.out.println("\nWelcome Trip Leader " + currentMember.getName() + "!");
        System.out.println("Would you like to:");
        System.out.println("\tc -> create a trip");
        System.out.println("\tv -> view a list of trips");
        System.out.println("\ts -> save the current list of trips");
        System.out.println("\tl -> load list of trips from file");
        System.out.println("\tq -> log out");

        command = input.nextLine();
        if (command.equals("c")) {
            createATrip();
        } else if (command.equals("v")) {
            viewTrips();
        } else if (command.equals("s")) {
            saveTripAgenda();
            mainMenuLeader();
        } else if (command.equals("l")) {
            loadTripAgenda();
            mainMenuLeader();
        } else if (command.equals("q")) {
            return;
        }
    }

    // EFFECTS: Provides a main memu for use by members
    private void mainMenuMember() {
        String command;

        System.out.println("\nWelcome Club Member " + currentMember.getName() + "!");
        System.out.println("Would you like to:");
        System.out.println("\tv -> view a list of trips");
        System.out.println("\ts -> save the current list of trips");
        System.out.println("\tl -> load list of trips from file");
        System.out.println("\tq -> log out ");
        command = input.nextLine().toLowerCase();
        if (command.equals("v")) {
            viewTrips();
        } else if (command.equals("q")) {
            return;
        } else if (command.equals("s")) {
            saveTripAgenda();
            mainMenuMember();
        } else if (command.equals("l")) {
            loadTripAgenda();
            mainMenuMember();
        } else if (command.equals("q")) {
            return;
        }
    }

    // EFFECTS: loads the trip agenda
    private void loadTripAgenda() {
        try {
            tripAgenda = jsonTripAgendaReader.read();
            System.out.println("\nLoaded list of trips from " + TRIPAGENDA_JSON_STORE);
        } catch (IOException e) {
            System.out.println("\nUnable to load list of trips from " + TRIPAGENDA_JSON_STORE);
        }
    }

    // EFFECTS: saves the trip agenda
    private void saveTripAgenda() {
        try {
            jsonTripAgendaWriter.open();
            jsonTripAgendaWriter.write(tripAgenda);
            jsonTripAgendaWriter.close();
            System.out.println("\nSaved list of trips to " + TRIPAGENDA_JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to save list of trips to " + TRIPAGENDA_JSON_STORE);
        }
    }

    // EFFECTS: Redirects gearmasters to the addgear screen
    private void displayMainMenuGearMaster() {
        String command;
        System.out.println("\nWelcome Gearmaster " + currentMember.getName());
        System.out.println("Would you like to: ");
        System.out.println("\tv -> View gearroom");
        System.out.println("\tg -> add gear");
        System.out.println("\ts -> save gearRoom to file");
        System.out.println("\tl -> load gearroom from file");
        System.out.println("\tq -> log out");
        command = input.nextLine();
        if (command.equals("v")) {
            viewGearRoom();
        } else if (command.equals("g")) {
            addGearToGearroom();
        } else if (command.equals("s")) {
            saveGearRoom();
        } else if (command.equals("l")) {
            loadGearRoom();
        } else {
            //
        }
    }

    // EFFECTS: loads the gear room
    private void loadGearRoom() {
        try {
            gearRoom = jsonGearRoomReader.read();
            System.out.println("Loaded GearRoom from " + GEARROOM_JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from " + GEARROOM_JSON_STORE);
        }
        displayMainMenuGearMaster();
    }

    // EFFECTS: saves the gear room
    private void saveGearRoom() {
        try {
            jsonGearRoomWriter.open();
            jsonGearRoomWriter.write(gearRoom);
            jsonGearRoomWriter.close();
            System.out.println("Saved Gear Room to " + GEARROOM_JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save Gear Room to " + GEARROOM_JSON_STORE);
        }
        displayMainMenuGearMaster();
    }

    // EFFECTS: Prints out list of gear in gearRoom
    private void viewGearRoom() {
        for (Gear g : gearRoom.getGearRoom()) {
            System.out.print(g.getName() + ",");
        }
        displayMainMenuGearMaster();
    }

    // MODIFIES: gearRoom
    // EFFECTS: Opens interface for gearmaster to add new gear to the gearRoom
    private void addGearToGearroom() {
        while (true) {
            System.out.println("What is the name of the gear you would like to add");
            String name = input.nextLine();
            Gear currentGear = new Gear(name);
            gearRoom.addGear(currentGear);
            System.out.println("Gear: " + currentGear.getName()
                    + " has been successfully added, would you like to add any more gear? (y/n)");
            String command = input.nextLine();
            if (command.equals("y")) {
                continue;
            } else {
                break;
            }
        }
        displayMainMenuGearMaster();
    }

    // MODIFIES: this, currentMember
    // EFFECTS: Creates an instance of a logged in member
    private void logIn() {
        System.out.println("\nWhat is your Name? ");
        String name = input.nextLine();
        currentMember = new Member(name);
    }

    // MODIFIES: currentMember
    // EFFECTS: Handles user input for the login screen
    private void processLogIn(String command) {
        if (command.equals("l")) {
            currentMember.setLogInState("leader");
            mainMenuLeader();
        } else if (command.equals("m")) {
            currentMember.setLogInState("member");
            mainMenuMember();
        } else if (command.equals("g")) {
            displayMainMenuGearMaster();
        } else {
            System.out.println("This is not a valid command, please try again:");
        }

    }

    // MODIFIES: this, currentTrip
    // EFFECTS: Creates a trip by asking the user some questions and adds it to the
    // trip agenda
    private void createATrip() {
        Trip currentTrip;

        System.out.println("What would you like to name your trip");
        String name = input.nextLine();
        List<String> gl = new ArrayList<String>();
        System.out.println("\nWhat gear is required to go one this trip,\nex: skis, climbing shoes, jacket, etc... ");
        System.out.println("press enter after each entry and \"q\" to finish");
        while (true) {
            String gearName = input.nextLine();
            if (gearName.equals("q")) {
                break;
            } else {
                gl.add(gearName.toLowerCase());
            }
        }
        currentTrip = new Trip(gl);
        modifyATrip(currentTrip, name);
        tripAgenda.addTrip(currentTrip);
        System.out.println("Thank you for creating a trip! \n Your trip has now been added to the agenda");
        currentTrip.addToGoing(currentMember);
        System.out.println("Press enter to return to login menu");
        input.nextLine();
    }

    // EFFECTS: Given a trip and its name, allows you to modify an existing trip on
    // the trip agenda
    private void modifyATrip(Trip currentTrip, String name) {
        currentTrip.setName(name);
        System.out.println("\nWhat day will this trip start? (integer)");
        currentTrip.setStartDate(input.nextInt());
        input.nextLine();
        System.out.println("\nWhat day will this trip end (integer >= start)");
        currentTrip.setEndDate(input.nextInt());
        input.nextLine();
    }

    // EFFECTS: Displays all posted trips in order of adding
    private void viewTrips() {
        List<Trip> trips = tripAgenda.getTrips();
        System.out.println("\nTrip Agenda:");
        for (Trip t : trips) {
            System.out.println((trips.indexOf(t) + 1) + ". " + t.getName() + "\t\t\tdays: " + t.getStartDate() + "-"
                    + t.getEndDate());
        }
        System.out
                .println("If you would like to view a trip in detail, type its entry number now. (press 0 to go back)");
        int command = input.nextInt();
        input.nextLine();
        if (command == 0) {
            if (currentMember.getLogInState().equals("leader")) {
                mainMenuLeader();
            } else {
                mainMenuMember();
            }
        } else {
            viewTrip(command);
        }

    }

    // MODIFIES: t, currentMember
    // EFFECTS: registers currentMember's interest in t
    private void interest(Trip t) {
        currentMember.registerInterested(t);
        System.out.println("\nThank you for registering as Interested!");
        System.out.println("Press enter to return to the trips menu");
        input.nextLine();
        viewTrips();

    }

    // MODIFIES: t, currentMember, gearRoom
    // EFFECTS: registers currentMember's commitment to trip t
    // also reserves gear where possible for currentMember
    private void commit(Trip t) {
        System.out.println("\nWhat Gear do you currently own? (press q when you are done)");
        for (String s : t.getGearList()) {
            System.out.println(s + ",");
        }
        while (true) {
            String gearName = input.nextLine();
            if (gearName.equals("q")) {
                break;
            } else {
                currentMember.addToMyGear(gearName);
            }
        }
        List<String> unrentableGear = currentMember.registerCommitted(t, gearRoom);
        System.out.println("Congratulations, you have been registered as committed on the trip");
        if (unrentableGear.isEmpty()) {
            System.out.println("All required gear is in the club room and reserved for you\n");
        } else {
            System.out.println("However, You must arrange for the following gear yourself:");
            System.out.println(unrentableGear);
        }
        System.out.println("Press enter to return to the trips menu");
        input.nextLine();
        viewTrips();

    }

    // EFFECTS: views a specific trip in detail and allows user to interact with it
    private void viewTrip(int c) {
        String command;
        Trip currentTrip = tripAgenda.getTrips().get(c - 1);

        displayTrip(currentTrip);
        command = input.nextLine();

        if (command.equals("i")) {
            interest(currentTrip);
        } else if (command.equals("c")) {
            commit(currentTrip);
        } else if (command.equals("g") && currentMember.getLogInState().equals("leader")) {
            going(currentTrip);
            viewTrips();
        } else if (command.equals("q")) {
            viewTrips();
        }
    }

    // EFFECTS: displays a specific trip in detail
    private void displayTrip(Trip currentTrip) {
        System.out.println("\n" + currentTrip.getName());
        System.out.println("Start Date: " + currentTrip.getStartDate());
        System.out.println("End Date: " + currentTrip.getEndDate());
        System.out.print("Going: ");
        for (Member m : currentTrip.getGoing()) {
            System.out.print(m.getName() + ", ");
        }
        System.out.print("\nCommitted: ");
        for (Member m : currentTrip.getCommitted()) {
            System.out.print(m.getName() + ", ");
        }
        System.out.print("\nInterested: ");
        for (Member m : currentTrip.getInterested()) {
            System.out.print(m.getName() + ", ");
        }
        System.out.println("\nWhat would you like to do? :");
        System.out.println("i -> Register as Interested");
        System.out.println("c -> Register as Committed");
        System.out.println("q -> return to view trips ");
        if (currentMember.getLogInState().equals("leader")) {
            System.out.println("g -> add members to going");
        }

    }

    // MODIFIES: t, selectedMember
    // EFFECTS: allows a trip leader to select a member as going on trip t
    private void going(Trip t) {
        while (true) {
            Member selectedMember = null;
            System.out.println("What is the member (from committed) would you like to add to going?");
            String name = input.nextLine();
            for (Member m : t.getCommitted()) {
                if (m.getName().equals(name)) {
                    selectedMember = m;
                }
            }
            if (selectedMember == null) {
                System.out.println("Sorry, there was no such member");
            } else {
                t.addToGoing(selectedMember);
                System.out.println("Member " + selectedMember.getName() + " has been successfully added to going");
            }
            System.out.println("Would you like to add any more members? (y/n)");
            String command = input.nextLine();
            if (command.equals("y")) {
                continue;
            } else {
                break;
            }

        }

    }

    // MODIFIES: this, tripAgenda, gearRoom, input
    // EFFECTS: initializes required objects and examples
    private void init() {
        input = new Scanner(System.in);
        tripAgenda = new TripAgenda();
        gearRoom = new GearRoom();
        jsonGearRoomWriter = new JsonGearRoomWriter(GEARROOM_JSON_STORE);
        jsonGearRoomReader = new JsonGearRoomReader(GEARROOM_JSON_STORE);
        jsonTripAgendaWriter = new JsonTripAgendaWriter(TRIPAGENDA_JSON_STORE);
        jsonTripAgendaReader = new JsonTripAgendaReader(TRIPAGENDA_JSON_STORE, gearRoom);
        glski.add("skis");
        glski.add("jacket");
        glski.add("boots");
        glhike.add("boots");
        glhike.add("poles");
        glhike.add("fleece");
        glcamp.add("tent");
        glcamp.add("sleeping bag");
        glcamp.add("jacket");
        glcamp.add("stove");
        initTrips();
        initGear();
    }

    // MODIFIES: tripAgenda
    // EFFECTS: initializes example trips
    private void initTrips() {
        Trip trip1 = new Trip(glski);
        trip1.setName("Backcountry skiing at Elfin Lakes");
        trip1.setStartDate(1);
        trip1.setEndDate(2);

        Trip trip2 = new Trip(glhike);
        trip2.setName("Hiking at Hollyburn");
        trip2.setStartDate(2);
        trip2.setEndDate(5);

        Trip trip3 = new Trip(glcamp);
        trip3.setName("Camping in the Fraser Valley");
        trip3.setStartDate(3);
        trip3.setEndDate(4);

        tripAgenda.addTrip(trip1);
        tripAgenda.addTrip(trip2);
        tripAgenda.addTrip(trip3);
    }

    // MODIFIES: gearRoom
    // EFFECTS: initializes basic gearRoom
    private void initGear() {
        gearRoom.addGear(new Gear("skis"));
        gearRoom.addGear(new Gear("skis"));
        gearRoom.addGear(new Gear("skis"));
        gearRoom.addGear(new Gear("skis"));
        gearRoom.addGear(new Gear("boots"));
        gearRoom.addGear(new Gear("boots"));
        gearRoom.addGear(new Gear("boots"));
        gearRoom.addGear(new Gear("boots"));
        gearRoom.addGear(new Gear("boots"));
        gearRoom.addGear(new Gear("boots"));
        gearRoom.addGear(new Gear("tent"));
        gearRoom.addGear(new Gear("tent"));
        gearRoom.addGear(new Gear("poles"));
        gearRoom.addGear(new Gear("poles"));
        gearRoom.addGear(new Gear("poles"));
        gearRoom.addGear(new Gear("poles"));
        gearRoom.addGear(new Gear("poles"));
    }

}
