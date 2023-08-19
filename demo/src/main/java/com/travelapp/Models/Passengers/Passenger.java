package com.travelapp.Models.Passengers;

import java.util.List;

import com.travelapp.Models.Activity;
import com.travelapp.Models.Destination;
import com.travelapp.Models.Behaviours.SignUpBehaviour;
import com.travelapp.Models.Package;

import java.util.ArrayList;

/**
 * An abstract class that represents a passenger in the travel application.
 * This class defines common attributes and behaviors for different types of
 * passengers.
 */
public abstract class Passenger {
    private String name;
    private Integer number;
    private Double balance = 0.0;

    SignUpBehaviour signUp;

    private List<Package> chosenPackages;
    private List<Activity> chosenActivities;

    /**
     * Constructor for creating a passenger with an initial balance.
     *
     * @param name    The name of the passenger.
     * @param number  The passenger's unique number.
     * @param balance The initial balance of the passenger.
     */
    public Passenger(String name, Integer number, Double balance) {
        this.name = name;
        this.number = number;
        this.balance = balance;
        this.chosenPackages = new ArrayList<>();
        this.chosenActivities = new ArrayList<>();
    }

    /**
     * Constructor for creating a passenger without an initial balance.
     *
     * @param name   The name of the passenger.
     * @param number The passenger's unique number.
     */
    public Passenger(String name, Integer number) {
        this.name = name;
        this.number = number;
        this.chosenPackages = new ArrayList<>();
        this.chosenActivities = new ArrayList<>();
    }

    /**
     * Get the name of the passenger.
     *
     * @return The name of the passenger.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the unique number of the passenger.
     *
     * @return The unique number of the passenger.
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Get the current balance of the passenger.
     *
     * @return The current balance of the passenger.
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * Set the balance of the passenger.
     *
     * @param balance The new balance for the passenger.
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * Update the passenger's balance after deducting a cost, if possible.
     *
     * @param balance The current balance of the passenger.
     * @param cost    The cost to be deducted from the balance.
     * @return True if the deduction is successful, false otherwise.
     */
    public boolean updateBalance(Double balance, Double cost) {
        if (balance >= cost) {
            setBalance(balance - cost);
            return true;
        }
        return false;
    }

    /**
     * Get the list of chosen packages by the passenger.
     *
     * @return The list of chosen packages.
     */
    public List<Package> getChosenPackages() {
        return chosenPackages;
    }

    /**
     * Get the list of chosen activities by the passenger.
     *
     * @return The list of chosen activities.
     */
    public List<Activity> getChosenActivities() {
        return chosenActivities;
    }

    /**
     * Calculate the cost based on the passenger's behavior.
     *
     * @param cost The original cost before any adjustments.
     * @return True if the cost calculation is successful, false otherwise.
     */
    public abstract boolean calculate(Double cost);

    /**
     * Choose a package and activities for the passenger.
     *
     * @param travelpackage The selected travel package.
     * @param destination   The chosen destination.
     * @param activity      The selected activity.
     * @return True if the selection and sign-up process is successful, false
     *         otherwise.
     */
    public boolean choosePackageAndActivities(Package travelpackage, Destination destination, Activity activity) {
        boolean status = false;
        if (travelpackage.getItenary().contains(destination)) {
            if (destination.getActivities().contains(activity)) {
                if (activity.signUp(this)) {
                    if (travelpackage.addPassenger(this)) {
                        this.addPackage(travelpackage);
                        status = true;
                    }
                }
            } else {
                System.out.println("Activity not present in Chosen Destination");
            }
        } else {
            System.out.println("Destination not present in Chosen Package");
        }

        if (status) {
            System.out
                    .println("Passenger " + this.getName() + " SignUp Successful for " + travelpackage.getName() + " - "
                            + destination.getName() + " - " + activity.getName());
        } else {
            System.out.println("SignUp failed");
        }
        return status;
    }

    /**
     * Add a travel package to the list of chosen packages.
     *
     * @param travelPackage The travel package to be added.
     */
    public void addPackage(Package travelPackage) {
        if (!chosenPackages.contains(travelPackage)) {
            chosenPackages.add(travelPackage);
        }
    }

    /**
     * Add an activity to the list of chosen activities.
     *
     * @param activity The activity to be added.
     */
    public void addActivity(Activity activity) {
        if (!chosenActivities.contains(activity)) {
            chosenActivities.add(activity);
        }
    }

    /**
     * Print details about the passenger and their chosen activities.
     */
    public void printPassengerDetails() {
        System.out.println("Passenger Details:");
        System.out.println("Name: " + this.getName());
        System.out.println("Passenger Number: " + this.getNumber());
        System.out.println("Balance: " + this.getBalance());
        System.out.println();
        System.out.println("ACTIVITIES:");
        for (Activity activity : this.getChosenActivities()) {
            for (Destination destination : activity.getAvailableDestinations()) {
                for (Package travelPackage : chosenPackages) {
                    if (travelPackage.getItenary().contains(destination)) {
                        System.out.println("Activity: " + activity.getName());
                        System.out.println("Destination: " + destination.getName());
                        System.out.println("Price: " + activity.getCost());
                        System.out.println();
                    }
                }
            }
        }
    }

    /**
     * Get a string representation of the passenger.
     *
     * @return A string representation of the passenger.
     */
    @Override
    public String toString() {
        return "Passenger [name=" + name + ", number=" + number + ", balance=" + balance + ", chosenPackages="
                + chosenPackages + ", chosenActivities=" + chosenActivities + "]";
    }

}
