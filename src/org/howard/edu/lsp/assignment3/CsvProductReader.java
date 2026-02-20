package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads product rows from a CSV file and applies transformation logic.
 * <p>
 * Implements the "Extract" portion of ETL plus validation/row skipping rules.
 * </p>
 */
public class CsvProductReader {

    /**
     * Reads the input CSV, applies row skipping rules, transforms valid rows,
     * and updates the provided report counters.
     *
     * @param inputFile    input CSV file (must exist)
     * @param report       pipeline report to update (rows read/transformed/skipped)
     * @param transformer  transformer implementing Assignment 2 rules
     * @return result containing transformed records ready for output
     * @throws IOException if an I/O error occurs while reading
     */
    public TransformResult readAndTransform(File inputFile, PipelineReport report, ProductTransformer transformer)
            throws IOException {

        List<ProductRecord> transformed = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

            // Header may be null for a truly empty file; output should still be header-only.
            String header = br.readLine();
            if (header == null) {
                return new TransformResult(transformed);
            }

            String line;
            while ((line = br.readLine()) != null) {

                // Ignore blank lines completely (not read, not skipped) to match expected summary counts.
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Count all non-header, non-blank lines encountered (including bad ones)
                report.incrementRowsRead();

                ProductRecord parsed = ProductRecord.tryParse(line);
                if (parsed == null) {
                    report.incrementRowsSkipped();
                    continue;
                }

                ProductRecord out = transformer.applyAll(parsed);
                transformed.add(out);
                report.incrementRowsTransformed();
            }
        }

        return new TransformResult(transformed);
    }
}