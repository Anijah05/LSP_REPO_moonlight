package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Writes transformed product records to a CSV file.
 * <p>
 * Implements the "Load" portion of ETL.
 * </p>
 */
public class CsvProductWriter {

    /**
     * Writes records to the output CSV with the required header and column order.
     *
     * @param outputFile output file path (e.g., data/transformed_products.csv)
     * @param records    transformed records to write (may be empty)
     * @throws IOException if an I/O error occurs while writing
     */
    public void write(File outputFile, List<ProductRecord> records) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();

            for (ProductRecord r : records) {
                bw.write(r.toOutputCsvRow());
                bw.newLine();
            }
        }
    }
}