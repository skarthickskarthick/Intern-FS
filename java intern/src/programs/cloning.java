package programs;

import java.util.ArrayList;
import java.util.Scanner;

public class cloning {
    static int id = 1;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int nofStudent = 0;

        while (true) {
            System.out.println("Enter the number of students:");
            String input = sc.nextLine().trim();
            try {
                nofStudent = Integer.parseInt(input);
                if (nofStudent <= 100 && nofStudent >= 1) {
                    break;
                } else {
                    System.out.println("Error: Number must be between 1 and 100");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input! Enter a number between 1 and 100");
            }
        }

        ArrayList<Student> students = new ArrayList<>();

        for (int i = 0; i < nofStudent; i++) {
            Student s = new Student();
            s.c = new College();
            System.out.println("Enter the details of student " + (i + 1) + ":");

            while (true) {
                System.out.print("Enter the name: ");
                s.name = sc.nextLine().trim();
                if (s.name.matches("[A-Za-z]+( [A-Za-z]+)*")) {
                    break;
                } else {
                    System.out.println("Error: Invalid name");
                }
            }

            while (true) {
                System.out.print("Enter your roll Number: ");
                s.rollno = sc.nextLine().trim();
                if (s.rollno.matches("\\d{7}")) {
                    break;
                } else {
                    System.out.println("Error: Invalid roll number. It must be a 7-digit number.");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter your Age: ");
                    s.age = Integer.parseInt(sc.nextLine().trim());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Enter a valid number for age.");
                }
            }

            while (true) {
                System.out.print("Enter the college name: ");
                s.c.name = sc.nextLine().trim();
                if (s.c.name.matches("[A-Za-z]+( [A-Za-z]+)*")) {
                    break;
                } else {
                    System.out.println("Error: Invalid college name");
                }
            }

            while (true) {
                System.out.print("Enter the college location: ");
                s.c.location = sc.nextLine().trim();
                if (s.c.location.matches("[A-Za-z]+( [A-Za-z]+)*")) {
                    break;
                } else {
                    System.out.println("Error: Invalid location name");
                }
            }

            for (int j = 1; j <= 3; j++) {
                while (true) {
                    System.out.println("Enter subject " + j + " marks:");
                    String markInput = sc.nextLine().trim();
                    try {
                        int mark = Integer.parseInt(markInput);
                        if (mark <= 100 && mark >= 1) {
                            s.sum += mark;
                            s.marks.add(mark);
                            break;
                        } else {
                            System.out.println("Error: Marks must be between 1 and 100");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Enter a valid number between 1 and 100");
                    }
                }
            }

            s.average = s.sum / 3.0;
            s.c.id = id++;
            students.add(s);
        }

        System.out.printf("%-15s %-10s %-5s %-10s %-25s %-15s %-10s %-10s\n",
                "Name", "Roll No", "Age", "College ID", "College (Location)", "Marks", "Total", "Average");

        ArrayList<Student> clonedStudents = new ArrayList<>();
        for (Student student : students) {
            try {
                clonedStudents.add(student.clone()); // Clone each student
            } catch (CloneNotSupportedException e) {
                System.out.println("Cloning failed!");
            }
        }


        for (Student student : clonedStudents) {
            student.displayStudentDetails();
        }

        sc.close();
    }
}

class Student implements Cloneable {
    String rollno;
    String name;
    int age;
    int sum;
    double average;
    ArrayList<Integer> marks = new ArrayList<>();
    College c;

    public void displayStudentDetails() {
        System.out.printf("%-15s %-10s %-5d %-10d %-25s %-15s %-10d %-10.2f\n",
                name, rollno, age, c.id, c.name + " (" + c.location + ")", marks, sum, average);
    }

    @Override
    protected Student clone() throws CloneNotSupportedException {
        Student cloned = (Student) super.clone(); // Shallow copy
        cloned.marks = new ArrayList<>(this.marks); // Deep copy of list
        cloned.c = this.c.clone(); // Deep copy of College object
        return cloned;
    }
}

class College implements Cloneable {
    int id;
    String name;
    String location;

    @Override
    protected College clone() throws CloneNotSupportedException {
        return (College) super.clone();
    }
}
