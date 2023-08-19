package com.travelapp.Models.Passengers;

import com.travelapp.Models.Behaviours.GoldSignUp;

/**
 * A class that represents a GoldPassenger, a type of passenger with a Gold
 * sign-up behavior.
 * Gold passengers receive discounts based on the GoldSignUp behavior.
 */
public class GoldPassenger extends Passenger {

    /**
     * Constructor for creating a GoldPassenger with an initial balance.
     *
     * @param name    The name of the GoldPassenger.
     * @param number  The unique number of the GoldPassenger.
     * @param balance The initial balance of the GoldPassenger.
     */
    public GoldPassenger(String name, Integer number, Double balance) {
        super(name, number, balance);
        signUp = new GoldSignUp();
    }

    /**
     * Calculate the adjusted cost for a GoldPassenger using the GoldSignUp
     * behavior.
     *
     * @param cost The original cost before any adjustments.
     * @return True if the balance is updated successfully, false otherwise.
     */
    @Override
    public boolean calculate(Double cost) {
        return updateBalance(getBalance(), signUp.price(cost));
    }

}
