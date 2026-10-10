package test;

/*
 * Allison Eckert
 * CIS 171 Midterm: Dachshund Care Tracker
 * Tests care status and remaining exercise calculations.
 */

import driver.DachshundCareTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DachshundCareTrackerTest {

    // Checks that matching amounts meet all targets.
    @Test
    public void allTargetsMet() {
        assertEquals("Daily targets met",
                DachshundCareTracker.calculateCareStatus(
                        0.75, 30, 2.0, 0.75, 30, 2.0));
    }

    // Checks the result when no care is recorded.
    @Test
    public void noCareRecorded() {
        assertEquals("No care recorded",
                DachshundCareTracker.calculateCareStatus(
                        0.0, 0, 0.0, 0.75, 30, 2.0));
    }

    // Checks food below its target.
    @Test
    public void foodBelowTarget() {
        assertEquals("Needs attention",
                DachshundCareTracker.calculateCareStatus(
                        0.50, 30, 2.0, 0.75, 30, 2.0));
    }

    // Checks food above its target.
    @Test
    public void foodAboveTarget() {
        assertEquals("Needs attention",
                DachshundCareTracker.calculateCareStatus(
                        1.0, 30, 2.0, 0.75, 30, 2.0));
    }

    // Checks water below its target.
    @Test
    public void waterBelowTarget() {
        assertEquals("Needs attention",
                DachshundCareTracker.calculateCareStatus(
                        0.75, 30, 1.0, 0.75, 30, 2.0));
    }

    // Checks exercise below its target.
    @Test
    public void exerciseBelowTarget() {
        assertEquals("Needs attention",
                DachshundCareTracker.calculateCareStatus(
                        0.75, 20, 2.0, 0.75, 30, 2.0));
    }

    // Checks a food amount within the decimal tolerance.
    @Test
    public void foodWithinTolerance() {
        assertEquals("Daily targets met",
                DachshundCareTracker.calculateCareStatus(
                        0.755, 30, 2.0, 0.75, 30, 2.0));
    }

    // Checks remaining minutes when exercise is incomplete.
    @Test
    public void exerciseStillNeeded() {
        assertEquals(10,
                DachshundCareTracker.calculateRemainingExercise(20, 30));
    }

    // Checks remaining minutes at the exact target.
    @Test
    public void exerciseTargetReached() {
        assertEquals(0,
                DachshundCareTracker.calculateRemainingExercise(30, 30));
    }

    // Checks that extra exercise never produces negative minutes.
    @Test
    public void exerciseAboveTarget() {
        assertEquals(0,
                DachshundCareTracker.calculateRemainingExercise(45, 30));
    }

    // Checks remaining minutes when no exercise is recorded.
    @Test
    public void noExerciseRecorded() {
        assertEquals(30,
                DachshundCareTracker.calculateRemainingExercise(0, 30));
    }
}

