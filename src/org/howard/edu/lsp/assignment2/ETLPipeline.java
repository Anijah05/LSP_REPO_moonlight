/**
 * Name: Anijah Dancer
 */
package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    private static final String INPUT_PATH = "data/products.csv";
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    public static void main(String[] args) {
        File inputFile = new File(INPUT_PATH);
        File outputFile = new File(OUTPUT_PATH);

        if (!inputFile.exists()) {
            System.out.println("ERROR: Missing input file at " + INPUT_PATH);
            System.out.println("Program exiting without processing.");
            return;
        }

        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;

        File parent = outputFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String header = br.readLine();

            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();

            if (header == null) {
                printSummary(rowsRead, rowsTransformed, rowsSkipped, outputFile.getPath());
                return;
            }

            String line;
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                rowsRead++;

                String[] parts = line.split(",", -1);
                if (parts.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                String productIdStr = parts[0].trim();
                String name = parts[1].trim();
                String priceStr = parts[2].trim();
                String category = parts[3].trim();

                int productId;
                BigDecimal price;

                try {
                    productId = Integer.parseInt(productIdStr);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    price = new BigDecimal(priceStr);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                    continue;
                }

                String transformedName = name.toUpperCase();
                boolean originalWasElectronics = category.equals("Electronics");

                BigDecimal transformedPrice = price;
                if (originalWasElectronics) {
                    transformedPrice = transformedPrice.multiply(new BigDecimal("0.90"));
                }

                BigDecimal finalRoundedPrice = transformedPrice.setScale(2, RoundingMode.HALF_UP);

                String transformedCategory = category;
                if (originalWasElectronics && finalRoundedPrice.compareTo(new BigDecimal("500.00")) > 0) {
                    transformedCategory = "Premium Electronics";
                }

                String priceRange = computePriceRange(finalRoundedPrice);

                bw.write(productId + "," +
                         transformedName + "," +
                         finalRoundedPrice.toPlainString() + "," +
                         transformedCategory + "," +
                         priceRange);
                bw.newLine();

                rowsTransformed++;
            }

        } catch (IOException e) {
            System.out.println("ERROR: An I/O error occurred while processing files.");
            System.out.println("Program exiting cleanly.");
            return;
        }

        printSummary(rowsRead, rowsTransformed, rowsSkipped, outputFile.getPath());
    }

    private static String computePriceRange(BigDecimal price) {
        if (price.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";
        } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";
        } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }

    private static void printSummary(int rowsRead, int rowsTransformed, int rowsSkipped, String outputPath) {
        System.out.println("Run Summary");
        System.out.println("Rows read (non-header): " + rowsRead);
        System.out.println("Rows transformed (written): " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output file written: " + outputPath);
    }
}
