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

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String dogName;
        double foodAmount;
        int exerciseMinutes;
        int waterCups;

        displayTitle();

            System.out.print("Enter your dachshund's name: ");
        dogName = input.nextLine();

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

        input.close();
    } //closes main

        // Displays the program title
        // no parameters -> void
        public static void displayTitle() {
            System.out.println("Dachshund Daily Care Tracker");
            System.out.println("----------------------------");
        }

            // Will determine the dachshund's daily care status
            // no parameters -> void
            public static void calculateCareStatus() {
                // Method will be completed in a later step.
            }
    }



