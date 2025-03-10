package programs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class String_Operations {
    public static void main(String args[])
    {
        Scanner scanner=new Scanner(System.in);
        String word1,word2;
        while (true) {
            System.out.print("Enter the string1: ");
            word1 = scanner.nextLine().trim();
            if (word1.matches("\\S+")) {
                break;
            } else {
                System.out.println("Error: Invalid string");
            }
        }

        while (true) {
            System.out.print("Enter the string2: ");
           word2 = scanner.nextLine().trim();

            // Allow any characters (alphabets, numbers, special characters, spaces)
            if (word2.matches("\\S+")) {
                break;
            } else {
                System.out.println("Error: Invalid string");
            }
        }
        applyStringMethods(word1);
        compareStrings(word1,word2);
        vowelsCount(word1);
        findUppercase(word1);
        findLowercase(word1);
        extractNumbers(word1);
        alternatePattern(word1);

        char c;
        while (true) {
            System.out.print("Enter the character for frequency: ");
            String input = scanner.nextLine();

            // Check if the input is a single character
            if (input.length() == 1 && input.matches("[A-Za-z]")) {
                c = input.charAt(0);

                // Check if the character is present in str1
                if (word1.contains(String.valueOf(c))) {
                    break;
                } else {
                    System.out.println("Error: Character not found in the given string. Try again.");
                }
            } else {
                System.out.println("Error: Invalid input. Enter a single alphabet character.");
            }
        }
        findFrequency(c,word1);
        findBlankspaces(word1);

    }


    public static void applyStringMethods(String inputString) {
        System.out.println("\nApplying Java Inbuilt String Methods:");

        // Substring (First 5 chars if available)
        System.out.println("substring(0, 5): " + (inputString.length() >= 5 ? inputString.substring(0, 5) : inputString));

        // Index of a specific character
        System.out.println("indexOf('a'): " + inputString.indexOf('a'));

        // Replace character
        System.out.println("replace('a', '*'): " + inputString.replace('a', '*'));

        // Convert to Uppercase
        System.out.println("toUpperCase(): " + inputString.toUpperCase());

        // Convert to Lowercase
        System.out.println("toLowerCase(): " + inputString.toLowerCase());

        // Trim leading and trailing spaces
        System.out.println("trim(): " + inputString.trim());

        // Check if string starts with a specific prefix
        System.out.println("startsWith(\"Java\"): " + inputString.startsWith("Java"));

        // Check if string ends with a specific suffix
        System.out.println("endsWith(\"Fun\"): " + inputString.endsWith("Fun"));

        // Split the string by space
        String[] words = inputString.split(" ");
        System.out.println("split(\" \") (words count): " + words.length);

        // Character at a specific position
        if (inputString.length() > 2) {
            System.out.println("charAt(2): " + inputString.charAt(2));
        }

        // Length of the string
        System.out.println("length(): " + inputString.length());

        // Replace all vowels with '*'
        System.out.println("replaceAll(\"[AEIOUaeiou]\", \"*\"): " + inputString.replaceAll("[AEIOUaeiou]", "*"));

        // Convert string to char array
            char[] charArray = inputString.toCharArray();
            System.out.println("toCharArray() (First 5 chars): " + (charArray.length >= 5 ? new String(charArray, 0, 5) : new String(charArray)));

        // Reverse string using StringBuilder
        System.out.println("Reverse String: " + new StringBuilder(inputString).reverse().toString());
    }

    public static  void compareStrings(String word1,String word2) {
        System.out.println("\nString Comparisons:");
        System.out.println("Using equals(): " + word1.equals(word2));
        System.out.println("Using equalsIgnoreCase(): " + word1.equalsIgnoreCase(word2));
    }
    public static void vowelsCount(String word1)
    {
        int count=0;
        ArrayList<Character> list=new ArrayList<>();
        for(int i=0;i<word1.length();i++)
        {
            if(word1.charAt(i)=='a'||word1.charAt(i)=='e'||word1.charAt(i)=='i'||word1.charAt(i)=='o'||word1.charAt(i)=='u')
            {
                count++;
                list.add(word1.charAt(i));
            }
        }
        System.out.println("vowels count: "+count);
        System.out.println("vowels are: "+list);
    }
    public static void findUppercase(String word1)
    {
        ArrayList<Character> list=new ArrayList<>();
        for(int i=0;i<word1.length();i++)
        {
            if(Character.isUpperCase(word1.charAt(i)))
            {

                list.add(word1.charAt(i));
            }
        }

        System.out.println("Upper case letters are: "+list);
    }
    public static void findLowercase(String word1)
    {
        ArrayList<Character> list=new ArrayList<>();
        for(int i=0;i<word1.length();i++)
        {
            if(Character.isLowerCase(word1.charAt(i)))
            {

                list.add(word1.charAt(i));
            }
        }

        System.out.println("Lower case letters are: "+list);
    }

    public static void findFrequency(Character character,String word1)
    {
        int count=0;

        for(int i=0;i<word1.length();i++)
        {
            if(word1.charAt(i)==character)
            {
                count++;

            }
        }
        System.out.println("Frequency of "+character+" is: "+count);
    }

    public static  void findBlankspaces(String word1)
    {
        int count=0;

        for(int i=0;i<word1.length();i++)
        {
            if(word1.charAt(i)==' ')
            {
                count++;

            }
        }
        System.out.println("Blank spaces count: "+count);
    }
    public static void extractNumbers(String word1)
    {
        ArrayList<Character> list=new ArrayList<>();
        for(int i=0;i<word1.length();i++)
        {
            if(Character.isDigit(word1.charAt(i)))
            {

                list.add(word1.charAt(i));
            }
        }

        System.out.println("Numbers are: "+list);
    }
    public static void alternatePattern(String word1)
    {
        String result="";
        for(int i=0;i<word1.length();i++)
        {
            if(i%2!=0)
            {
               if( Character.isAlphabetic(word1.charAt(i))) {
                   if (Character.isUpperCase(word1.charAt(i)))
                       result += word1.charAt(i) - 65+1;
                   else result += word1.charAt(i) - 97+1;
               }
               else
                   result+=word1.charAt(i);
            }

                else

            result+=String.valueOf(word1.charAt(i));
        }
        System.out.println("Alternate pattern :"+result);
    }
}
