package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for member customers.
 *
 * Author: Anijah
 */
public class MemberDiscountStrategy implements DiscountStrategy {

    /**
     * Applies a 10 percent discount.
     *
     * @param price the original price
     * @return the final price
     */
    @Override
    public double calculate(double price) {
        return price * 0.90;
    }
}