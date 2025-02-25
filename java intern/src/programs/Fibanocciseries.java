package programs;

import java.math.BigInteger;
import java.util.Scanner;

public class Fibanocciseries {
    public static void main(String args[])
    {

        Scanner scanner = new Scanner(System.in);
        int num;

        // Input validation: Ensure the user enters a valid positive integer
        while (true) {
            System.out.print("Enter the number of Fibonacci terms: ");
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                if (num >= 0) {
                    break; // Valid input, exit loop
                } else {
                    System.out.println("Error: Please enter a non-negative integer.");
                }
            } else {
                System.out.println("Error: Invalid input. Please enter an integer.");
                scanner.next(); // Consume invalid input
            }
        }

        // Generate Fibonacci series
        System.out.println("Fibonacci Series up to " + num + " terms:");
        printFibonacci(num);

        scanner.close();
    }

    // Function to print Fibonacci series using BigInteger
    public static void printFibonacci(int n) {
        if (n == 0) {
            System.out.println("No terms to display.");
            return;
        }

        BigInteger first = BigInteger.ZERO;
        BigInteger second = BigInteger.ONE;

        System.out.print(first); // Print first term

        if (n > 1) {
            System.out.print(", " + second); // Print second term
        }

        for (int i = 2; i < n; i++) {
            BigInteger next = first.add(second);
            System.out.print(", " + next);
            first = second;
            second = next;
        }
        System.out.println();
    }
}
