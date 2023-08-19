package com.travelapp.Models.Behaviours;

/**
 * A class that implements the SignUpBehaviour interface and represents a
 * GoldSignUp behavior.
 * This behavior applies a 10% discount to the provided cost.
 */
public class GoldSignUp implements SignUpBehaviour {

    /**
     * Calculates the price after applying a 10% discount.
     *
     * @param cost The original cost before the discount.
     * @return The cost after applying the 10% discount.
     */
    @Override
    public Double price(Double cost) {
        return cost * 0.9;
    }

}
