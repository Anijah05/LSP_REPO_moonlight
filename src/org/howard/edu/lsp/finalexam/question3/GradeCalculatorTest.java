package org.howard.edu.lsp.finalexam.question3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the GradeCalculator class.
 */
public class GradeCalculatorTest {

    private final GradeCalculator calculator = new GradeCalculator();

    @Test
    void testAverageReturnsCorrectAverage() {
        assertEquals(80.0, calculator.average(70, 80, 90));
    }

    @Test
    void testLetterGradeReturnsCorrectLetter() {
        assertEquals("A", calculator.letterGrade(95));
    }

    @Test
    void testIsPassingReturnsTrueForPassingAverage() {
        assertTrue(calculator.isPassing(60));
    }

    @Test
    void testBoundaryLetterGradeAtMinimumPassingScore() {
        assertEquals("D", calculator.letterGrade(60));
    }

    @Test
    void testBoundaryLetterGradeAtMinimumAScore() {
        assertEquals("A", calculator.letterGrade(90));
    }

    @Test
    void testAverageThrowsExceptionForScoreBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.average(-1, 80, 90));
    }

    @Test
    void testAverageThrowsExceptionForScoreAboveOneHundred() {
        assertThrows(IllegalArgumentException.class, () -> calculator.average(101, 80, 90));
    }
}
