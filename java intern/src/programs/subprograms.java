package programs;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class subprograms {
    public  static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        BigInteger num;
        while (true) {
            try {
                System.out.println("Enter the number:");

                // Read input directly as BigInteger
                num = new BigInteger(sc.nextLine().trim());  // Trims spaces but still checks for empty input

                break;  // Exit loop if valid
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number (no spaces, no empty input):");
            }
        }

        int ch=0;
        while(true) {
            while (true) {
                try {

                    System.out.println("1.sum 2.reverse 3.largest 4.smallest 5.average 6.find index 7.string format 8.mid element 9.Armstrong number" +
                            "10.total counts 11.number reptition 12.replacing number 13.exit");
                    System.out.println("enter the choice");
                    ch = Integer.parseInt(sc.nextLine().trim());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("enter valid  choice number");


                }
            }
                if (ch == 1)
                    System.out.println("sum:" + sumDigits(num));
                else if (ch == 2)
                    System.out.println("reverse:" + reverseDigits(num));
                else if (ch == 3) {
                    System.out.println("largest:" + largest(num));
                }
                else  if(ch==4)
                    System.out.println("smallest:" + smallest(num));
                else if(ch==5)
                    System.out.println("average:"+average(num));
                else if(ch==6)
                    System.out.println("index position:"+findIndex(num));
                else if (ch==7)
                    System.out.println("string format:"+stringFormat(num));
                else if (ch==8)
                    System.out.println("mid element:"+midElement(num));
                else if (ch==9)
                    System.out.println("armstrong:"+armstrong(num));
                else if (ch==10)
                    System.out.println("total counts:"+totalCounts(num));
                else if (ch==11)
                    System.out.println("number repeated times:"+repetition(num));
                else if (ch==12)
                    System.out.println("After replacing:"+replacingNumber(num));
                else if(ch==13)
                   break;

                 else
                    System.out.println("enter valid choice");


            }


    }

    public static int sumDigits(BigInteger number)
    {
        BigInteger ten = BigInteger.TEN;
        int sum = 0;
        number = number.abs(); // Convert to positive for negative numbers

        while (!number.equals(BigInteger.ZERO)) {
            sum += number.mod(ten).intValue(); // Extract last digit
            number = number.divide(ten); // Remove last digit
        }
        return sum;

    }
    public static BigInteger reverseDigits(BigInteger num)
    {
        boolean isNegative=false;
        if(num.compareTo(BigInteger.ZERO)<0) {
            isNegative = true;
            num=num.multiply(BigInteger.valueOf(-1));
        }
        BigInteger rev=BigInteger.ZERO;
        while(!num.equals(BigInteger.ZERO))
        {
            rev=rev.multiply(BigInteger.TEN).add(num.mod(BigInteger.TEN));
            num=num.divide(BigInteger.TEN);
        }

        if (isNegative) {
            rev = rev.multiply(BigInteger.valueOf(-1)); // Corrected syntax
        }
        return rev;
    }
    public static int largest(BigInteger number)
    {
        BigInteger ten = BigInteger.TEN;
        int max = 0;
        number = number.abs(); // Convert to positive for negative numbers

        while (!number.equals(BigInteger.ZERO)) {
            max=Math.max(number.mod(ten).intValue(),max);// Extract last digit
            number = number.divide(ten); // Remove last digit
        }
        return  max;
    }
    public static int smallest(BigInteger number)
    {
        BigInteger ten = BigInteger.TEN;
        int min = Integer.MAX_VALUE;
        number = number.abs(); // Convert to positive for negative numbers

        while (!number.equals(BigInteger.ZERO)) {
            min=Math.min(number.mod(ten).intValue(),min);// Extract last digit
            number = number.divide(ten); // Remove last digit
        }
        return  min;
    }
    public static double average(BigInteger number)
    {
        BigInteger ten = BigInteger.TEN;
        BigInteger num=number;
        int sum = 0;
        number = number.abs(); // Convert to positive for negative numbers

        while (!number.equals(BigInteger.ZERO)) {
            sum += number.mod(ten).intValue(); // Extract last digit
            number = number.divide(ten); // Remove last digit
        }
        return sum/totalCounts(num);

    }
    public static int totalCounts(BigInteger number)
    {
        BigInteger ten = BigInteger.TEN;
       int count=0;
        number = number.abs(); // Convert to positive for negative numbers

        while (!number.equals(BigInteger.ZERO)) {
            count++; // Extract last digit
            number = number.divide(ten); // Remove last digit
        }
        return count;
    }
    public static ArrayList<Integer> findIndex(BigInteger number)
    {
        ArrayList<Integer> list = new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        int num=0;
        while(list.isEmpty()) {
            while(true)
            {
                try {
                    System.out.println("enter the number");
                    num = Integer.parseInt(sc.nextLine().trim());
                    if(isValid(num,number))
                    break;

                }
                catch (NumberFormatException e)
                {
                    System.out.println("enter valid number");


                }
            }


            BigInteger ten = BigInteger.TEN;
            int totalcounts = totalCounts(number);
            number = number.abs(); // Convert to positive for negative numbers

            while (!number.equals(BigInteger.ZERO)) {
                if (number.mod(ten).intValue() == num) {
                    list.add(totalcounts - 1);// Extract last digit
                }
                totalcounts--;
                number = number.divide(ten); // Remove last digit
            }
        }
        return list;
    }
    public  static String stringFormat(BigInteger number)
    {
        BigInteger ten = BigInteger.TEN;
      String str="";
        number = number.abs(); // Convert to positive for negative numbers

        while (!number.equals(BigInteger.ZERO)) {
            str= word(number.mod(ten).intValue())+" "+str; // Extract last digit
            number = number.divide(ten); // Remove last digit
        }
        return str;
    }
    public static String word(int n)
    {
        switch (n)
        {
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            default:
                return "zero";
        }
    }
    public static String armstrong(BigInteger number)
    {
        int power=totalCounts(number);
        BigInteger num=number;
        BigInteger ten = BigInteger.TEN;
        BigInteger sum =BigInteger.ZERO;
        number = number.abs(); // Convert to positive for negative numbers

        while (!number.equals(BigInteger.ZERO)) {
            BigInteger digit = number.mod(ten); // Extract last digit
            sum = sum.add(digit.pow(power));
            number = number.divide(ten); // Remove last digit
        }
        if(num.equals(sum))
            return "yes";
        else
            return "no";
    }
    public static ArrayList<Integer> repetition(BigInteger number)
    {
        int[] arr=new int[10];
        ArrayList<Integer> list=new ArrayList<>();
        BigInteger ten = BigInteger.TEN;

        number = number.abs(); // Convert to positive for negative numbers

        while (!number.equals(BigInteger.ZERO)) {
           arr[number.mod(ten).intValue()]++; // Extract last digit
            number = number.divide(ten); // Remove last digit
        }
        int num;
        Scanner sc=new Scanner(System.in);
        while(list.isEmpty()) {
            while(true)
            {
                try {
                    System.out.println("enter the number");
                    num = Integer.parseInt(sc.nextLine().trim());
                    break;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("enter valid number");


                }
            }
            if(arr[num]!=0)
                list.add(arr[num]);
            else
                System.out.println("enter the number present");


        }
        return list;

    }
    public static BigInteger replacingNumber(BigInteger number)
    {
        Scanner sc=new Scanner(System.in);
        int num;
        while(true)
        {
            try {
                System.out.println("enter the number to replace");
                num = Integer.parseInt(sc.nextLine().trim());

                if(isValid(num,number))
                break;
                else
                    System.out.println("enter valid number present");
            }
            catch (NumberFormatException e)
            {
                System.out.println("enter valid number");


            }
        }
        int anum;
        while(true)
        {
            try {
                System.out.println("enter the alternate number");
                anum= Integer.parseInt(sc.nextLine().trim());
                    break;

            }
            catch (NumberFormatException e)
            {
                System.out.println("enter valid number");


            }
        }
        String str=String.valueOf(number);
       str= str.replace(String.valueOf(num),String.valueOf(anum));
        BigInteger ans=new BigInteger(str);
        return ans;


    }
    public static boolean isValid(int num,BigInteger number)
    {

        BigInteger ten = BigInteger.TEN;
        int totalcounts = totalCounts(number);
        number = number.abs(); // Convert to positive for negative numbers

        while (!number.equals(BigInteger.ZERO)) {
            if (number.mod(ten).intValue() == num) {
               return true;
            }

            number = number.divide(ten); // Remove last digit
        }
        return false;

    }
    public static String midElement(BigInteger number)
    {
        String numStr = number.abs().toString();
        int length = numStr.length();

        // Calculate Mean (center of digit positions)
        double mean = (length - 1) / 2.0;



        // Find middle index based on mean
        int middleIndex = (int) Math.round(mean);

        // Determine middle digit(s)
        String middleDigit;
        if (length % 2 == 1) { // Odd-length number
            middleDigit = String.valueOf(numStr.charAt(middleIndex));
        } else { // Even-length number
            middleDigit = numStr.substring(middleIndex - 1, middleIndex + 1);
        }
        return middleDigit;
    }
}
