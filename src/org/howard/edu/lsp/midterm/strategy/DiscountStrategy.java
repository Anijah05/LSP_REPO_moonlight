package org.howard.edu.lsp.midterm.strategy;

/**
 * Strategy interface for calculating final prices.
 *
 * Author: Anijah
 */
public interface DiscountStrategy {

    /**
     * Calculates the final price after applying the strategy.
     *
     * @param price the original price
     * @return the final price
     */
    double calculate(double price);
}