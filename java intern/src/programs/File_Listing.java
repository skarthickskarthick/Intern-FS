package programs;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class File_Listing
{
        public static void main(String[] args) {
            // 📂 Specify the directory path (Change this to your desired directory)
            String directoryPath = "D:\\Karthick\\java\\Placement Study Material";



            Scanner scanner=new Scanner(System.in);

            while (true) {
                System.out.print("Enter a valid directory path: ");
                directoryPath = scanner.nextLine().trim();

                // Check if the input is not empty and is a valid directory
                if (!directoryPath.isEmpty() && Files.exists(Path.of(directoryPath)) && Files.isDirectory(Path.of(directoryPath))) {
                    break; // Valid directory, exit loop
                } else {
                    System.out.println("❌ Error: Invalid path! Please enter a valid directory.");
                }
            }

            // Create a File object
            File directory = new File(directoryPath);

            // Check if the directory exists
            if (directory.exists() && directory.isDirectory()) {
                System.out.println("📁 Files in directory: " + directoryPath);
                listFilesRecursively(directory, 0); // Start recursion with level 0
            } else {
                System.out.println("❌ The specified path is not a valid directory.");
            }
        }

        // Recursive method to list files and directories
        public static void listFilesRecursively(File dir, int level) {
            File[] files = dir.listFiles(); // Get files and directories

            if (files != null) {
                for (File file : files) {
                    // Print indentation based on the depth level
                    for (int i = 0; i < level; i++) {
                        System.out.print("    "); // 4 spaces indentation
                    }

                    if (file.isDirectory()) {
                        System.out.println("📂 " + file.getName());
                        // Recursively list files inside subdirectory
                        listFilesRecursively(file, level + 1);
                    } else {
                        System.out.println("📄 " + file.getName());
                    }
                }
            }
        }
    }


