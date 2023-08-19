package com.travelapp.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a destination where activities can take place.
 * A destination has a name and a list of associated activities.
 */
public class Destination {

    private String name;
    private List<Activity> activities;

    /**
     * Constructor for creating a Destination.
     *
     * @param name The name of the destination.
     */
    public Destination(String name) {
        this.name = name == "" ? "Unknown Destination" : name;
        this.activities = new ArrayList<>();
    }

    /**
     * Get the name of the destination.
     *
     * @return The name of the destination.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the list of activities associated with the destination.
     *
     * @return The list of activities associated with the destination.
     */
    public List<Activity> getActivities() {
        return activities;
    }

    /**
     * Add an activity to the list of activities associated with the destination.
     *
     * @param activity The activity to be added.
     * @return True if the activity is added successfully, false if the activity is
     *         already in the list.
     */
    public boolean addActivity(Activity activity) {
        if (!activities.contains(activity)) {
            activities.add(activity);
            activity.addDestination(this);
            return true;
        }
        return false;
    }

    /**
     * Get a string representation of the destination.
     *
     * @return A string representation of the destination.
     */
    @Override
    public String toString() {
        return "Destination [name=" + name + ", activities=" + activities + "]";
    }

}
