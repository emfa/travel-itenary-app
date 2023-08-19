package com.travelapp.Models.Behaviours;

/**
 * A class that implements the SignUpBehaviour interface and represents a
 * StandardSignUp behavior.
 * This behavior offers the standard sign-up price without any adjustments.
 */
public class StandardSignUp implements SignUpBehaviour {

    /**
     * Calculates the price for the standard sign-up, which remains unchanged.
     *
     * @param cost The original cost before any adjustments.
     * @return The unchanged cost for the standard sign-up.
     */
    @Override
    public Double price(Double cost) {
        return cost;
    }

}
