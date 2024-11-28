# My Personal Project
## Proposal
My project aims to streamline and digitize some of the trip planning and gear inventory systems of the Varsity Outdoor Club at the University of British Columbia.

The program will model the Varsity Outdoor Club's gear room, and ensure that there is enough gear for every trip, and inform trip leaders if there is not. It will query users on
what gear they are bringing on their own and what they will need from the club in order to better ensure that this works out. It will also inform users if there is enough gear for them when they register. The other functionality of the trip planning software will be to provide efficient solutions for carpools, ensuring that as many people get spots to drivers closest to them.

Ideally trip leaders of the VOC would use this to help them plan a trip, and maybe further in the future these funcitonalities could be integrated into the website. 

This project is of interest to me because I'm an enthusiastic member of the club, and have suffered from both sudden gear shortages and picking suboptimal car spots and the delays that come with them.


## User Stories

1. As a trip leader I would like to add a trip to the list of trips
2. As a viewer I would like to see a list of all trips posted
3. As a trip leader, I would like to add someone to the list of people going on a trip
4. As a member, I would like to add myself to the list of people interested in the trip
5. As a member, I would like to add myself to the list of people committed to the trip
6. As a quartermaster, I would like to add gear to the gear room (list of gear)
7. As a member, I would like to view everybody interested, committed, and going on the trip
8. As member, I would like to see if there is enough unreserved gear in the gearroom on the day of the trip for me
9. As a member, when I am added to a trip, I would like to mark the gear I already have and don't need to rent
10. As a gearmaster, I would like to save the current state of the Gear Room (if i choose)
11. As a user, I would like to save the current state of the trip agenda (if i choose)
12. As a user, I would like to load a Gear Room and Trip Agenda from file (if i choose)

## Instructions for the end user
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by logging in as a leader/member, viewing a specific trip
  and then pressing the register interested or committed (which checks for gear reservations) to add only yourself (Member X) to the trip (Y). 
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by logging in as a LEADER only, viewing a specific trip
  and then pressing the going button, which allows you to add any member (Member X) to the trip Y
- You also have the ability to add trips as a whole to the agenda, which is like adding Y to Z (although the agenda is a trivial class)
- You also have the ability to add Gear (A) to the gear room (B), although B is a trivial class. This gear's availability is checked in the first required action
- You can locate my visual component by opening the app, where the Varsity Outdoor Club's logo is displayed on the login screen
- You can save the state of my application by pressing either the Save Gearrom buttoon when logging in as a gearmaster, or the save Trip agenda button when
  logged in as a member/leader
- You can reload the state of my application by pressing the Load Trip Agenda button (which loads only the gearroom) when logged in as a gearmaster, or the load 
  trip agenda button when logged in as a member/leader (which loads both the gearroom and trip agenda, however the gearroom is inaccessible to users)


Note: There are two major functionalities in the app, only one can be done in a session, so I have 3 combined samples
## Phase 4 Task 2:
Wed Nov 27 18:54:45 PST 2024
gm logged in as gear master
Wed Nov 27 18:54:49 PST 2024
skis added to gearroom
Wed Nov 27 18:54:53 PST 2024
skis2 added to gearroom
Wed Nov 27 18:54:55 PST 2024
skis3 added to gearroom
Wed Nov 27 18:55:01 PST 2024
xki added to gearroom
Wed Nov 27 18:55:05 PST 2024
skis added to gearroom
Wed Nov 27 18:55:07 PST 2024
skis added to gearroom
Wed Nov 27 18:55:57 PST 2024
m logged in as leader
Wed Nov 27 18:55:57 PST 2024
skis added to gearroom
Wed Nov 27 18:55:57 PST 2024
skis added to gearroom
Wed Nov 27 18:55:57 PST 2024
skis added to gearroom
Wed Nov 27 18:55:57 PST 2024
Gear room parsed
Wed Nov 27 18:56:07 PST 2024
m going on ski trip
Wed Nov 27 18:56:07 PST 2024
ski trip added to agenda    
Wed Nov 27 18:57:44 PST 2024
m2 logged in as member
Wed Nov 27 18:57:44 PST 2024
skis added to gearroom
Wed Nov 27 18:57:44 PST 2024
skis added to gearroom
Wed Nov 27 18:57:44 PST 2024
skis added to gearroom
Wed Nov 27 18:57:44 PST 2024
Gear room parsed
Wed Nov 27 18:57:46 PST 2024
skis added to gearroom
Wed Nov 27 18:57:46 PST 2024
skis added to gearroom
Wed Nov 27 18:57:46 PST 2024
skis added to gearroom
Wed Nov 27 18:57:46 PST 2024
Gear room parsed
Wed Nov 27 18:57:46 PST 2024
m going on ski trip
Wed Nov 27 18:57:46 PST 2024
ski trip added to agenda
Wed Nov 27 18:57:46 PST 2024
Trip agenda parsed
Wed Nov 27 18:57:52 PST 2024
m2 committed to ski trip
Wed Nov 27 18:57:52 PST 2024
skis reserved on 1
Wed Nov 27 18:57:52 PST 2024
skis reserved on 2
Wed Nov 27 18:57:52 PST 2024
skis reserved on 3
Wed Nov 27 18:57:52 PST 2024
skis reserved on 4
Wed Nov 27 18:57:52 PST 2024
Checked m2 gear requirements for ski trip


