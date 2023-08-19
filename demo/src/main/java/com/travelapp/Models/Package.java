package com.travelapp.Models;

import java.util.ArrayList;
import java.util.List;

import com.travelapp.Models.Passengers.Passenger;

/**
 * A class that represents a travel package containing destinations and
 * activities.
 * A package has a name, maximum passenger capacity, itinerary, and list of
 * passengers.
 */
public class Package {
    private String name;
    private Integer maxPassengerCapacity;
    private List<Destination> itenary;
    private List<Passenger> passengers;

    /**
     * Constructor for creating a Package.
     *
     * @param name     The name of the package.
     * @param capacity The maximum passenger capacity of the package.
     */
    public Package(String name, Integer capacity) {
        this.name = name == "" ? "Unknown Package" : name;
        this.maxPassengerCapacity = capacity < 0 ? 0 : capacity;
        this.itenary = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    /**
     * Get the name of the package.
     *
     * @return The name of the package.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the list of destinations in the package's itinerary.
     *
     * @return The list of destinations in the itinerary.
     */
    public List<Destination> getItenary() {
        return itenary;
    }

    /**
     * Get the maximum passenger capacity of the package.
     *
     * @return The maximum passenger capacity of the package.
     */
    public Integer getMaxPassengerCapacity() {
        return maxPassengerCapacity;
    }

    /**
     * Get the list of passengers who have booked the package.
     *
     * @return The list of passengers who have booked the package.
     */
    public List<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * Add a destination to the package's itinerary.
     *
     * @param destination The destination to be added.
     * @return True if the destination is added successfully, false if the
     *         destination is already in the itinerary.
     */
    public boolean addDestination(Destination destination) {
        if (!itenary.contains(destination)) {
            if (destination.getName() != "") {
                itenary.add(destination);
                return true;
            }
        }
        return false;
    }

    /**
     * Add a passenger to the list of passengers who have booked the package.
     *
     * @param passenger The passenger to be added.
     * @return True if the passenger is added successfully, false if the passenger
     *         is already in the list.
     */
    public boolean addPassenger(Passenger passenger) {
        if (passenger != null) {
            if (passengers.size() < maxPassengerCapacity) {
                if (!passengers.contains(passenger)) {
                    passengers.add(passenger);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Print the itinerary of the package along with details of activities and
     * destinations.
     */
    public void printItinerary() {
        System.out.println("Travel Package: " + name);
        System.out.println();
        System.out.println("ITENARY : ");
        for (Destination destination : itenary) {
            System.out.println("Destination: " + destination.getName());
            for (Activity activity : destination.getActivities()) {
                System.out.println("Activity: " + activity.getName());
                System.out.println("Description: " + activity.getDescription());
                System.out.println("Cost: " + activity.getCost());
                System.out.println("Capacity: " + activity.getCapacity());
                System.out.println();
            }
        }
    }

    /**
     * Print the list of passengers who have booked the package.
     */
    public void printPassengerList() {
        System.out.println("Passenger List for Travel Package: " + name);
        System.out.println("Passenger Capacity: " + maxPassengerCapacity);
        System.out.println("Number of Passengers: " + passengers.size());
        System.out.println();
        System.out.println("PASSENGERS : ");
        for (Passenger passenger : passengers) {
            System.out.println("Passenger Name: " + passenger.getName());
            System.out.println("Passenger Number: " + passenger.getNumber());
            System.out.println();
        }
    }

    /**
     * Show available activities for booking based on the package's itinerary and
     * activity capacity.
     *
     * @return A list of available activities.
     */
    public List<Activity> showAvailableActivities() {

        List<Activity> result = new ArrayList<>();

        for (Destination destination : itenary) {
            for (Activity activity : destination.getActivities()) {
                if (activity.getMembers().size() < activity.getCapacity()) {
                    if (!result.contains(activity)) {
                        result.add(activity);
                    }
                }
            }
        }
        return result;
    }

}
