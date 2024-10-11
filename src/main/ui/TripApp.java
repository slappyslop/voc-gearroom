package ui;

import model.Gear;
import model.GearRoom;
import model.Member;
import model.Trip;
import model.TripAgenda;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TODO: refactor and remove replication
//TODO: View GearRoom

/*
 * Runs Trip management
 * Very inspired by TellerApp
 */
public class TripApp {
    private Scanner input;
    private TripAgenda tripAgenda;
    private GearRoom gearRoom;
    Member currentMember;
    List<String> glski = new ArrayList<>();
    List<String> glcamp = new ArrayList<>();
    List<String> glhike = new ArrayList<>();

    public TripApp() {
        runTripApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTripApp() {
        boolean keepGoing = true;
        String command = null;

        init();

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

    //EFFECTS: displays a screen where users get to choose under what role they want to log in
    private void displayLeaderOrMember() {
        System.out.print("\nHello " + currentMember.getName());
        System.out.println("\nWould you like to log in as a leader, member, or gearmaster?");
        System.out.println("\tl -> Leader");
        System.out.println("\tm -> Member");
        System.out.println("\tg -> Gearmaster");
        System.out.println("\tq -> quit");

    }

    //EFFECTS: Provides a main menu for use by trip leaders
    private void mainMenuLeader() {
        String command;

        System.out.println("\nWelcome Trip Leader " + currentMember.getName() + "!");
        System.out.println("Would you like to:");
        System.out.println("\tc -> create a trip");
        System.out.println("\tv -> view a list of trips");

        command = input.nextLine();
        if (command.equals("c")) {
            createATrip();
        } else if (command.equals("v")) {
            viewTrips();
        }
    }

    //EFFECTS: Provides a main memu for use by members
    private void mainMenuMember() {
        String command;

        System.out.println("\nWelcome Club Member " + currentMember.getName() + "!");
        System.out.println("Would you like to:");
        System.out.println("\tv -> view a list of trips");
        System.out.println("\tq -> log out ");
        command = input.nextLine().toLowerCase();
        if (command.equals("v")) {
            viewTrips();
        } else if (command.equals("q")) {
            return;
        }
    }

    //EFFECTS: Redirects gearmasters to the addgear screen
    private void mainMenuGearMaster() {
        System.out.println("Welcome Gearmaster " + currentMember.getName());
        while (true) {
            System.out.println("What is the name of the gear you would like to add");
            String name = input.nextLine();
            Gear currentGear = new Gear(name);
            gearRoom.addGear(currentGear);
            System.out.println("Gear:" + currentGear.getName()
                    + "has been successfully added, would you like to add any more gear? (y/n)");
            String command = input.nextLine();
            if (command.equals("y")) {
                continue;
            } else {
                break;
            }
        }

    }

    //MODIFIES: this, currentMember
    //EFFECTS: Creates an instance of a logged in member
    private void logIn() {
        System.out.println("What is your Name? ");
        String name = input.nextLine();
        currentMember = new Member(name);
    }

    //MODIFIES: currentMember
    //EFFECTS: Handles user input for the login screen
    private void processLogIn(String command) {
        if (command.equals("l")) {
            currentMember.setLogInState("leader");
            mainMenuLeader();
        } else if (command.equals("m")) {
            currentMember.setLogInState("member");
            mainMenuMember();
        } else if (command.equals("g")) {
            mainMenuGearMaster();
        } else {
            System.out.println("This is not a valid command, please try again:");
        }

    }

    //MODIFIES: this, currentTrip
    //EFFECTS: Creates a trip by asking the user some questions and adds it to the trip agenda
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
        currentTrip = new Trip(gearRoom, gl);
        modifyATrip(currentTrip, name);
        tripAgenda.addTrip(currentTrip);
        System.out.println("Thank you for creating a trip! \n Your trip has now been added to the agenda");
        currentTrip.addToGoing(currentMember);
        System.out.println("Press enter to return to login menu");
        input.nextLine();
    }

    //EFFECTS: Given a trip and its name, allows you to modify an existing trip on the trip agenda
    private void modifyATrip(Trip currentTrip, String name) {
        currentTrip.setName(name);
        System.out.println("\nWhat day will this trip start? (integer)");
        currentTrip.setStartDate(input.nextInt());
        input.nextLine();
        System.out.println("\nWhat day will this trip end (integer >= start)");
        currentTrip.setEndDate(input.nextInt());
        input.nextLine();
    }


    private void viewTrips() {
        List<Trip> trips = tripAgenda.getTrips();
        System.out.println("\nTrip Agenda:");
        for (Trip t : trips) {
            System.out.println((trips.indexOf(t) + 1) + ". " + t.getName() + "\t\t\tdays: " + t.getStartDate() + "-"
                    + t.getEndDate());
        }
        System.out
                .println("If you would like to view a trip in detail, type its entry number now. (press 0 to log out)");
        int command = input.nextInt();
        input.nextLine();
        if (command == 0) {
            return;
        } else {
            viewTrip(command);
        }

    }

    private void interest(Trip t) {
        currentMember.registerInterested(t);
        System.out.println("\nThank you for registering as Interested!");
        System.out.println("Press enter to return to the trips menu");
        input.nextLine();
        viewTrips();

    }

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
        if (currentMember.registerCommitted(t)) {
            System.out.println("Congratulations, you have been registered as committed on the trip");
            System.out.println("Gear in the clubroom has been reserved for you.\n");
        } else {
            System.out.println("You have been registered as committed on the trip,");
            System.out.println("but there isn't enough gear in the clubroom. Please talk to a quartermaster in person");
        }
        System.out.println("Press enter to return to the trips menu");
        input.nextLine();
        viewTrips();

    }

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

    private void init() {
        input = new Scanner(System.in);
        tripAgenda = new TripAgenda();
        gearRoom = new GearRoom();
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

    private void initTrips() {
        Trip trip1 = new Trip(gearRoom, glski);
        trip1.setName("Backcountry skiing at Elfin Lakes");
        trip1.setStartDate(1);
        trip1.setEndDate(2);

        Trip trip2 = new Trip(gearRoom, glhike);
        trip2.setName("Hiking at Hollyburn");
        trip2.setStartDate(2);
        trip2.setEndDate(5);

        Trip trip3 = new Trip(gearRoom, glcamp);
        trip3.setName("Camping in the Fraser Valley");
        trip3.setStartDate(3);
        trip3.setEndDate(4);

        tripAgenda.addTrip(trip1);
        tripAgenda.addTrip(trip2);
        tripAgenda.addTrip(trip3);
    }

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
