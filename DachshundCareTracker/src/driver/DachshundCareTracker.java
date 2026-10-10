/*
 * Allison Eckert
 * CIS 171
 * Midterm Project: Dachshund Care Tracker
 *
 * Tracks daily food, water, and exercise.
 * Compares recorded amounts with individual daily targets.
 *
 * Research:
 * https://www.pdsa.org.uk/pet-help-and-advice/looking-after-your-pet/puppies-dogs/medium-dogs/dachshund
 *
 * Food targets depend on the dog's feeding plan.
 * PDSA exercise guidance: standard dachshund 60 minutes,
 * miniature dachshund 30 minutes. Individual needs vary.
 */

// Question/struggle: How should different care amounts
// affect the overall daily care status?

// Incremental design: Build and test input, calculations,
// and output separately before combining them.

package driver;

import java.util.Scanner;

public class DachshundCareTracker {

    // Allows for small decimal differences when comparing food cups.
    private static final double FOOD_TOLERANCE_CUPS = 0.01;

    // Collects daily targets and amounts, then displays the results.
    // Consumes: command-line arguments, which are not used.
    // Produces: console prompts and a daily care summary.
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        displayTitle();

        String dogName = readDogName(input);

        System.out.println(
                "\nEnter daily targets from your dog's care plan.");

        double foodTarget = readAmount(
                input, "Daily food target (cups): ", false);

        double waterTarget = readAmount(
                input, "Daily water target (cups): ", false);

        int exerciseTarget = readMinutes(
                input, "Daily exercise target (minutes): ", false);

        System.out.println("\nEnter today's recorded care.");

        double foodAmount = readAmount(
                input, "Food eaten today (cups): ", true);

        double waterCups = readAmount(
                input, "Water consumed today (cups): ", true);

        int exerciseMinutes = readMinutes(
                input, "Exercise today (minutes): ", true);

        String careStatus = calculateCareStatus(
                foodAmount, exerciseMinutes, waterCups,
                foodTarget, exerciseTarget, waterTarget);

        int remainingExercise = calculateRemainingExercise(
                exerciseMinutes, exerciseTarget);

        displaySummary(
                dogName, foodAmount, exerciseMinutes, waterCups,
                foodTarget, exerciseTarget, waterTarget,
                careStatus, remainingExercise);

        input.close();
    }

    // Displays the program title.
    // Consumes: no parameters.
    // Produces: console title; returns nothing.
    public static void displayTitle() {
        System.out.println("Dachshund Daily Care Tracker");
        System.out.println("----------------------------");
    }

    // Reads a name and rejects blank entries.
    // Consumes: a Scanner for keyboard input.
    // Produces: a nonblank dog name.
    public static String readDogName(Scanner input) {
        System.out.print("Enter your dachshund's name: ");
        String dogName = input.nextLine().trim();

        while (dogName.isEmpty()) {
            System.out.print("Name cannot be blank. Enter a name: ");
            dogName = input.nextLine().trim();
        }

        return dogName;
    }

    // Reads a valid decimal amount.
    // Consumes: Scanner, prompt, and whether zero is allowed.
    // Produces: a finite positive or nonnegative number.
    public static double readAmount(
            Scanner input, String prompt, boolean allowZero) {

        double amount = 0;
        boolean valid = false;

        do {
            System.out.print(prompt);

            try {
                amount = Double.parseDouble(input.nextLine().trim());

                valid = Double.isFinite(amount)
                        && (allowZero ? amount >= 0 : amount > 0);
            } catch (NumberFormatException exception) {
                valid = false;
            }

            if (!valid) {
                if (allowZero) {
                    System.out.println(
                            "Enter a number of zero or greater.");
                } else {
                    System.out.println(
                            "Enter a number greater than zero.");
                }
            }
        } while (!valid);

        return amount;
    }

    // Reads exercise minutes as a whole number.
    // Consumes: Scanner, prompt, and whether zero is allowed.
    // Produces: valid positive or nonnegative whole minutes.
    public static int readMinutes(
            Scanner input, String prompt, boolean allowZero) {

        int minutes = 0;
        boolean valid = false;

        do {
            System.out.print(prompt);

            try {
                minutes = Integer.parseInt(input.nextLine().trim());
                valid = allowZero ? minutes >= 0 : minutes > 0;
            } catch (NumberFormatException exception) {
                valid = false;
            }

            if (!valid) {
                if (allowZero) {
                    System.out.println(
                            "Enter a whole number of zero or greater.");
                } else {
                    System.out.println(
                            "Enter a whole number greater than zero.");
                }
            }
        } while (!valid);

        return minutes;
    }

    // Determines whether recorded care meets the daily targets.
    // Consumes: actual food, exercise, water, and their targets.
    // Produces: a daily care status string; no console output.
    public static String calculateCareStatus(
            double foodAmount, int exerciseMinutes, double waterCups,
            double foodTarget, int exerciseTarget, double waterTarget) {

        boolean foodOnTarget =
                Math.abs(foodAmount - foodTarget)
                        <= FOOD_TOLERANCE_CUPS;

        if (foodAmount == 0 && exerciseMinutes == 0 && waterCups == 0) {
            return "No care recorded";
        } else if (foodOnTarget
                && exerciseMinutes >= exerciseTarget
                && waterCups >= waterTarget) {
            return "Daily targets met";
        } else {
            return "Needs attention";
        }
    }

    // Calculates exercise minutes still needed.
    // Consumes: completed exercise and the daily exercise target.
    // Produces: remaining minutes, never less than zero.
    public static int calculateRemainingExercise(
            int exerciseMinutes, int exerciseTarget) {

        return Math.max(0, exerciseTarget - exerciseMinutes);
    }

    // Displays recorded care, targets, and specific feedback.
    // Consumes: name, amounts, targets, status, remaining exercise.
    // Produces: formatted console summary; returns nothing.
    public static void displaySummary(
            String dogName,
            double foodAmount, int exerciseMinutes, double waterCups,
            double foodTarget, int exerciseTarget, double waterTarget,
            String careStatus, int remainingExercise) {

        System.out.println("\nDaily Care Summary for " + dogName);
        System.out.println("----------------------------");

        System.out.printf(
                "Food: %.2f cups | Target: %.2f cups%n",
                foodAmount, foodTarget);

        System.out.printf(
                "Water: %.2f cups | Target: %.2f cups%n",
                waterCups, waterTarget);

        System.out.printf(
                "Exercise: %d minutes | Target: %d minutes%n",
                exerciseMinutes, exerciseTarget);

        System.out.println("Daily Care Status: " + careStatus);

        if (foodAmount < foodTarget - FOOD_TOLERANCE_CUPS) {
            System.out.println("Food is below the daily target.");
        } else if (foodAmount > foodTarget + FOOD_TOLERANCE_CUPS) {
            System.out.println("Food is above the daily target.");
        } else {
            System.out.println("Food matches the daily target.");
        }

        if (waterCups < waterTarget) {
            System.out.println("Water is below the daily target.");
        } else {
            System.out.println("Water target met.");
        }

        System.out.println(
                "Exercise minutes remaining: " + remainingExercise);
    }
}