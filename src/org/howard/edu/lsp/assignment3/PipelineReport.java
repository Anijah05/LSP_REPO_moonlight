package org.howard.edu.lsp.assignment3;

/**
 * Tracks ETL run statistics and prints the required run summary.
 */
public class PipelineReport {

    private int rowsRead;
    private int rowsTransformed;
    private int rowsSkipped;

    /**
     * Increments the number of non-header, non-blank lines encountered (including bad ones).
     */
    public void incrementRowsRead() {
        rowsRead++;
    }

    /**
     * Increments the number of rows successfully transformed and written.
     */
    public void incrementRowsTransformed() {
        rowsTransformed++;
    }

    /**
     * Increments the number of invalid rows skipped.
     */
    public void incrementRowsSkipped() {
        rowsSkipped++;
    }

    /**
     * Prints the run summary required by the assignment.
     *
     * @param outputPath the output file path written
     */
    public void printSummary(String outputPath) {
        System.out.println("Run Summary");
        System.out.println("Rows read (non-header): " + rowsRead);
        System.out.println("Rows transformed (written): " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output file written: " + outputPath);
    }
}