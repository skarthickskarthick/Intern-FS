package programs;

import java.util.ArrayList;
import java.util.Scanner;


public class Student_Marklist {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
      int n=0;
        while(true)
        {
            System.out.println("enter the number of students");
            String input = sc.nextLine().trim();
            try {
                 n=Integer.parseInt(input);
                if (n<=100&&n>=1) {
                    // Exit loop if valid

                    break;
                } else {
                    System.out.println("Error: Number must be between the given range");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input! Enter a number between the given range");

            }

        }

        ArrayList<Student> students=new ArrayList<>();

        for(int i=0;i<n;i++)
        {

            Student s=new Student();
            System.out.println("enter the  details of student"+(i+1));
                        while(true)
            {
                System.out.println("subject 1 marks");
                String input = sc.nextLine().trim();
                try {
                   int mark=Integer.parseInt(input);
                    if (mark<=100&&mark>=1) {
                        // Exit loop if valid
                        s.sum+=mark;
                        s.marks.add(mark);
                        break;
                    } else {
                        System.out.println("Error: Number must be between the given range");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid input! Enter a number between the given range");

                }

            }
            while(true)
            {
                System.out.println("subject 2 marks");
                String input = sc.nextLine().trim();
                try {
                    int mark=Integer.parseInt(input);
                    if (mark<=100&&mark>=1) {
                        // Exit loop if valid
                        s.sum+=mark;
                        s.marks.add(mark);
                        break;
                    } else {
                        System.out.println("Error: Number must be between the given range");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid input! Enter a number between the given range");

                }

            }
            while(true)
            {
                System.out.println("subject 3 marks");
                String input = sc.nextLine().trim();
                try {
                    int mark=Integer.parseInt(input);
                    if (mark<=100&&mark>=1) {
                        // Exit loop if valid
                        s.sum+=mark;
                        s.marks.add(mark);
                        break;
                    } else {
                        System.out.println("Error: Number must be between the given range");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid input! Enter a number between the given range");

                }

            }
            s.average=s.sum/3;
            students.add(s);
        }
        System.out.printf("%-30s %-10s %-10s %-10s %-10s  %-10s%n",
                "Name", "Subject1", "Subject2", "Subject3", "Total Marks","Average Marks");
        for(int i=0;i<students.size();i++)
        {
           System.out.printf("%-30s %-10d %-10d %-10d %-10d   %-10.2f%n",
                   students.get(i).name,
                   students.get(i).marks.get(0),
                   students.get(i).marks.get(1),
                   students.get(i).marks.get(2),
                   students.get(i).sum,
                   students.get(i).average);
        }

    }
    public static class  Student
    {
        String name;
        ArrayList<Integer> marks=new ArrayList<>();
        int sum;
        double average;

    }

}
