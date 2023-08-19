package com.travelapp;

import java.util.ArrayList;
import java.util.List;

import com.travelapp.Models.Activity;
import com.travelapp.Models.Destination;
import com.travelapp.Models.Package;
import com.travelapp.Models.Passengers.GoldPassenger;
import com.travelapp.Models.Passengers.Passenger;
import com.travelapp.Models.Passengers.PremiumPassenger;
import com.travelapp.Models.Passengers.StandardPassenger;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws Exception {

        List<Passenger> passengersList = new ArrayList<>();

        Activity activity1 = new Activity("Hiking", "Enjoy a hike in the mountains", 50.0, 10);
        Activity activity2 = new Activity("Snorkeling", "Explore underwater life", 70.0, 8);
        Activity activity3 = new Activity("Surfing", "Surf on high rising waves of the Ocean", 50.0, 5);
        Activity activity4 = new Activity("City Tour", "Explore the local landmarks", 30.0, 15);
        Activity activity5 = new Activity("Kayaking", "Paddle along the coastline", 60.0, 10);
        Activity activity6 = new Activity("Cultural Workshop", "Learn traditional arts and crafts", 25.0, 20);
        Activity activity7 = new Activity("Beach Volleyball", "Have fun playing volleyball on the beach", 15.0, 20);

        Destination destination1 = new Destination("Tropical Island");
        Destination destination2 = new Destination("Historic City");
        Destination destination3 = new Destination("Mountain Retreat");
        Destination destination4 = new Destination("Coastal Village");

        Package package1 = new Package("City Exploration", 50);
        Package package2 = new Package("Nature Retreat", 70);
        Package package3 = new Package("Island Adventure", 60);

        destination1.addActivity(activity2);
        destination1.addActivity(activity3);
        destination1.addActivity(activity7);

        destination2.addActivity(activity4);
        destination2.addActivity(activity6);

        destination3.addActivity(activity1);

        destination4.addActivity(activity5);

        package1.addDestination(destination2);
        package2.addDestination(destination4);
        package2.addDestination(destination3);
        package3.addDestination(destination1);

        System.out.println("---------------------------------------------------------------------------");
        package1.printItinerary();
        System.out.println("---------------------------------------------------------------------------");
        package2.printItinerary();
        System.out.println("---------------------------------------------------------------------------");
        package3.printItinerary();
        System.out.println("---------------------------------------------------------------------------");

        StandardPassenger standardPassenger1 = new StandardPassenger("John", 1, 150.0);
        passengersList.add(standardPassenger1);
        GoldPassenger goldPassenger1 = new GoldPassenger("Alice", 2, 300.0);
        passengersList.add(goldPassenger1);
        PremiumPassenger premiumPassenger1 = new PremiumPassenger("Bob", 3);
        passengersList.add(premiumPassenger1);

        StandardPassenger standardPassenger2 = new StandardPassenger("Emily", 4, 150.0);
        passengersList.add(standardPassenger2);
        GoldPassenger goldPassenger2 = new GoldPassenger("David", 5, 250.0);
        passengersList.add(goldPassenger2);
        PremiumPassenger premiumPassenger2 = new PremiumPassenger("Sophia", 6);
        passengersList.add(premiumPassenger2);

        StandardPassenger standardPassenger3 = new StandardPassenger("Michael", 7, 180.0);
        passengersList.add(standardPassenger3);
        GoldPassenger goldPassenger3 = new GoldPassenger("Olivia", 8, 280.0);
        passengersList.add(goldPassenger3);
        PremiumPassenger premiumPassenger3 = new PremiumPassenger("Daniel", 9);
        passengersList.add(premiumPassenger3);

        StandardPassenger standardPassenger4 = new StandardPassenger("Ava", 10, 220.0);
        passengersList.add(standardPassenger4);
        GoldPassenger goldPassenger4 = new GoldPassenger("William", 11, 320.0);
        passengersList.add(goldPassenger4);
        PremiumPassenger premiumPassenger4 = new PremiumPassenger("Emma", 12);
        passengersList.add(premiumPassenger4);

        standardPassenger1.choosePackageAndActivities(package1, destination2, activity4);
        standardPassenger1.choosePackageAndActivities(package1, destination2, activity6);

        standardPassenger2.choosePackageAndActivities(package1, destination2, activity6);
        standardPassenger2.choosePackageAndActivities(package2, destination4, activity5);
        standardPassenger2.choosePackageAndActivities(package3, destination1, activity3);

        goldPassenger1.choosePackageAndActivities(package1, destination2, activity4);
        goldPassenger1.choosePackageAndActivities(package2, destination3, activity1);
        goldPassenger2.choosePackageAndActivities(package2, destination4, activity5);
        goldPassenger2.choosePackageAndActivities(package3, destination1, activity2);

        premiumPassenger1.choosePackageAndActivities(package1, destination2, activity4);
        premiumPassenger2.choosePackageAndActivities(package1, destination2, activity4);
        premiumPassenger1.choosePackageAndActivities(package1, destination2, activity6);
        premiumPassenger2.choosePackageAndActivities(package3, destination1, activity2);

        standardPassenger3.choosePackageAndActivities(package1, destination2, activity4);
        standardPassenger3.choosePackageAndActivities(package1, destination2, activity6);

        standardPassenger4.choosePackageAndActivities(package1, destination2, activity6);
        standardPassenger4.choosePackageAndActivities(package2, destination4, activity5);
        standardPassenger4.choosePackageAndActivities(package3, destination1, activity7);

        goldPassenger3.choosePackageAndActivities(package1, destination2, activity4);
        goldPassenger3.choosePackageAndActivities(package2, destination3, activity1);
        goldPassenger4.choosePackageAndActivities(package2, destination4, activity5);
        goldPassenger4.choosePackageAndActivities(package3, destination1, activity2);

        premiumPassenger3.choosePackageAndActivities(package1, destination2, activity4);
        premiumPassenger4.choosePackageAndActivities(package1, destination2, activity4);
        premiumPassenger3.choosePackageAndActivities(package1, destination2, activity6);
        premiumPassenger4.choosePackageAndActivities(package3, destination1, activity2);

        System.out.println();
        System.out.println();

        System.out.println(
                "-------------------------------------Package Passengers LIST----------------------------------------");
        System.out.println(
                "------------------------------------xxxxxxxxxxxxxxxxx---------------------------------------");
        System.out.println(
                "------------------------------------xxxxxxxxxxxxxxxxx---------------------------------------");
        package1.printPassengerList();
        System.out.println("---------------------------------------------------------------------------");
        package2.printPassengerList();
        System.out.println("---------------------------------------------------------------------------");
        package3.printPassengerList();
        System.out.println();
        System.out.println();

        System.out.println(
                "-------------------------------------PASSENGERS Details----------------------------------------");
        System.out.println(
                "------------------------------------xxxxxxxxxxxxxxxxx---------------------------------------");
        System.out.println(
                "------------------------------------xxxxxxxxxxxxxxxxx---------------------------------------");
        for (Passenger person : passengersList) {
            person.printPassengerDetails();
            System.out.println();
        }

        System.out.println(
                "------------------------ Display Available Activities for a Package 1 -----------------------------");
        for (Activity activity : package1.showAvailableActivities()) {
            activity.printActivityDetails();
        }

        System.out.println(
                "------------------------ Display Available Activities for a Package 2 -----------------------------");
        for (Activity activity : package2.showAvailableActivities()) {
            activity.printActivityDetails();
        }

        System.out.println(
                "------------------------ Display Available Activities for a Package 3 -----------------------------");
        for (Activity activity : package3.showAvailableActivities()) {
            activity.printActivityDetails();
        }

    }

}
