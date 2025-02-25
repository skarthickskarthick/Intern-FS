package programs;

import java.util.Scanner;

public class Validate_Inputs {
    public static void main(String args[])
    {


    }
 public static int   getInput(int n)
    {
        Scanner scanner=new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter your Age: ");
                n= Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid number for age.");
            }
        }
        return n;
    }
    public static double getInput(Double n)
    {
        Scanner scanner=new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter your Age: ");
                n= Double.parseDouble(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid number for age.");
            }
        }
        return n;
    }
    public static String getNameInput(String name)
    {
        Scanner scanner=new Scanner(System.in);
        while (true) {
            System.out.print("Enter the college name: ");
           name = scanner.nextLine().trim();
            if (name.matches("[A-Za-z]+( [A-Za-z]+)*")) {
                break;
            } else {
                System.out.println("Error: Invalid college name");
            }
        }
        return name;
    }
    public static int getConditionedInput(int choice)
    {
        Scanner scanner=new Scanner(System.in);

        while (true) {
            try {

                System.out.println("1-Domestic 2-Commercial 3-Industrial 4-Agriculture 5-Government office 6-Temporary Connection");
                System.out.println("enter the choice");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 6)
                    break;
                else
                    System.out.println("enter number less than 6");
            } catch (NumberFormatException e) {
                System.out.println("Invalid Format!");
            }
        }
        return choice;
    }
    public  static String getPhoneInput(String phone)
    {
        Scanner scanner=new Scanner(System.in);
        while (true) {
            System.out.print("Enter your Phone Number: ");
            phone = scanner.nextLine().trim();
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
        return phone;
    }
}
