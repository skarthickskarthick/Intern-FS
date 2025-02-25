package programs;

import java.util.Scanner;

public class operator_precedence {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);

        while(true) {
            System.out.println("1.precedence evaluation 2.exit");
            System.out.println("enter the choice");
            String input = sc.nextLine().trim(); // Read input & trim spaces
            int ch=-1;
            try {
               ch= Integer.parseInt(input); // Convert to integer

                if (ch>= 1 && ch <= 2) {

                    if(ch==2)
                        break;
                    // Exit loop if valid
                } else {
                    System.out.println("Error: Number must be between the given range");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input! Enter a number between the given range");
            }

            int a=0,b=0,c=0;
            if(ch==1) {
                while(true) {
                    System.out.println("enter the number1");
                    input = sc.nextLine().trim();
                    try {
                        a = Integer.parseInt(input);
                        if (a >= 1 && a <= 100) {
                            // Exit loop if valid
                            break;
                        } else {
                            System.out.println("Error: Number must be between the given range");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid input! Enter a number between the given range");

                    }
                }
                while(true) {
                    System.out.println("enter the number2");
                    input = sc.nextLine().trim();
                    try {
                        b = Integer.parseInt(input);
                        if (b >= 1 && b <= 100) {
                            // Exit loop if valid
                            break;
                        } else {
                            System.out.println("Error: Number must be between the given range");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid input! Enter a number between the given range");

                    }
                }
                while(true) {
                    System.out.println("enter the number3");
                    input = sc.nextLine().trim();
                    try {
                        c = Integer.parseInt(input);
                        if (c >= 1 && c <= 100) {
                            // Exit loop if valid
                            break;
                        } else {
                            System.out.println("Error: Number must be between the given range");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid input! Enter a number between the given range");

                    }
                }
                    // 1. Parentheses ()
                    int result1 = (a + b) * c;
                    System.out.println("Parentheses: " + result1); // (10+5) * 2 = 30

                    // 2. Unary Operators (++ -- ! ~)
                    int result2 = ++a - b--;
                    System.out.println("Unary: " + result2); // (11 - 5) = 6 (a=11, b=4)


                    // 3. Multiplicative (* / %)
                    int result3 = a * b / c % 3;
                    System.out.println("Multiplicative: " + result3); // (11 * 4 / 2 % 3) = 1

                    // 4. Additive (+ -)
                    int result4 = a + b - c;
                    System.out.println("Additive: " + result4); // 11 + 4 - 2 = 13

                    // 5. Shift Operators (<< >> >>>)
                    int result5 = a << 1;
                    System.out.println("Bitwise Shift: " + result5); // 11 * 2 = 22

                    // 6. Relational Operators (<, >, <=, >=)
                    boolean result6 = a > b;
                    System.out.println("Relational: " + result6); // 11 > 4 → true

                    // 7. Equality Operators (== !=)
                    boolean result7 = a != c;
                    System.out.println("Equality: " + result7); // 11 != 2 → true

                    // 8. Bitwise AND, XOR, OR (&, ^, |)
                    int result8 = a & b;
                    System.out.println("Bitwise AND: " + result8); // 11 & 4 → 0

                    int result9 = a ^ b;
                    System.out.println("Bitwise XOR: " + result9); // 11 ^ 4 → 15

                    int result10 = a | b;
                    System.out.println("Bitwise OR: " + result10); // 11 | 4 → 15

                    // 9. Logical AND (&&) and OR (||)
                    boolean result11 = (a > b) && (b < c);
                    System.out.println("Logical AND: " + result11); // (true && false) → false

                    boolean result12 = (a > b) || (b < c);
                    System.out.println("Logical OR: " + result12); // (true || false) → true

                    // 10. Ternary Operator (?:)
                    int result13 = (a > b) ? a : b;
                    System.out.println("Ternary: " + result13); // 11 (a > b is true)

            }

        }
    }
}
