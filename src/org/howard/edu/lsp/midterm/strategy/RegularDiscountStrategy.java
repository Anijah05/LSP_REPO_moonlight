package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for regular customers.
 *
 * Author: Anijah
 */
public class RegularDiscountStrategy implements DiscountStrategy {

    /**
     * Returns the original price.
     *
     * @param price the original price
     * @return the final price
     */
    @Override
    public double calculate(double price) {
        return price;
    }
}