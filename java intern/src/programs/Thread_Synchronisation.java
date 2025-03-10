package programs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class BankAccount
{
    private double balance;
    private final List<String> transactionHistory = new ArrayList<>();
    BankAccount(double balance)
    {
        this.balance=balance;

    }
    public synchronized void deposit(double amount)
    {
        balance+=amount;
        String transaction = String.format("%-15s | %-10s | %-10.2f | %-15.2f",
                Thread.currentThread().getName(), "Deposit", amount, balance);
        transactionHistory.add(transaction);

        System.out.println("Amount Deposited");
        System.out.println("Available Balance: "+balance);
        System.out.println();
    }
    public void withdraw(double amount)
    {
        synchronized (this)
        {
            if(balance>=amount)
            {
                balance-=amount;
                String transaction = String.format("%-15s | %-10s | %-10.2f | %-15.2f",
                        Thread.currentThread().getName(), "Withdraw", amount, balance);
                transactionHistory.add(transaction);
                System.out.println("Amount withdrawn");
                System.out.println("Available Balance: "+balance);
                System.out.println();
            }
            else
            {
                String transaction = String.format("%-15s | %-10s | %-10.2f | %-15s",
                        Thread.currentThread().getName(), "Withdraw", amount, "Failed (Insufficient)");
                transactionHistory.add(transaction);
                System.out.println("Insufficient Balance!");
                System.out.println("Available Balance: "+balance);
                System.out.println();

            }
        }
    }
    public synchronized List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
}
public class Thread_Synchronisation {
    public static void main(String args[])
    {
        Scanner scanner=new Scanner(System.in);
       double amount;
        while (true) {
            try {
                System.out.println("Enter account opening balance: ");
                amount= Double.parseDouble(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid amount.");
            }
        }
        BankAccount account=new BankAccount(amount);
        int noofCustomers;
        while (true) {
            try {
                System.out.println("Enter the number of transactions: ");
                noofCustomers= Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid number ");
            }
        }
        ArrayList<Thread> threadList=new ArrayList<>();
        Thread thread;
        for(int i=1;i<=noofCustomers;i++) {
            System.out.println("Transaction: "+i);
            char state;
            while (true) {
                System.out.println("Enter d- deposit w- withdrawal: ");
                String input = scanner.nextLine().trim().toLowerCase(); // Read input, trim spaces, and convert to lowercase
                if (input.equals("d") || input.equals("w")) {
                    state = input.charAt(0); // Store the valid input
                    break;
                } else {
                    System.out.println("Error: Enter only 'd' for deposit or 'w' for withdrawal.");
                }
            }
            double money;
            while (true) {
                try {
                    System.out.println("Enter the amount: ");
                    money= Double.parseDouble(scanner.nextLine().trim());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Enter a valid number ");
                }
            }
            if(state=='d')
            {
                double finalMoney = money;
                thread = new Thread(() -> {
                    account.deposit(finalMoney);
                }, "Transaction "+i);
            }
            else
            {
                double finalMoney = money;
                thread = new Thread(() -> {
                    account.withdraw(finalMoney);
                }, "Transaction "+i);
            }
            thread.start();
            threadList.add(thread);
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Print transaction history in a table format
        System.out.println("\n===== Transaction History =====");
        System.out.printf("%-15s | %-10s | %-10s | %-15s\n", "Transaction", "Type", "Amount", "Balance");
        System.out.println("------------------------------------------------------------");
        for (String transaction : account.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
}

