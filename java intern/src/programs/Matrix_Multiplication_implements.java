package programs;

import java.util.Scanner;

class Matrix_Implementation implements Runnable
{
    int row,column;
    int[][] matrix1,matrix2,result;
    Matrix_Implementation(int row,int column,int[][] matrix1,int[][] matrix2,int[][]  result)
    {
        this.row=row;
        this.column=column;
        this.matrix1=matrix1;
        this.matrix2=matrix2;
        this.result=result;

    }

    public void run()
    {
        int sum = 0;
        for (int k = 0; k < matrix1[0].length; k++) {
            sum += matrix1[row][k] * matrix2[k][column];
        }
        result[row][column] = sum;

    }
}

public class Matrix_Multiplication_implements {
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
        Thread[][] threads=new Thread[rows][columns];
         //Create thread for each element
        System.out.println("Thread Details");
        for(int i=0;i<rows;i++)
        {
            for (int j=0;j<columns;j++)
            {
                threads[i][j]=new Thread(new Matrix_Implementation(i,j,matrix1,matrix2,result));
                threads[i][j].start();
                System.out.println(threads[i][j].getName()+"     "+Thread.currentThread().getName());

            }
        }
        //Wait for all thread to complete

        for(int i=0;i<result.length;i++)
        {
            for (int j=0;j<result.length;j++)
            {
                        try {
                            threads[i][j].join();
                        }
                catch (InterruptedException e)
                {
                    System.out.println(e);
                }

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
