package programs;

import java.util.ArrayList;
import java.util.Scanner;

class Customer
{
    int customerID;
    String customerName;
    int bankID;
    int loanID;

    double principal;
    double annualRate;
    int tenureInYears;
    double emi;
    double finalAmount;
    double interest;
    Scanner scanner=new Scanner(System.in);
    public void getDetail(ArrayList<Bank> banksList)
    {
        while (true) {
            try {

                System.out.println("enter the customer id: ");
                customerID = Integer.parseInt(scanner.nextLine());
                    break;

            } catch (NumberFormatException e) {
                System.out.println("Invalid Format!");
            }


        }

        while (true) {
            System.out.print("Enter the  customer name: ");
            customerName = scanner.nextLine();
            try {
                if (customerName.matches("[A-Za-z]+( [A-Za-z]+)*")) {
                    break;
                } else {
                    System.out.println("Error: Invalid name");
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

        }

        while (true) {
            try {
                System.out.println("1-SBI 2-Axis Bank 3-HDFC Bank");
                System.out.println("enter the bank name by  bank numer: ");
                bankID = Integer.parseInt(scanner.nextLine());
                if (bankID >= 1 && bankID <=3)
                {

                    annualRate=banksList.get(bankID-1).rateInYears;
                    break;
                }

                else
                    System.out.println("enter valid number");
            } catch (NumberFormatException e) {
                System.out.println("Invalid Format!");
            }


        }
        while (true) {
            try {

                System.out.println("enter the principal amount: ");
                principal = Double.parseDouble(scanner.nextLine());

                    break;

            } catch (NumberFormatException e) {
                System.out.println("Invalid Format!");
            }


        }
        while (true) {
            try {

                System.out.println("enter the time period(years): ");
                tenureInYears = Integer.parseInt(scanner.nextLine());

                    break;

            } catch (NumberFormatException e) {
                System.out.println("Invalid Format!");
            }


        }
    }
    public void calculateEMI()
    {

    }

    @Override
    public String toString() {
        return "Customer ID: " + customerID + ", Name: " + customerName +
                ", Bank ID: " + bankID + ", Loan ID: " + loanID +
                ", Principal: " + principal + ", Rate: " + annualRate +
                "%, Tenure: " + tenureInYears + " years";
    }

}
class Personalloan extends Customer
{

    double monthlyRate;
    int tenureInMonths;
    public void calculateEMI()
    {
       monthlyRate = annualRate / 12 / 100;

        // Convert tenure from years to months
         tenureInMonths = tenureInYears * 12;

        // EMI Formula: (P * r * (1 + r)^n) / ((1 + r)^n - 1)
        emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, tenureInMonths)) /
                (Math.pow(1 + monthlyRate, tenureInMonths) - 1);
        finalAmount=emi*tenureInMonths;
        interest=finalAmount-principal;
    }


}

class Bank
{
    int bankID;
    String bankName;
    double rateInYears;
    ArrayList<Customer> customersList=new ArrayList<>();
    int loanID;
    Bank(int bankID,String bankName,double rateInYears)
    {
        this.bankID=bankID;
        this.bankName=bankName;
        this.rateInYears=rateInYears;
    }

}
public class Loan_Calculation {
    public static void main(String args[])
    {

        Scanner scanner=new Scanner(System.in);
     Customer customer;
        ArrayList<Bank> banksList=new ArrayList<>();

        banksList.add(new Bank(1,"SBI",10));
        banksList.add(new Bank(2,"Axis Bank",11));
        banksList.add(new Bank(3,"HDFC Bank",12));
        int loanchoice,nofCustomers;

        while (true) {
            try {

                System.out.println("enter the total number of customers");
                nofCustomers = Integer.parseInt(scanner.nextLine());
                if (nofCustomers >= 1 && nofCustomers <= 6)
                    break;
                else
                    System.out.println("enter number less than 6");
            } catch (NumberFormatException e) {
                System.out.println("Invalid Format!");
            }


        }

     for(int i=1;i<=nofCustomers;i++)
     {
System.out.println("enter the details of the customer-"+i);


           customer= new Personalloan();
           customer.loanID=i;

           customer.getDetail(banksList);

             customer.calculateEMI();

             banksList.get(customer.bankID-1).customersList.add(customer);
             //System.out.println(banksList.get(0).customersList.get(0));
     }


        for (Bank b : banksList) {
            if (!b.customersList.isEmpty()) { // Only print banks with customers
                System.out.println("\nBank Name: " + b.bankName + " | Interest Rate: " + b.rateInYears + "%");
                System.out.println("------------------------------------------------------------");
                System.out.printf("%-10s %-15s %-10s %-12s %-8s %-10s %-10s %-10s\n",
                        "Cust ID", "Name", "Loan ID", "Principal", "Tenure", "EMI", "Final Amt","Interest");


                for (Customer c : b.customersList) {
                    System.out.printf("%-10d %-15s %-10d %-12.2f %-8d %-10.2f %-10.2f %-10.2f\n",
                            c.customerID, c.customerName, c.loanID, c.principal,
                            c.tenureInYears, c.emi, c.finalAmount,c.interest);
                }
                System.out.println("------------------------------------------------------------\n");
            }
        }


    }
}
