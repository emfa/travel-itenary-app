package com.travelapp.Models.Behaviours;

/**
 * A class that implements the SignUpBehaviour interface and represents a
 * PremiumSignUp behavior.
 * This behavior offers a sign-up price of 0.0, indicating no cost.
 */
public class PremiumSignUp implements SignUpBehaviour {

    /**
     * Calculates the price for the premium sign-up, which is 0.0.
     *
     * @param cost The original cost before any adjustments (not used in this case).
     * @return The cost for the premium sign-up, which is always 0.0.
     */
    @Override
    public Double price(Double cost) {
        return 0.0;
    }

}
