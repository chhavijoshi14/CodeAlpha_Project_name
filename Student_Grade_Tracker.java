import java.util.ArrayList;
import java.util.Scanner;
public class Student_Grade_Tracker {



        public static void main(String[] args) {
            // Create a Scanner object for user input
            Scanner scanner = new Scanner(System.in);
            // Create an ArrayList to store student grades
            ArrayList<Double> grades = new ArrayList<>();

            // Prompt the teacher to enter grades
            System.out.println("Enter student grades (type 'done' when finished):");

            while (true) {
                System.out.print("Enter a grade: ");
                String input = scanner.nextLine();

                // Check if the user wants to finish inputting grades
                if (input.equalsIgnoreCase("done")) {
                    break;
                }

                try {
                    // Convert the input to a double and add to the ArrayList
                    double grade = Double.parseDouble(input);
                    if (grade < 0) {
                        System.out.println("Grade cannot be negative. Please enter a valid grade.");
                    } else {
                        grades.add(grade);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                }
            }

            // Close the scanner
            scanner.close();

            // Compute and display the average, highest, and lowest scores
            if (grades.isEmpty()) {
                System.out.println("No grades entered.");
            } else {
                double sum = 0;
                double highest = grades.get(0);
                double lowest = grades.get(0);

                for (double grade : grades) {
                    sum += grade;
                    if (grade > highest) {
                        highest = grade;
                    }
                    if (grade < lowest) {
                        lowest = grade;
                    }
                }

                double average = sum / grades.size();

                System.out.printf("Average score: %.2f%n", average);
                System.out.printf("Highest score: %.2f%n", highest);
                System.out.printf("Lowest score: %.2f%n", lowest);
            }
        }
    }


