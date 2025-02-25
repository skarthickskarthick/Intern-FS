package programs;

import java.math.BigInteger;
import java.util.Scanner;

public class reverse_digits {
    public  static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        BigInteger num;
        while(true)
        {
            try {
                System.out.println("enter the number");
                num=sc.nextBigInteger();
                break;
            }
            catch (Exception e)
            {
                System.out.println("enter valid number");
                sc.next();

            }
        }
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
        System.out.println("the reverse of the number"+num+"is:  "+rev);
    }
}
