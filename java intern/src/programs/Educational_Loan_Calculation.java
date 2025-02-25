package programs;
import java.util.ArrayList;
import java.util.Scanner;

class Customers
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
    int courseDuration;
    boolean isSubsidued;
    int subsidued;
    Scanner scanner=new Scanner(System.in);
    public void getDetail(ArrayList<Banks> banksList)
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
        while (true) {
            try {

                System.out.println("enter the duration of the course: ");
                courseDuration = Integer.parseInt(scanner.nextLine());

                break;

            } catch (NumberFormatException e) {
                System.out.println("Invalid Format!");
            }


        }

        while (true) {
            try {

                System.out.println("Are you subsidued by government 1-yes 2-no ");
                 subsidued= Integer.parseInt(scanner.nextLine());
                if(subsidued==1)
                {
                    isSubsidued=true;
                    break;

                }
                else if(subsidued==2)
                {
                    isSubsidued=false;
                    break;
                }

                else
                    System.out.println("enter valid inputs either 1 or 2");

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
class Educational_Loan extends Customers
{

    double monthlyRate;
    int tenureInMonths;
    public void calculateEMI()
    {

        if (isSubsidued) {
            annualRate -= 2;  // Example: Reduce interest rate by 2% for subsidized loans
        }
        if(!isSubsidued)
        {
            // Calculate extra interest accumulated during grace period (Simple Interest)
            double interestDuringGrace = (principal * annualRate * courseDuration) / (12 * 100);

            // New principal after grace period
            double newPrincipal = principal + interestDuringGrace;
        }

        double monthlyRate = annualRate / 12 / 100;
        int totalMonths = (tenureInYears * 12) + courseDuration; // Grace period is included

        emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, totalMonths)) /
                (Math.pow(1 + monthlyRate, totalMonths) - 1);
        finalAmount = emi * totalMonths;
        interest = finalAmount - principal;
    }


}

class Banks
{
    int bankID;
    String bankName;
    double rateInYears;
    ArrayList<Customers> customersList=new ArrayList<>();
    int loanID;
    Banks(int bankID,String bankName,double rateInYears)
    {
        this.bankID=bankID;
        this.bankName=bankName;
        this.rateInYears=rateInYears;
    }

}
class Educational_Loan_Calculation {
    public static void main(String args[])
    {

        Scanner scanner=new Scanner(System.in);
        Customers customer;
        ArrayList<Banks> banksList=new ArrayList<>();

        banksList.add(new Banks(1,"SBI",8));
        banksList.add(new Banks(2,"Axis Bank",9));
        banksList.add(new Banks(3,"HDFC Bank",10));
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


            customer= new Educational_Loan();
            customer.loanID=i;

            customer.getDetail(banksList);

            customer.calculateEMI();

            banksList.get(customer.bankID-1).customersList.add(customer);
            //System.out.println(banksList.get(0).customersList.get(0));
        }


        for (Banks b : banksList) {
            if (!b.customersList.isEmpty()) { // Only print banks with customers
                System.out.println("\nBank Name: " + b.bankName + " | Interest Rate: " + b.rateInYears + "%");
                System.out.println("------------------------------------------------------------");
                System.out.printf("%-10s %-15s %-10s %-12s %-8s %-10s %-10s %-10s\n",
                        "Cust ID", "Name", "Loan ID", "Principal", "Tenure", "EMI", "Final Amt","Interest");


                for (Customers c : b.customersList) {
                    System.out.printf("%-10d %-15s %-10d %-12.2f %-8d %-10.2f %-10.2f %-10.2f\n",
                            c.customerID, c.customerName, c.loanID, c.principal,
                            c.tenureInYears, c.emi, c.finalAmount,c.interest);
                }
                System.out.println("------------------------------------------------------------\n");
            }
        }


    }
}
