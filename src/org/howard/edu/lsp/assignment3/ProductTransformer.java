package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Applies the Assignment 2 transformations in the required exact order.
 * <p>
 * Encapsulates the "Transform" portion of ETL.
 * </p>
 */
public class ProductTransformer {

    /**
     * Applies all transformations in the required order and returns a new record.
     *
     * @param in input record (parsed from CSV)
     * @return transformed record ready for output
     */
    public ProductRecord applyAll(ProductRecord in) {
        // 1) Convert product names to UPPERCASE.
        String nameUpper = in.getName().toUpperCase();

        // Track original category before changes (rule #3 depends on original)
        boolean originalWasElectronics = "Electronics".equals(in.getCategory());

        // 2) If category is "Electronics", apply 10% discount to the price.
        BigDecimal discounted = in.getPrice();
        if (originalWasElectronics) {
            discounted = discounted.multiply(new BigDecimal("0.90"));
        }

        // Round-half-up to exactly 2 decimals (used for later rules)
        BigDecimal finalRoundedPrice = discounted.setScale(2, RoundingMode.HALF_UP);

        // 3) If final rounded price > 500 AND original category was Electronics,
        //    change category to "Premium Electronics".
        String outCategory = in.getCategory();
        if (originalWasElectronics && finalRoundedPrice.compareTo(new BigDecimal("500.00")) > 0) {
            outCategory = "Premium Electronics";
        }

        // 4) Add PriceRange based on final rounded price.
        String priceRange = computePriceRange(finalRoundedPrice);

        return in.with(nameUpper, finalRoundedPrice, outCategory, priceRange);
    }

    /**
     * Computes the PriceRange field from the final rounded price.
     *
     * @param finalRoundedPrice price rounded to two decimals
     * @return price range label
     */
    public String computePriceRange(BigDecimal finalRoundedPrice) {
        if (finalRoundedPrice.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";
        } else if (finalRoundedPrice.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";
        } else if (finalRoundedPrice.compareTo(new BigDecimal("500.00")) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }
}