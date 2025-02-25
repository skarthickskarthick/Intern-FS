package programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream_Implementation {
    public static void main(String args[])
    {
        List<Integer> numberList= Arrays.asList(11,41,61,15,13);
        //Stream Creation
        //filter()
        numberList.stream().filter(n->n%2==0).forEach(n->System.out.print(n+" "));
        System.out.println();

        //Map()

        numberList.stream().map(n->n*n).forEach(n->System.out.print(n+" "));
        System.out.println();

        //sorted()
        numberList.stream().sorted().forEach(n->System.out.print(n+" "));
        System.out.println();
        // Sort names in reverse order
        numberList.stream().sorted((a, b) -> b.compareTo(a)).forEach(n->System.out.print(n+" "));
        System.out.println();

        //parallel stream
        numberList.parallelStream().forEach(n -> System.out.print(Thread.currentThread().getName() + " - " + n+"    "));
        System.out.println();


        //Sequential Stream by default
        numberList.stream().forEach(n -> System.out.print(Thread.currentThread().getName() + " - " + n+"    "));
        System.out.println();


        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");// this is not stored just printed and removed from memory
        // Collecting results (stored in memory)
        List<String> upperCaseNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

    }
}
