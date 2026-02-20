package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a single product row in the ETL pipeline.
 * <p>
 * Encapsulates parsing/formatting and ensures price formatting rules are followed.
 * </p>
 */
public class ProductRecord {

    private final int productId;
    private final String name;
    private final BigDecimal price;
    private final String category;
    private final String priceRange;

    /**
     * Constructs a product record.
     *
     * @param productId   product ID
     * @param name        product name
     * @param price       product price (should already be rounded where required)
     * @param category    category
     * @param priceRange  computed price range
     */
    public ProductRecord(int productId, String name, BigDecimal price, String category, String priceRange) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = priceRange;
    }

    /**
     * Attempts to parse a CSV line into a basic ProductRecord (without priceRange).
     * Applies trimming and validates field count and numeric parsing.
     *
     * @param csvLine a non-blank CSV line
     * @return ProductRecord if valid; otherwise null to indicate the row should be skipped
     */
    public static ProductRecord tryParse(String csvLine) {
        String[] parts = csvLine.split(",", -1);
        if (parts.length != 4) {
            return null;
        }

        String idStr = parts[0].trim();
        String name = parts[1].trim();
        String priceStr = parts[2].trim();
        String category = parts[3].trim();

        int id;
        BigDecimal price;

        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            return null;
        }

        try {
            price = new BigDecimal(priceStr);
        } catch (NumberFormatException e) {
            return null;
        }

        // priceRange is not known until after transformations
        return new ProductRecord(id, name, price, category, "");
    }

    /**
     * Creates an output CSV row in the required column order with two-decimal price.
     *
     * @return CSV row string
     */
    public String toOutputCsvRow() {
        return productId + "," +
               name + "," +
               formatTwoDecimals(price) + "," +
               category + "," +
               priceRange;
    }

    /**
     * Returns the product ID.
     *
     * @return product ID
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Returns the product name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price.
     *
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Returns the category.
     *
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the computed price range.
     *
     * @return price range
     */
    public String getPriceRange() {
        return priceRange;
    }

    /**
     * Returns a new ProductRecord with updated fields.
     *
     * @param newName       updated name
     * @param newPrice      updated price
     * @param newCategory   updated category
     * @param newPriceRange updated price range
     * @return new ProductRecord instance
     */
    public ProductRecord with(String newName, BigDecimal newPrice, String newCategory, String newPriceRange) {
        return new ProductRecord(this.productId, newName, newPrice, newCategory, newPriceRange);
    }

    private static String formatTwoDecimals(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}