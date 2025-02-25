package programs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Employee
{
    String employeeID;
    String employeeName;
    String Designation;
    double basicPay;
    abstract public void getDetail();
    abstract public void print();
    abstract public void calculateSalary();


}

class Salary extends Employee
{
    Scanner scanner=new Scanner(System.in);



   public void getDetail()
   {
       while (true) {
           System.out.print("Enter the employee id ");
         employeeID = scanner.nextLine();
           if (employeeID.matches("^[A-Za-z0-9]+$")) {
               break;
           } else {
               System.out.println("Error: Invalid string");
           }
       }

       while (true) {
       System.out.print("Enter the  employee name: ");
       employeeName = scanner.nextLine();
       if (employeeName.matches("[A-Za-z]+( [A-Za-z]+)*")) {
           break;
       } else {
           System.out.println("Error: Invalid name");
       }
   }

       while (true) {
       System.out.print("Enter the Designation: ");
       Designation = scanner.nextLine();
       if (Designation.matches("[A-Za-z]+( [A-Za-z]+)*")) {
           break;
       } else {
           System.out.println("Error: Invalid designation");
       }
   }

       while(true)
       {
           try {
               System.out.println("enter the basic pay");
               basicPay= Double.parseDouble(scanner.nextLine());
               break;
           }
           catch (NumberFormatException e)
           {
               System.out.println("enter valid number");
           }
       }



   }
   double houserentAllowance;
   double conveyenceAlowance;
   double educationAllowance;
   double grossPay;
   double netPay;
   double providendFund;
   double tax;
   public void calculateSalary()
   {
        houserentAllowance=0.01*basicPay;
        conveyenceAlowance=0.02*basicPay;
        educationAllowance=0.01*basicPay;
        providendFund=0.1*basicPay;
       tax=0.02*basicPay;
       grossPay=houserentAllowance+conveyenceAlowance+educationAllowance+basicPay;
       netPay=grossPay-providendFund-tax;

   }
   public void print()
   {
       System.out.printf("%-15s%-25s%-20s%-15.2f%-30.2f%-30.2f%-30.2f%-30.2f\n",
               employeeID, employeeName, Designation, basicPay,
               houserentAllowance,conveyenceAlowance, educationAllowance , grossPay);

       // Print net pay
       System.out.printf("\n%-15s%-25s%-20s%-15s%-30s%-30s%-30s%-30s\n",
               "", "", "", "", "", "", "Net Pay", netPay);

   }

}
public class Employee_Payslip {
    public static void main(String args[])
    {
        Scanner scanner=new Scanner(System.in);
        ArrayList<Employee> list=new ArrayList<>();
        int nofEmployees;
        while(true)
        {
            try {
                System.out.println("enter the no of employees");
                nofEmployees= Integer.parseInt(scanner.nextLine());
                if(nofEmployees>=1&&nofEmployees<=6)
                break;
                else
                    System.out.println("enter the value less than 6");
            }
            catch (NumberFormatException e)
            {
                System.out.println("enter valid number");
            }
        }
            for(int i=1;i<=nofEmployees;i++)
            {
                System.out.println("enter details of employee "+i);
                Employee e=new Salary();
                e.getDetail();
                list.add(e);
            }
        System.out.printf("%-15s%-25s%-20s%-15s%-30s%-30s%-30s%-30s\n",
                "Employee ID", "Employee Name", "Designation", "Basic Pay",
                "House Rent Allowance", "Conveyance Allowance",
                "Education Allowance", "Gross Pay");
            for(Employee e:list)
            {
                e.calculateSalary();

                e.print();
            }
    }
}
