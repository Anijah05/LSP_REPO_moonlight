package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for VIP customers.
 *
 * Author: Anijah
 */
public class VipDiscountStrategy implements DiscountStrategy {

    /**
     * Applies a 20 percent discount.
     *
     * @param price the original price
     * @return the final price
     */
    @Override
    public double calculate(double price) {
        return price * 0.80;
    }
}