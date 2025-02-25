package programs;

import java.util.Scanner;

public class Name_Convention {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the number");
        int number= sc.nextInt();
      int digits=  countDigits(number);
      System.out.println(digits);

    }
    public static int countDigits(int number)
    {
        int count=0;
        while(number>0)
        {
            ++count;
            number/=10;

        }
        return count;
    }
}
