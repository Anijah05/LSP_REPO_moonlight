package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for holiday pricing.
 *
 * Author: Anijah
 */
public class HolidayDiscountStrategy implements DiscountStrategy {

    /**
     * Applies a 15 percent discount.
     *
     * @param price the original price
     * @return the final price
     */
    @Override
    public double calculate(double price) {
        return price * 0.85;
    }
}