package com.travelapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.travelapp.Models.Activity;
import com.travelapp.Models.Destination;
import com.travelapp.Models.Package;
import com.travelapp.Models.Passengers.GoldPassenger;
import com.travelapp.Models.Passengers.PremiumPassenger;
import com.travelapp.Models.Passengers.StandardPassenger;

public class PassengerTest {

    @Test
    public void testCreateStandardPassengerWithValidDetails() {
        StandardPassenger passenger = new StandardPassenger("John", 1, 150.0);

        assertNotNull(passenger);
        assertEquals("John", passenger.getName());
        assertEquals(1, passenger.getNumber());
        assertEquals(150.0, passenger.getBalance());
    }

    @Test
    public void testCreateGoldPassengerWithValidDetails() {
        GoldPassenger passenger = new GoldPassenger("Alice", 2, 200.0);

        assertNotNull(passenger);
        assertEquals("Alice", passenger.getName());
        assertEquals(2, passenger.getNumber());
        assertEquals(200.0, passenger.getBalance());
    }

    @Test
    public void testCreatePremiumPassengerWithValidDetails() {
        PremiumPassenger passenger = new PremiumPassenger("Bob", 3);

        assertNotNull(passenger);
        assertEquals("Bob", passenger.getName());
        assertEquals(3, passenger.getNumber());
        assertEquals(0.0, passenger.getBalance());
    }

    @Test
    public void testChoosePackageAndActivityForStandardPassenger() {
        StandardPassenger passenger = new StandardPassenger("John", 1, 150.0);
        Package testPackage = new Package("Test Package", 10);
        Destination destination = new Destination("Test Destination");
        Activity activity = new Activity("Test Activity", "Description", 50.0, 5);
        destination.addActivity(activity);
        testPackage.addDestination(destination);

        boolean chooseResult = passenger.choosePackageAndActivities(testPackage, destination, activity);

        assertTrue(chooseResult);
        assertTrue(passenger.getChosenActivities().contains(activity));
        assertTrue(passenger.getChosenPackages().contains(testPackage));
        assertEquals(100.0, passenger.getBalance());
    }

    @Test
    public void testChoosePackageAndActivityForGoldPassenger() {
        GoldPassenger passenger = new GoldPassenger("Alice", 2, 200.0);
        Package testPackage = new Package("Test Package", 10);
        Destination destination = new Destination("Test Destination");
        Activity activity = new Activity("Test Activity", "Description", 50.0, 5);
        destination.addActivity(activity);
        testPackage.addDestination(destination);

        boolean chooseResult = passenger.choosePackageAndActivities(testPackage, destination, activity);

        assertTrue(chooseResult);
        assertTrue(passenger.getChosenActivities().contains(activity));
        assertTrue(passenger.getChosenPackages().contains(testPackage));
        assertEquals(155.0, passenger.getBalance());
    }

    @Test
    public void testChoosePackageAndActivityForPremiumPassenger() {
        PremiumPassenger passenger = new PremiumPassenger("Bob", 3);
        Package testPackage = new Package("Test Package", 10);
        Destination destination = new Destination("Test Destination");
        Activity activity = new Activity("Test Activity", "Description", 50.0, 5);
        destination.addActivity(activity);
        testPackage.addDestination(destination);

        boolean chooseResult = passenger.choosePackageAndActivities(testPackage, destination, activity);

        assertTrue(chooseResult);
        assertTrue(passenger.getChosenActivities().contains(activity));
        assertTrue(passenger.getChosenPackages().contains(testPackage));
        assertEquals(0.0, passenger.getBalance(), 0.01);
    }

    @Test
    public void testChooseNonExistentPackageForPassenger() {
        StandardPassenger passenger = new StandardPassenger("John", 1, 150.0);
        Package nonExistentPackage = new Package("Nonexistent Package", 10);
        Destination destination = new Destination("Test Destination");
        Activity activity = new Activity("Test Activity", "Description", 50.0, 5);
        destination.addActivity(activity);

        boolean chooseResult = passenger.choosePackageAndActivities(nonExistentPackage, destination, activity);

        assertFalse(chooseResult);
        assertFalse(passenger.getChosenActivities().contains(activity));
        assertFalse(passenger.getChosenPackages().contains(nonExistentPackage));
        assertEquals(150.0, passenger.getBalance());
    }

    @Test
    public void testChooseNonExistentDestinationForPassenger() {
        StandardPassenger passenger = new StandardPassenger("John", 1, 150.0);
        Package testPackage = new Package("Test Package", 10);
        Destination nonExistentDestination = new Destination("Nonexistent Destination");
        Activity activity = new Activity("Test Activity", "Description", 50.0, 5);

        boolean chooseResult = passenger.choosePackageAndActivities(testPackage, nonExistentDestination, activity);

        assertFalse(chooseResult);
        assertFalse(passenger.getChosenActivities().contains(activity));
        assertFalse(passenger.getChosenPackages().contains(testPackage));
        assertEquals(150.0, passenger.getBalance());
    }

    @Test
    public void testChooseNonExistentActivityForPassenger() {
        StandardPassenger passenger = new StandardPassenger("John", 1, 150.0);
        Package testPackage = new Package("Test Package", 10);
        Destination destination = new Destination("Test Destination");
        Activity nonExistentActivity = new Activity("Nonexistent Activity", "Description", 50.0, 5);
        testPackage.addDestination(destination);

        boolean chooseResult = passenger.choosePackageAndActivities(testPackage, destination, nonExistentActivity);

        assertFalse(chooseResult);
        assertFalse(passenger.getChosenActivities().contains(nonExistentActivity));
        assertFalse(passenger.getChosenPackages().contains(testPackage));
        assertEquals(150.0, passenger.getBalance());
    }

    @Test
    public void testChoosePackageWithNoAvailableActivitiesForPassengerInsufficientBalance() {
        StandardPassenger passenger = new StandardPassenger("John", 1, 150.0);
        Package testPackage = new Package("Test Package", 10);
        Destination destination = new Destination("Test Destination");
        Activity activity1 = new Activity("Activity 1", "Description", 50.0, 1);
        Activity activity2 = new Activity("Activity 2", "Description", 60.0, 1);
        destination.addActivity(activity1);
        destination.addActivity(activity2);
        testPackage.addDestination(destination);
        passenger.choosePackageAndActivities(testPackage, destination, activity1);
        passenger.choosePackageAndActivities(testPackage, destination, activity2);

        Activity additionalActivity = new Activity("Additional Activity", "Description", 70.0, 1);
        destination.addActivity(additionalActivity);

        boolean chooseResult = passenger.choosePackageAndActivities(testPackage, destination, additionalActivity);

        assertFalse(chooseResult);
        assertFalse(passenger.getChosenActivities().contains(additionalActivity));
        assertEquals(40.0, passenger.getBalance(), 0.01);
    }

    @Test
    public void testCheckSignedUpActivitiesWithMultipleActivities() {
        StandardPassenger passenger = new StandardPassenger("John", 1, 150.0);
        Package testPackage = new Package("Test Package", 10);
        Destination destination = new Destination("Test Destination");
        Activity activity1 = new Activity("Activity 1", "Description", 50.0, 5);
        Activity activity2 = new Activity("Activity 2", "Description", 60.0, 5);
        destination.addActivity(activity1);
        destination.addActivity(activity2);
        testPackage.addDestination(destination);
        passenger.choosePackageAndActivities(testPackage, destination, activity1);
        passenger.choosePackageAndActivities(testPackage, destination, activity2);

        assertTrue(passenger.getChosenActivities().contains(activity1));
        assertTrue(passenger.getChosenActivities().contains(activity2));
        assertFalse(passenger.getChosenActivities().contains(new Activity("Nonexistent", "Description", 0.0, 0)));
    }

    @Test
    public void testCheckSignedUpActivitiesWithNoActivities() {
        StandardPassenger passenger = new StandardPassenger("John", 1, 150.0);

        assertFalse(passenger.getChosenActivities().contains(new Activity("Nonexistent", "Description", 0.0, 0)));
    }

    @Test
    public void testCheckBalanceUpdateForStandardPassenger() {
        StandardPassenger passenger = new StandardPassenger("John", 1, 150.0);
        Package testPackage = new Package("Test Package", 10);
        Destination destination = new Destination("Test Destination");
        Activity activity = new Activity("Test Activity", "Description", 50.0, 5);
        destination.addActivity(activity);
        testPackage.addDestination(destination);
        double originalBalance = passenger.getBalance();
        passenger.choosePackageAndActivities(testPackage, destination, activity);

        assertEquals(originalBalance - 50.0, passenger.getBalance());
    }

    @Test
    public void testCheckBalanceUpdateForGoldPassenger() {
        GoldPassenger passenger = new GoldPassenger("Alice", 2, 200.0);
        Package testPackage = new Package("Test Package", 10);
        Destination destination = new Destination("Test Destination");
        Activity activity = new Activity("Test Activity", "Description", 50.0, 5);
        destination.addActivity(activity);
        testPackage.addDestination(destination);

        double originalBalance = passenger.getBalance();
        passenger.choosePackageAndActivities(testPackage, destination, activity);

        assertEquals(originalBalance - 45.0, passenger.getBalance());
    }

}
