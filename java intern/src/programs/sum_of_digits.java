package programs;

import java.math.BigInteger;
import java.util.Scanner;

public class sum_of_digits {
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        BigInteger number;

        // Read and validate input
        while (true) {
            try {
                System.out.print("Enter an integer number: ");
                number = scanner.nextBigInteger(); // Reads directly as BigInteger
                break; // Valid input, exit loop
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }

        // Calculate sum of digits using BigInteger arithmetic
        BigInteger ten = BigInteger.TEN;
        int sum = 0;
        number = number.abs(); // Convert to positive for negative numbers

        while (!number.equals(BigInteger.ZERO)) {
            sum += number.mod(ten).intValue(); // Extract last digit
            number = number.divide(ten); // Remove last digit
        }

        // Output result
        System.out.println("Sum of digits: " + sum);
        scanner.close();

    }
}
