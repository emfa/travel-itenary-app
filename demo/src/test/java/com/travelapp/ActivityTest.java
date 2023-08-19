package com.travelapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.travelapp.Models.Activity;
import com.travelapp.Models.Passengers.GoldPassenger;
import com.travelapp.Models.Passengers.Passenger;
import com.travelapp.Models.Passengers.PremiumPassenger;
import com.travelapp.Models.Passengers.StandardPassenger;

public class ActivityTest {

    @Test
    public void testCreateActivityWithValidDetails() {
        Activity validActivity = new Activity("Valid Activity", "Description", 50.0, 5);
        assertNotNull(validActivity);
        assertEquals("Valid Activity", validActivity.getName());
        assertEquals("Description", validActivity.getDescription());
        assertEquals(50.0, validActivity.getCost());
        assertEquals(5, validActivity.getCapacity());
    }

    @Test
    public void testCreateActivityWithNegativeCost() {
        Activity negativeCostActivity = new Activity("Negative Cost Activity", "Description", -50.0, 5);
        assertNotNull(negativeCostActivity);
        assertEquals("Negative Cost Activity", negativeCostActivity.getName());
        assertEquals(0.0, negativeCostActivity.getCost());
    }

    @Test
    public void testCreateActivityWithNegativeCapacity() {
        Activity negativeCapacityActivity = new Activity("Negative Capacity Activity", "Description", 50.0, -5);
        assertNotNull(negativeCapacityActivity);
        assertEquals("Negative Capacity Activity", negativeCapacityActivity.getName());
        assertEquals(0, negativeCapacityActivity.getCapacity());
    }

    @Test
    public void testSignUpPassengerForActivity() {
        Activity testActivity = new Activity("Test Activity", "Description", 50.0, 5);
        Passenger passenger = new StandardPassenger("John", 1, 150.0);

        boolean signUpResult = testActivity.signUp(passenger);

        assertTrue(signUpResult);
        assertTrue(testActivity.getMembers().contains(passenger));
        assertEquals(1, testActivity.getMembers().size());
    }

    @Test
    public void testSignUpMultiplePassengersForActivityUpToCapacity() {
        Activity testActivity = new Activity("Test Activity", "Description", 3.0, 2);
        Passenger passenger1 = new StandardPassenger("John", 1, 150.0);
        Passenger passenger2 = new StandardPassenger("Alice", 2, 200.0);

        boolean signUpResult1 = testActivity.signUp(passenger1);
        boolean signUpResult2 = testActivity.signUp(passenger2);

        assertTrue(signUpResult1);
        assertTrue(signUpResult2);
        assertTrue(testActivity.getMembers().contains(passenger1));
        assertTrue(testActivity.getMembers().contains(passenger2));
        assertEquals(2, testActivity.getMembers().size());
    }

    @Test
    public void testSignUpPassengerExceedingCapacity() {
        Activity testActivity = new Activity("Test Activity", "Description", 3.0, 1);
        Passenger passenger1 = new StandardPassenger("John", 1, 150.0);
        Passenger passenger2 = new StandardPassenger("Alice", 2, 200.0);

        boolean signUpResult1 = testActivity.signUp(passenger1);
        boolean signUpResult2 = testActivity.signUp(passenger2);

        assertTrue(signUpResult1);
        assertFalse(signUpResult2);
        assertTrue(testActivity.getMembers().contains(passenger1));
        assertFalse(testActivity.getMembers().contains(passenger2));
        assertEquals(1, testActivity.getMembers().size());
    }

    @Test
    public void testSignUpPassengerWithInsufficientBalance() {
        Activity testActivity = new Activity("Test Activity", "Description", 50.0, 1);
        Passenger passenger = new StandardPassenger("John", 1, 30.0);

        boolean signUpResult = testActivity.signUp(passenger);

        assertFalse(signUpResult);
        assertFalse(testActivity.getMembers().contains(passenger));
        assertEquals(0, testActivity.getMembers().size());
    }

    @Test
    public void testSignUpStandardPassengerWithNoDiscount() {
        Activity testActivity = new Activity("Test Activity", "Description", 50.0, 3);
        StandardPassenger passenger = new StandardPassenger("John", 1, 150.0);

        boolean signUpResult = testActivity.signUp(passenger);

        assertTrue(signUpResult);
        assertTrue(testActivity.getMembers().contains(passenger));
        assertEquals(1, testActivity.getMembers().size());
        assertEquals(100.0, passenger.getBalance());
    }

    @Test
    public void testSignUpGoldPassengerWithDiscount() {
        Activity testActivity = new Activity("Test Activity", "Description", 50.0, 3);
        GoldPassenger passenger = new GoldPassenger("John", 1, 150.0);

        boolean signUpResult = testActivity.signUp(passenger);

        assertTrue(signUpResult);
        assertTrue(testActivity.getMembers().contains(passenger));
        assertEquals(1, testActivity.getMembers().size());
        assertEquals(105.0, passenger.getBalance());
    }

    @Test
    public void testSignUpPremiumPassengerForFree() {
        Activity testActivity = new Activity("Test Activity", "Description", 50.0, 3);
        PremiumPassenger passenger = new PremiumPassenger("John", 1);

        boolean signUpResult = testActivity.signUp(passenger);

        assertTrue(signUpResult);
        assertTrue(testActivity.getMembers().contains(passenger));
        assertEquals(1, testActivity.getMembers().size());
        assertEquals(0.0, passenger.getBalance());
    }
}
