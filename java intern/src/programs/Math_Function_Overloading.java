package programs;

import java.util.Scanner;

public class Math_Function_Overloading {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        double number1,number2;

        while(true)
        {
            try {
                System.out.println("enter the number1");
                number1=Double.parseDouble(sc.nextLine().trim());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("enter valid number");


            }
        }

        while(true)
        {
            try {
                System.out.println("enter the number2");
                number2=Double.parseDouble(sc.nextLine().trim());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("enter valid number");


            }
        }

        calculate(number1);
        calculate(number1,number2);
        calculate((int) number1);
        calculate((int) number1,(int) number2);
    }
    static void calculate(double number1)
    {
        System.out.println("Single double Type variable");
        System.out.println("Absolute : "+Math.abs(number1));
        System.out.println("square root : "+Math.sqrt(number1));
        System.out.println("Cube root : "+Math.cbrt(number1));
        System.out.println("Log : "+Math.log10(number1));
        System.out.println("floor : "+Math.floor(number1));
        System.out.println("ceil : "+Math.ceil(number1));
        System.out.println("round-off : "+Math.round(number1));
        System.out.println("round to int : "+Math.rint(number1));
    }
    static void calculate(double number1,double number2)
    {
        System.out.println(" 2 double type variable");
        System.out.println("Maximum : "+Math.max(number1,number2));
        System.out.println("Minimum : "+Math.min(number1,number2));
        System.out.println("Power : "+Math.pow(number1,number2));
        System.out.println("Hypotenuse : "+Math.hypot(number1,number2));

    }
    static void calculate(int number1,int number2) {
        System.out.println("Double int variable");
        System.out.println("Addition : " + Math.addExact(number1, number2));
        System.out.println("Subtraction: " + Math.subtractExact(number1, number2));
        System.out.println("Multiplication : " + Math.multiplyExact(number1, number2));
            System.out.println("floor division : "+Math.floorDiv(number1,number2));
            System.out.println("floor modulus : "+Math.floorMod(number1,number2));

    }
    static void calculate(int number1)
    {
        System.out.println("Single int variable");
        System.out.println("Increment : "+Math.incrementExact(number1));
        System.out.println("Decrement : "+Math.decrementExact(number1));
        System.out.println("Cube root : "+Math.cbrt(number1));

    }

}
