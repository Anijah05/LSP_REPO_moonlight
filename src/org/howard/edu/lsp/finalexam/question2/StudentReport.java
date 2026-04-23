package org.howard.edu.lsp.finalexam.question2;

/**
 * Represents a student report with student name and GPA data.
 */
public class StudentReport extends Report {
    private String studentName;
    private double gpa;

    /**
     * Loads student report data.
     */
    @Override
    protected void loadData() {
        studentName = "John Doe";
        gpa = 3.8;
    }

    /**
     * Prints the student report header.
     */
    @Override
    protected void formatHeader() {
        System.out.println("=== HEADER ===");
        System.out.println("Student Report");
        System.out.println();
    }

    /**
     * Prints the student report body.
     */
    @Override
    protected void formatBody() {
        System.out.println("=== BODY ===");
        System.out.println("Student Name: " + studentName);
        System.out.println("GPA: " + gpa);
        System.out.println();
    }

    /**
     * Prints the student report footer.
     */
    @Override
    protected void formatFooter() {
        System.out.println("=== FOOTER ===");
        System.out.println("End of Student Report");
        System.out.println();
    }
}
