package programs;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

// ✅ Class 1: Demo1 (Contains three methods)
class Demo1 {

    // ✅ 1. Leap Year Check
    public void checkLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            System.out.println(year + " is a Leap Year.");
        } else {
            System.out.println(year + " is NOT a Leap Year.");
        }
    }

    // ✅ 2. Standard Deviation Calculation
    public double calculateSTDDEV(double[] numbers) {
        int n = numbers.length;
        double sum = 0, mean, standardDeviation = 0;

        // Calculate Mean
        for (double num : numbers) {
            sum += num;
        }
        mean = sum / n;

        // Calculate Variance
        for (double num : numbers) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        // Standard Deviation = sqrt(variance)
        return Math.sqrt(standardDeviation / n);
    }

    // ✅ 3. Display User Details
    public void displayUserDetails(ArrayList<Demo_Class.user> list) {


        System.out.printf("%-30s %-10s %-20s %-15s %-15s%n",
                "Name", "Age", "Address", "Phone", "Department");

        for (Demo_Class.user user : list) {
            System.out.printf("%-30s %-10d %-20s %-15s %-15s%n",
                    user.name, user.age, user.address, user.phone, user.dept);
        }


    }
}

// ✅ Class 2: Demo2 (Contains two methods)
class Demo2 {

    // ✅ Static Variables
    static int counter = 0; // Static variable
    final static int MAX_VALUE = 100; // Final variable (Cannot be changed)

    // ✅ 1. Switch Case Implementation (Static Method)
    public static void switchExample(int choice) {
        switch (choice) {
            case 1:
                System.out.println("You selected Option 1: bad luck ");
                break;
            case 2:
                System.out.println("You selected Option 2: bad luck");
                break;
            case 3:
                System.out.println("You selected Option 3: good luck");
                break;
            case 4:
                System.out.println("You selected Option 4: good luck");
                break;
            default:
                System.out.println("Invalid choice! Please select a valid option.");
        }
    }

    // ✅ 2. Static Implementation (Static Variable, Final Variable, Inc/Dec Operators)
    public static void staticImplementation() {
        System.out.println("\n--- Static & Final Variable Demonstration ---");

        // Static variable manipulation
        System.out.println("Initial Counter Value: " + counter);
        counter++; // Increment operator
        System.out.println("Counter after Increment: " + counter);
        counter--; // Decrement operator
        System.out.println("Counter after Decrement: " + counter);

        // Final variable usage
        System.out.println("Max Value (Final Variable): " + MAX_VALUE);
        // MAX_VALUE++; // ❌ This will cause an error because final variables cannot be changed.
    }
}

// ✅ Main Class
public class Demo_Class {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Demo1 obj1 = new Demo1();

        // ⭐ 1. Leap Year Check

        int year;


        while (true) {
            try {
                System.out.println("enter the year");
                year = Integer.parseInt(sc.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("enter valid  number");


            }
        }
        obj1.checkLeapYear(year);

        // ⭐ 2. Standard Deviation Calculation

        int nofElemesnts;

        while (true) {
            try {

                System.out.print("\nEnter the number of elements for STDDEV: ");
                nofElemesnts = Integer.parseInt(sc.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("enter valid   number");

            }
        }
        double[] numbers = new double[nofElemesnts];
        System.out.println("Enter " + nofElemesnts + " numbers: ");
        for (int i = 0; i < nofElemesnts; i++) {

            while (true) {
                try {

                    System.out.print("\nEnter the element ");
                    numbers[i] = Integer.parseInt(sc.nextLine().trim());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("enter valid  number");


                }
            }


        }
        double stddev = obj1.calculateSTDDEV(numbers);
        System.out.println("Standard Deviation: " + stddev);

        // ⭐ 3. Display User Details
        // Consume newline



        while (true) {
            try {

                System.out.print("\nEnter the number of users ");
                nofElemesnts = Integer.parseInt(sc.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("enter valid   number");

            }
        }
        ArrayList<Demo_Class.user> list=new ArrayList<Demo_Class.user>();
         for(int i=0;i<nofElemesnts;i++){
        String name;
        while (true) {
            System.out.print("\nEnter your Name: ");
            name = sc.nextLine().trim();
            try {

                if (name.matches("[A-Za-z]+( [A-Za-z]+)*")) {
                    // Exit loop if valid
                    break;
                } else {
                    System.out.println("Error: Invalid name");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input! Enter a valid name ");

            }

        }


        int age;

        while (true) {
            try {

                System.out.print("Enter your Age: ");
                age = Integer.parseInt(sc.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("enter valid   number");


            }
        }


        // Consume newline

        String address;

        while (true) {
            System.out.print("Enter your Address: ");
            address = sc.nextLine().trim();
            try {

                if (address.matches("[A-Za-z0-9 ,.-]{5,100}")) {
                    // Exit loop if valid
                    break;
                } else {
                    System.out.println("Error: Invalid number");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input! Enter a valid one ");

            }

        }


        String phone;

        while (true) {
            System.out.print("Enter your Phone Number: ");
            phone = sc.nextLine().trim();
            try {

                if (phone.matches("\\d{9,15}")) {
                    // Exit loop if valid
                    break;
                } else {
                    System.out.println("Error: Invalid number");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input! Enter a valid one ");

            }
        }
        String dept;

        while (true) {
            System.out.print("Enter your Department: ");
            dept = sc.nextLine().trim();
            try {

                if (dept.matches("[A-Za-z]+( [A-Za-z]+)*")) {
                    // Exit loop if valid
                    break;
                } else {
                    System.out.println("Error: Invalid department");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input! Enter a valid one ");

            }

        }

             Demo_Class.user u=new Demo_Class.user();
             u.name=name;
             u.age=age;
             u.address=address;
             u.phone=phone;
             u.dept=dept;
             list.add(u);


    }
        obj1.displayUserDetails(list);
        // ⭐ 4. Switch Case Example
        ;
        int choice;


        while (true) {
            try {
                System.out.print("\nEnter your choice (1-4) for switch case example: ");

                choice= Integer.parseInt(sc.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("enter valid  choice number");


            }
        }
        Demo2.switchExample(choice);

        // ⭐ 5. Static Implementation Example
        Demo2.staticImplementation();

        sc.close();
    }
    static class user
    {
        String name;
        int age;
        String address;
        String phone;
        String dept;
    }
}
