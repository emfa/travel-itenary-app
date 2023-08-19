package com.travelapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.travelapp.Models.Activity;
import com.travelapp.Models.Destination;
import com.travelapp.Models.Package;
import com.travelapp.Models.Passengers.Passenger;
import com.travelapp.Models.Passengers.StandardPassenger;

public class PackageTest {

    @Test
    public void testCreatePackageWithValidNameAndCapacity() {
        Package validPackage = new Package("Valid Package", 10);
        assertNotNull(validPackage);
        assertEquals("Valid Package", validPackage.getName());
        assertEquals(10, validPackage.getMaxPassengerCapacity());
    }

    @Test
    public void testCreatePackageWithEmptyName() {
        Package emptyNamePackage = new Package("", 5);
        assertNotNull(emptyNamePackage);
        assertEquals("Unknown Package", emptyNamePackage.getName());
        assertEquals(5, emptyNamePackage.getMaxPassengerCapacity());
    }

    @Test
    public void testCreatePackageWithNegativeCapacity() {
        Package negativeCapacityPackage = new Package("Negative Capacity Package", -5);
        assertNotNull(negativeCapacityPackage);
        assertEquals("Negative Capacity Package", negativeCapacityPackage.getName());
        assertEquals(0, negativeCapacityPackage.getMaxPassengerCapacity());
    }

    @Test
    public void testCreatePackageWithZeroCapacity() {
        Package zeroCapacityPackage = new Package("Zero Capacity Package", 0);
        assertNotNull(zeroCapacityPackage);
        assertEquals("Zero Capacity Package", zeroCapacityPackage.getName());
        assertEquals(0, zeroCapacityPackage.getMaxPassengerCapacity());
    }

    @Test
    public void testAddValidDestinationToPackage() {
        Package testPackage = new Package("Test Package", 10);
        Destination validDestination = new Destination("Valid Destination");

        boolean addResult = testPackage.addDestination(validDestination);

        assertTrue(addResult);
        assertTrue(testPackage.getItenary().contains(validDestination));
        assertEquals(1, testPackage.getItenary().size());
    }

    @Test
    public void testAddSameDestinationTwiceToPackage() {
        Package testPackage = new Package("Test Package", 10);
        Destination duplicateDestination = new Destination("Duplicate Destination");

        boolean firstAddResult = testPackage.addDestination(duplicateDestination);
        boolean secondAddResult = testPackage.addDestination(duplicateDestination);

        assertTrue(firstAddResult);
        assertFalse(secondAddResult);
        assertTrue(testPackage.getItenary().contains(duplicateDestination));
        assertEquals(1, testPackage.getItenary().size());
    }

    @Test
    public void testAddPassengerToPackage() {
        Package testPackage = new Package("Test Package", 2);
        Passenger passenger = new StandardPassenger("John", 1, 150.0);

        boolean addResult = testPackage.addPassenger(passenger);

        assertTrue(addResult);
        assertTrue(testPackage.getPassengers().contains(passenger));
        assertEquals(1, testPackage.getPassengers().size());
    }

    @Test
    public void testAddPassengerExceedingCapacity() {
        Package testPackage = new Package("Test Package", 1);
        Passenger passenger1 = new StandardPassenger("John", 1, 150.0);
        Passenger passenger2 = new StandardPassenger("Alice", 2, 200.0);

        testPackage.addPassenger(passenger1);
        boolean addResult = testPackage.addPassenger(passenger2);

        assertFalse(addResult);
        assertFalse(testPackage.getPassengers().contains(passenger2));
        assertEquals(1, testPackage.getPassengers().size());
    }

    @Test
    public void testAddSamePassengerTwiceToPackage() {
        Package testPackage = new Package("Test Package", 2);
        Passenger passenger1 = new StandardPassenger("John", 1, 150.0);

        testPackage.addPassenger(passenger1);
        boolean addResult = testPackage.addPassenger(passenger1);

        assertFalse(addResult);
        assertEquals(1, testPackage.getPassengers().size());
    }

    @Test
    public void testAddNullPassengerToPackage() {
        Package testPackage = new Package("Test Package", 2);

        boolean addResult = testPackage.addPassenger(null);

        assertFalse(addResult);
        assertEquals(0, testPackage.getPassengers().size());
    }

    @Test
    public void testShowAvailableActivitiesForPackageWithMultipleDestinations() {
        Package testPackage = new Package("Test Package", 10);
        Destination destination1 = new Destination("Destination 1");
        Destination destination2 = new Destination("Destination 2");
        testPackage.addDestination(destination1);
        testPackage.addDestination(destination2);
        destination1.addActivity(new Activity("Activity 1", "Description", 50.0, 5));
        destination2.addActivity(new Activity("Activity 2", "Description", 60.0, 8));

        List<Activity> availableActivities = testPackage.showAvailableActivities();

        assertEquals(2, availableActivities.size());
    }

    @Test
    public void testShowAvailableActivitiesForPackageWithNoDestinations() {
        Package emptyPackage = new Package("Empty Package", 10);

        List<Activity> availableActivities = emptyPackage.showAvailableActivities();

        assertEquals(0, availableActivities.size());
    }

}
