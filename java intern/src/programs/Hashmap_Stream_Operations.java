package programs;

import java.util.*;
import java.util.stream.Collectors;

public class Hashmap_Stream_Operations {
    public static void main(String args[])
    {
        HashMap<Integer,String> employeemap=new HashMap<>();
        employeemap.put(1,"abc");
        employeemap.put(5,",lmllm");
        employeemap.put(3,"sdf");
        employeemap.put(6,"mncjbsha");
        employeemap.put(2,"sdf");
        employeemap.put(4,"safdflk");


        //print
        employeemap.entrySet().stream().forEach(entry->System.out.println(entry.getKey()+" ->"+entry.getValue()));

        //sort
        System.out.println("\nSorted by Key:");
        employeemap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey()) // Sorting by key
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

        //remove duplicates
        // Removing duplicate values and keeping only the first occurrence
        System.out.println("\nHashMap after Removing Duplicate Values:");
        Set<String> seenValues = new HashSet<>();
        Map<Integer, String> uniqueValueMap = employeemap.entrySet()
                .stream()
                .filter(entry -> seenValues.add(entry.getValue())) // Only add unseen values
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));

        uniqueValueMap.forEach((key, value) -> System.out.println(key + " -> " + value));

        //filter
        // 4. Filter: Keep only those entries where key is greater than 2
        System.out.println("\nFiltered (Keys > 2):");
        employeemap.entrySet()
                .stream()
                .filter(entry -> entry.getKey() > 2) // Filtering condition
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));


        //map

        // 5. Mapping: Convert values to uppercase
        System.out.println("\nMapping (Values to Uppercase):");
        employeemap.entrySet()
                .stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().toUpperCase())) // Mapping values
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));


        //limit
        System.out.println("\nLimiting (First 3 entries):");
        employeemap.entrySet()
                .stream()
                .limit(3) // Limiting to first 3 elements
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));


        //skip
// 7. Skip: Skip the first 2 entries and display the rest
        System.out.println("\nSkipping First 2 Entries:");
        employeemap.entrySet()
                .stream()
                .skip(2) // Skipping first 2 elements
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));


    }
}
