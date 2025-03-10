package programs;

import java.util.Scanner;
class Thread_implementation extends Thread
{
    int row;
    int matrix1[][],matrix2[][],result[][];
    Thread_implementation(int row,int[][] matrix1,int matrix2[][],int result[][])
    {
        this.row=row;

        this.matrix1=matrix1;
        this.matrix2=matrix2;
        this.result=result;
    }
   public  void run()
   {
       int columns1 = matrix1[0].length;
       int columns2 = matrix2[0].length;

       for (int j = 0; j < columns2; j++) {
           result[row][j] = 0; // Initialize
           for (int k = 0; k < columns1; k++) {
               result[row][j] += matrix1[row][k] * matrix2[k][j];
           }
       }
   }


}

public class Matrix_Multiplication_extends {
    public static void main(String args[])
    {
        int rows,columns;
        Scanner scanner=new Scanner(System.in);
            while (true) {
                try {
                    System.out.println("Enter the total number of rows ");
                    rows= Integer.parseInt(scanner.nextLine().trim());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Enter a valid number");
                }
            }

        while (true) {
            try {
                System.out.println("Enter the total number of columns ");
                columns= Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid number");
            }
        }

        System.out.println("Matrix 1");
        int[][] matrix1=new int[rows][columns];
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<columns;j++)
            {
               matrix1[i][j]= getInput(matrix1[i][j]);
            }
        }
        System.out.println("Matrix 2");
        int[][] matrix2=new int[rows][columns];
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<columns;j++)
            {
                matrix2[i][j]= getInput(matrix1[i][j]);
            }
        }
        int[][] result=new int[rows][columns];
        Thread[] threads=new Thread[rows];
        // Creating threads for each row
        System.out.println("Thread Details");
        for(int i=0;i<rows;i++)
        {
            threads[i]=new Thread_implementation(i,matrix1,matrix2,result);
            System.out.println(threads[i].getName()+"     "+Thread.currentThread().getName());
            threads[i].start();

        }

        // Wait for all threads to finish

        for (int i = 0; i < rows; i++) {
            try {

                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Display result
        System.out.println("Result Matrix:");
        for (int[] row : result) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }


    }

    public static int getInput(int n)
    {
        Scanner scanner=new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter the element ");
                n= Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid number");
            }
        }
        return n;
    }
}
