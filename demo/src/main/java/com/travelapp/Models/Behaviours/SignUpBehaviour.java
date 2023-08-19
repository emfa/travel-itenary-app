package com.travelapp.Models.Behaviours;

/**
 * An interface that defines the contract for different sign-up behaviors.
 * Classes implementing this interface should provide a method to calculate the
 * sign-up price.
 */
public interface SignUpBehaviour {

    /**
     * Calculates the price for a specific sign-up behavior.
     *
     * @param cost The original cost before any adjustments.
     * @return The calculated sign-up price based on the specific behavior.
     */
    public Double price(Double cost);

}
