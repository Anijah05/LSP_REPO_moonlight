package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class for reports using the Template Method pattern.
 * The generateReport method defines the fixed report workflow.
 */
public abstract class Report {

    /**
     * Template method that defines the fixed workflow for generating a report.
     */
    public final void generateReport() {
        loadData();
        formatHeader();
        formatBody();
        formatFooter();
    }

    /**
     * Loads report-specific data.
     */
    protected abstract void loadData();

    /**
     * Formats and prints the report header.
     */
    protected abstract void formatHeader();

    /**
     * Formats and prints the report body.
     */
    protected abstract void formatBody();

    /**
     * Formats and prints the report footer.
     */
    protected abstract void formatFooter();
}
