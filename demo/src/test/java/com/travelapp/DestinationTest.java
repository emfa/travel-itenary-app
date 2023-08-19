package com.travelapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.travelapp.Models.Activity;
import com.travelapp.Models.Destination;

public class DestinationTest {

    public void testCreateDestinationWithValidName() {
        Destination validDestination = new Destination("Valid Destination");
        assertNotNull(validDestination);
        assertEquals("Valid Destination", validDestination.getName());
    }

    @Test
    public void testCreateDestinationWithEmptyName() {
        Destination emptyNameDestination = new Destination("");
        assertNotNull(emptyNameDestination);
        assertEquals("Unknown Destination", emptyNameDestination.getName());
    }

    @Test
    public void testAddValidActivityToDestination() {
        Destination testDestination = new Destination("Test Destination");
        Activity validActivity = new Activity("Valid Activity", "Description", 50.0, 5);

        boolean addResult = testDestination.addActivity(validActivity);

        assertTrue(addResult);
        assertTrue(testDestination.getActivities().contains(validActivity));
        assertEquals(1, testDestination.getActivities().size());
    }

    @Test
    public void testAddSameActivityTwiceToDestination() {
        Destination testDestination = new Destination("Test Destination");
        Activity duplicateActivity = new Activity("Duplicate Activity", "Description", 50.0, 5);

        boolean firstAddResult = testDestination.addActivity(duplicateActivity);
        boolean secondAddResult = testDestination.addActivity(duplicateActivity);

        assertTrue(firstAddResult);
        assertFalse(secondAddResult);
        assertTrue(testDestination.getActivities().contains(duplicateActivity));
        assertEquals(1, testDestination.getActivities().size());
    }

    @Test
    public void testGetActivitiesForValidDestination() {
        Destination testDestination = new Destination("Test Destination");
        Activity activity1 = new Activity("Activity 1", "Description", 50.0, 5);
        Activity activity2 = new Activity("Activity 2", "Description", 60.0, 8);
        testDestination.addActivity(activity1);
        testDestination.addActivity(activity2);

        List<Activity> activities = testDestination.getActivities();

        assertEquals(2, activities.size());
        assertTrue(activities.contains(activity1));
        assertTrue(activities.contains(activity2));
    }

    @Test
    public void testGetActivitiesForNonExistentDestination() {
        Destination nonExistentDestination = new Destination("Nonexistent Destination");

        List<Activity> activities = nonExistentDestination.getActivities();

        assertEquals(0, activities.size());
    }

}
