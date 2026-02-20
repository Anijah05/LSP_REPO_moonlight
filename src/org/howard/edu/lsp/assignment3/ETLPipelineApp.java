package org.howard.edu.lsp.assignment3;

/**
 * Name: Anijah
 */

import java.io.File;
import java.util.List;

/**
 * Entry point for Assignment 3 ETL pipeline application.
 * <p>
 * Reads {@code data/products.csv}, applies the same transformations as Assignment 2,
 * and writes {@code data/transformed_products.csv}. Also prints a run summary.
 * </p>
 */
public class ETLPipelineApp {

    private static final String INPUT_PATH = "data/products.csv";
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    /**
     * Runs the ETL pipeline using relative paths required by the assignment.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        File inputFile = new File(INPUT_PATH);
        File outputFile = new File(OUTPUT_PATH);

        if (!inputFile.exists()) {
            System.out.println("ERROR: Missing input file at " + INPUT_PATH);
            System.out.println("Program exiting without processing.");
            return;
        }

        // Ensure output directory exists
        File parent = outputFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        PipelineReport report = new PipelineReport();
        CsvProductReader reader = new CsvProductReader();
        ProductTransformer transformer = new ProductTransformer();
        CsvProductWriter writer = new CsvProductWriter();

        List<ProductRecord> transformed;
        try {
            TransformResult result = reader.readAndTransform(inputFile, report, transformer);
            transformed = result.getTransformedRecords();

            // Always write output header; if input was header-only, transformed will be empty
            writer.write(outputFile, transformed);

            // Print summary (same required fields as Assignment 2)
            report.printSummary(outputFile.getPath());

        } catch (Exception e) {
            // Graceful handling: no stack trace
            System.out.println("ERROR: An I/O error occurred while processing files.");
            System.out.println("Program exiting cleanly.");
        }
    }
}