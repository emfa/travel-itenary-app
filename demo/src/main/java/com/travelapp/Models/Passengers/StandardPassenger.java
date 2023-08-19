package com.travelapp.Models.Passengers;

import com.travelapp.Models.Behaviours.StandardSignUp;

/**
 * A class that represents a StandardPassenger, a type of passenger with a
 * Standard sign-up behavior.
 * Standard passengers have a StandardSignUp behavior associated with them.
 */
public class StandardPassenger extends Passenger {

    /**
     * Constructor for creating a StandardPassenger with an initial balance.
     *
     * @param name    The name of the StandardPassenger.
     * @param number  The unique number of the StandardPassenger.
     * @param balance The initial balance of the StandardPassenger.
     */
    public StandardPassenger(String name, Integer number, Double balance) {
        super(name, number, balance);
        signUp = new StandardSignUp();
    }

    /**
     * Calculate the adjusted cost for a StandardPassenger using the StandardSignUp
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
