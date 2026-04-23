package org.howard.edu.lsp.finalexam.question2;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates polymorphism by storing different Report objects in a List.
 */
public class Driver {

    /**
     * Runs the report generation demonstration.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        List<Report> reports = new ArrayList<>();
        reports.add(new StudentReport());
        reports.add(new CourseReport());

        for (Report report : reports) {
            report.generateReport();
        }
    }
}
