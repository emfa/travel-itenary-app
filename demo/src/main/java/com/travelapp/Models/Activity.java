package com.travelapp.Models;

import java.util.ArrayList;
import java.util.List;

import com.travelapp.Models.Passengers.Passenger;

/**
 * A class that represents an activity available for passengers to participate
 * in.
 * An activity has a name, description, cost, and capacity.
 */
public class Activity {
    private String name;
    private String description;
    private Double cost;
    private Integer capacity;

    private List<Destination> availableDestinations;
    private List<Passenger> members;

    /**
     * Constructor for creating an Activity.
     *
     * @param name        The name of the activity.
     * @param description The description of the activity.
     * @param cost        The cost of participating in the activity.
     * @param capacity    The maximum capacity of participants in the activity.
     */
    public Activity(String name, String description, Double cost, Integer capacity) {
        this.name = name;
        this.description = description;
        this.cost = cost < 0 ? 0 : cost;
        this.capacity = capacity < 0 ? 0 : capacity;
        this.members = new ArrayList<>();
        this.availableDestinations = new ArrayList<>();
    }

    /**
     * Get the name of the activity.
     *
     * @return The name of the activity.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the description of the activity.
     *
     * @return The description of the activity.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the cost of participating in the activity.
     *
     * @return The cost of participating in the activity.
     */
    public Double getCost() {
        return cost;
    }

    /**
     * Get the maximum capacity of participants in the activity.
     *
     * @return The maximum capacity of participants in the activity.
     */

    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Get the list of passengers who have signed up for the activity.
     *
     * @return The list of passengers who have signed up for the activity.
     */
    public List<Passenger> getMembers() {
        return members;
    }

    /**
     * Sign up a passenger for the activity.
     *
     * @param passenger The passenger who wants to sign up.
     * @return True if the sign-up is successful, false otherwise.
     */
    public boolean signUp(Passenger passenger) {
        if (members.size() < capacity) {
            if (passenger.calculate(getCost())) {
                members.add(passenger);
                passenger.addActivity(this);
                return true;
            } else {
                System.out.println("Insufficient Balance");
            }
        } else {
            System.out.println("Maximum Activity Capacity Reached");
        }
        return false;
    }

    /**
     * Add a destination to the list of available destinations for the activity.
     *
     * @param destination The destination to be added.
     */
    public void addDestination(Destination destination) {
        if (!availableDestinations.contains(destination)) {
            this.availableDestinations.add(destination);
        }
    }

    /**
     * Print details about the activity, including its current availability.
     */
    public void printActivityDetails() {
        int availableSpaces = capacity - members.size();

        System.out.println("ACTIVITY DETAILS: ");
        System.out.println("Activity: " + name);
        System.out.println("Description: " + description);
        System.out.println("Cost: " + cost);
        System.out.println("Capacity: " + capacity);
        System.out.println("Available Spaces: " + availableSpaces);
        System.out.println();

    }

    /**
     * Get the list of available destinations for the activity.
     *
     * @return The list of available destinations for the activity.
     */
    public List<Destination> getAvailableDestinations() {
        return availableDestinations;
    }

    /**
     * Get a string representation of the activity.
     *
     * @return A string representation of the activity.
     */
    @Override
    public String toString() {
        return "Activity [name=" + name + ", description=" + description + ", cost=" + cost + ", capacity=" + capacity
                + "]";
    }

}
