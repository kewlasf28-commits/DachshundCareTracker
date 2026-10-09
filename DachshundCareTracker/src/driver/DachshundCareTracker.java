/*
 * Allison Eckert
 * Midterm Project
 * Dachshund Care Tracker
 *
 * This program tracks a dachshund's daily food, water,
 * exercise, and care information to determine a daily
 * care status.
 */

// Question/struggle: How should I determine the daily care status
// when the food, water, and exercise amounts are different?

// Incremental design: I am building and testing one part of the program
// at a time before adding the next feature.

package driver;

import java.util.Scanner;

public class DachshundCareTracker {

    // Collects daily care information and displays the summary.
    // Consumes: command-line arguments, which are not used.
    // Produces: console prompts, a summary, and care warnings.
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String dogName;
        double foodAmount;
        int exerciseMinutes;
        int waterCups;

        displayTitle();

        // Keep asking until the user enters a name.
        // trim() removes extra spaces before and after the name.
        System.out.print("Enter your dachshund's name: ");
        dogName = input.nextLine().trim();

        while (dogName.isEmpty()) {
            System.out.print(
                    "Name cannot be blank. Enter your dachshund's name: ");
            dogName = input.nextLine().trim();
        }

        System.out.print("Enter amount of food eaten today (cups): ");
        foodAmount = input.nextDouble();

        System.out.print("Enter exercise completed today (minutes): ");
        exerciseMinutes = input.nextInt();

        System.out.print("Enter amount of water consumed today (cups): ");
        waterCups = input.nextInt();

        System.out.println();
        System.out.println("Daily Care Summary for " + dogName);
        System.out.println("----------------------------");
        System.out.println("Food: " + foodAmount + " cups");
        System.out.println("Exercise: " + exerciseMinutes + " minutes");
        System.out.println("Water: " + waterCups + " cups");

        calculateCareStatus(foodAmount, exerciseMinutes, waterCups);

        input.close();
    } // closes main

    // Displays the program title.
    // Consumes: no parameters.
    // Produces: the title in the console; returns nothing.
    public static void displayTitle() {
        System.out.println("Dachshund Daily Care Tracker");
        System.out.println("----------------------------");
    }

    // Checks for missing daily care and displays warnings.
    // Consumes: food cups, exercise minutes, and water cups.
    // Produces: console messages; returns nothing.
    public static void calculateCareStatus(double foodAmount,
                                           int exerciseMinutes,
                                           int waterCups) {

        if (foodAmount == 0 && exerciseMinutes == 0 && waterCups == 0) {
            System.out.println(
                    "WARNING: No food, water, or exercise recorded today.");
        }

        if (foodAmount == 0) {
            System.out.println(
                    "WARNING: No food recorded today. Check feeding.");
        }

        if (waterCups == 0) {
            System.out.println(
                    "WARNING: No water recorded today. Check water availability.");
        }

        if (exerciseMinutes == 0) {
            System.out.println(
                    "REMINDER: No exercise recorded today.");
        }
    } // closes calculateCareStatus

} // closes class