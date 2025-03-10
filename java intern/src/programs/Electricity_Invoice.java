package programs;
import java.util.ArrayList;
import java.util.Scanner;
interface Consumer
{
    Scanner scanner=new Scanner(System.in);
    default void getDetail()
    {
        int consumerID;
        String consumerName,phone;
        while (true) {
            try {
                System.out.println("enter the consumer id: ");
              consumerID = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Format!");
            }
        }
        while (true) {
            System.out.print("Enter the  consumer name: ");
           consumerName = scanner.nextLine();
            try {
                if (consumerName.matches("[A-Za-z]+( [A-Za-z]+)*")) {
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
            System.out.print("Enter your Phone Number: ");
           phone = scanner.nextLine().trim();
            try {
                if (phone.matches("\\d{10}")) {
                    // Exit loop if valid
                    break;
                } else {
                    System.out.println("Error: Invalid number");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input! Enter a valid one ");
            }
        }
        setDetail(consumerID,consumerName,phone);
    }
    void setDetail(int consunerID,String consumerName,String phone);
    //void calculateBill();
    void printDetails();
}
class Domestic_Bill implements Consumer
{
    int consumerID;
    String consumerName;
    String phone;
    double units;
    double totalAmount = 0;
    void gettingDetails()
    {
        getDetail();
        while (true) {
            try {
                System.out.println("enter the units consumed: ");
                units = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Format!");
            }
        }
    }
    public void setDetail(int consumerID,String consumerName,String phone)
    {
        this.consumerID=consumerID;
        this.consumerName=consumerName;
        this.phone=phone;
    }
    public void calculateBill()
    {
        if (units <= 100) {
            totalAmount = 0; // Free for first 100 units
        }
        else if (units <= 200) {
            totalAmount = (units - 100) * 2.25;
        }
        else if (units <= 400) {
            totalAmount = (100 * 2.25) + (units - 200) * 4.50;
        }
        else if (units <= 500) {
            totalAmount = (100 * 2.25) + (200 * 4.50) + (units - 400) * 6.00;
        }
        else {
            totalAmount = (100 * 2.25) + (200 * 4.50) + (100 * 6.00) + (units - 500) * 8.00;
        }
    }
    public void printDetails() {
        System.out.printf("| %-10d | %-15s | %-12s | %-5.2f | %-15s | %-10.2f |\n",
                consumerID, consumerName, phone, units,"Domestic", totalAmount);
    }
}
class Commercial_Bill implements Consumer
{
    int consumerID;
    String consumerName;
    String phone;
    double units;
    double totalAmount = 0;
    void gettingDetails()
    {
        getDetail();
        while (true) {
            try {
                System.out.println("enter the units consumed: ");
                units = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Format!");
            }
        }
    }
    public void setDetail(int consumerID,String consumerName,String phone)
    {
        this.consumerID=consumerID;
        this.consumerName=consumerName;
        this.phone=phone;
    }
    public void calculateBill()
    {
        totalAmount = units * 7.50; // Flat rate for commercial connections
    }
    public void printDetails() {
        System.out.printf("| %-10d | %-15s | %-12s | %-5.2f | %-15s | %-10.2f |\n",
                consumerID, consumerName, phone, units,"Commercial", totalAmount);
    }
}

class Industry_Bill implements Consumer
{
    int consumerID;
    String consumerName;
    String phone;
    double units;
    double totalAmount = 0;
    void gettingDetails()
    {
        getDetail();
        while (true) {
            try {
                System.out.println("enter the units consumed: ");
                units = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Format!");
            }
        }
    }
    public void setDetail(int consumerID,String consumerName,String phone)
    {
        this.consumerID=consumerID;
        this.consumerName=consumerName;
        this.phone=phone;
    }
    public void calculateBill()
    {
        totalAmount = units * 5.50; // Lower than commercial but higher than domestic
    }
    public void printDetails() {
        System.out.printf("| %-10d | %-15s | %-12s | %-5.2f | %-15s | %-10.2f |\n",
                consumerID, consumerName, phone, units,"Industrial", totalAmount);
    }
}
class Agriculture_Bill implements Consumer
{
    int consumerID;
    String consumerName;
    String phone;
    double units;
    double totalAmount = 0;
    void gettingDetails()
    {
        getDetail();
        while (true) {
            try {
                System.out.println("enter the units consumed: ");
                units = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Format!");
            }
        }
    }
    public void setDetail(int consumerID,String consumerName,String phone)
    {
        this.consumerID=consumerID;
        this.consumerName=consumerName;
        this.phone=phone;
    }
    public void calculateBill()
    {
        totalAmount = 0; // Lower than commercial but higher than domestic
    }
    public void printDetails() {
        System.out.printf("| %-10d | %-15s | %-12s | %-5.2f | %-15s | %-10.2f |\n",
                consumerID, consumerName, phone, units,"Agriculture", totalAmount);
    }
}
class Government_Offices_Bill implements Consumer
{
    int consumerID;
    String consumerName;
    String phone;
    double units;
    double totalAmount = 0;
    void gettingDetails()
    {
        getDetail();
        while (true) {
            try {
                System.out.println("enter the units consumed: ");
                units = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Format!");
            }
        }
    }
    public void setDetail(int consumerID,String consumerName,String phone)
    {
        this.consumerID=consumerID;
        this.consumerName=consumerName;
        this.phone=phone;
    }
    public void calculateBill()
    {
        totalAmount = units * 4.00; // Special lower rate for government offices
    }
    public void printDetails() {
        System.out.printf("| %-10d | %-15s | %-12s | %-5.2f | %-15s | %-10.2f |\n",
                consumerID, consumerName, phone, units,"Government", totalAmount);
    }
}
class Temporary_Bill implements Consumer
{
    int consumerID;
    String consumerName;
    String phone;
    double units;
    double totalAmount = 0;
    void gettingDetails()
    {
        getDetail();
        while (true) {
            try {
                System.out.println("enter the units consumed: ");
                units = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Format!");
            }
        }
    }
    public void setDetail(int consumerID,String consumerName,String phone)
    {
        this.consumerID=consumerID;
        this.consumerName=consumerName;
        this.phone=phone;
    }
    public void calculateBill()
    {
        totalAmount = units * 10.00; // Higher rate for short-term use
    }
    public void printDetails() {
        System.out.printf("| %-10d | %-15s | %-12s | %-5.2f | %-15s | %-10.2f |\n",
                consumerID, consumerName, phone, units,"Temporary", totalAmount);
    }
}
public class Electricity_Invoice {
    public static void main(String args[])
    {
        Scanner scanner=new Scanner(System.in);
        int choice,noofConsumers;
        ArrayList<Consumer> consumerlist=new ArrayList<>();
        while (true) {
            try {
                System.out.println("enter the total number of consumers");
                noofConsumers = Integer.parseInt(scanner.nextLine());
                if (noofConsumers >= 1 && noofConsumers <= 10)
                    break;
                else
                    System.out.println("enter number less than 10");
            } catch (NumberFormatException e) {
                System.out.println("Invalid Format!");
            }
        }
        for(int i=1;i<=noofConsumers;i++) {
            System.out.println("enter the details for consumer" +i);
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
            if(choice==1)
            {
                Consumer domestic=new Domestic_Bill();

                ( (Domestic_Bill) domestic).gettingDetails();
                ((Domestic_Bill)domestic).calculateBill();
                consumerlist.add(domestic);
            }
            else if (choice==2)
            {
                Consumer commercial=new Commercial_Bill();

                ( (Commercial_Bill) commercial).gettingDetails();
                ((Commercial_Bill)commercial).calculateBill();
                consumerlist.add(commercial);
            }
            else if(choice ==3)
            {
                Consumer industrial=new Industry_Bill();

                ( (Industry_Bill) industrial).gettingDetails();
                ((Industry_Bill)industrial).calculateBill();
                consumerlist.add(industrial);
            }
            else if(choice==4)
            {
                Consumer agri=new Agriculture_Bill();

                ( (Agriculture_Bill) agri).gettingDetails();
                ((Agriculture_Bill)agri).calculateBill();
                consumerlist.add(agri);
            }
            else if(choice==5)
            {
                Consumer govern=new Government_Offices_Bill();

                ( (Government_Offices_Bill) govern).gettingDetails();
                ((Government_Offices_Bill)govern).calculateBill();
                consumerlist.add(govern);
            }
            else if(choice==6)
            {
                Consumer temp=new Temporary_Bill();

                ( (Temporary_Bill) temp).gettingDetails();
                ((Temporary_Bill)temp).calculateBill();
                consumerlist.add(temp);
            }
            else
                break;
        }
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("| Consumer ID | Name            | Phone Number | Units | Type            | Bill Amount|");
        System.out.println("-------------------------------------------------------------");
        for (Consumer consumer : consumerlist) {
            consumer.printDetails();
        }
        System.out.println("-------------------------------------------------------------");
    }
}