package com.travelapp.Models.Passengers;

import com.travelapp.Models.Behaviours.PremiumSignUp;

/**
 * A class that represents a PremiumPassenger, a type of passenger with a
 * Premium sign-up behavior.
 * Premium passengers have a PremiumSignUp behavior associated with them.
 */
public class PremiumPassenger extends Passenger {

    /**
     * Constructor for creating a PremiumPassenger without an initial balance.
     *
     * @param name   The name of the PremiumPassenger.
     * @param number The unique number of the PremiumPassenger.
     */
    public PremiumPassenger(String name, Integer number) {
        super(name, number);
        signUp = new PremiumSignUp();
    }

    /**
     * Calculate the adjusted cost for a PremiumPassenger using the PremiumSignUp
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
