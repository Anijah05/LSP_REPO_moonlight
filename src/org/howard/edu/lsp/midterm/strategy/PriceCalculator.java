package org.howard.edu.lsp.midterm.strategy;

/**
 * Uses a discount strategy to calculate final prices.
 *
 * Author: Anijah
 */
public class PriceCalculator {
    private DiscountStrategy strategy;

    /**
     * Sets the pricing strategy to use.
     *
     * @param strategy the discount strategy
     */
    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price using the current strategy.
     *
     * @param price the original price
     * @return the final price
     */
    public double calculatePrice(double price) {
        if (strategy == null) {
            return price;
        }
        return strategy.calculate(price);
    }
}