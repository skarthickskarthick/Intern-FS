package programs;
import java.util.Scanner;
public class add {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int sum=0,a=-1;
        while(a!=0) {
            System.out.println("enter the number or enter 0 to exit");
            a = sc.nextInt();
            sum += a;
        }
        System.out.println("sum="+sum);
    }
}