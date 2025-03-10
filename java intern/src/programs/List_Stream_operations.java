package programs;

import java.util.ArrayList;
import java.util.Arrays;

public class List_Stream_operations {
    public static void main(String args[])
    {
        ArrayList<Integer> integersList=new ArrayList<>(Arrays.asList(101,-22,-3,40,-500,6,6));
        ArrayList<Long> longList=new ArrayList<>(Arrays.asList(1000L,600L,214L,0L,0L,540L,358l,600L,484l));
        ArrayList<Float> floatList=new ArrayList<>(Arrays.asList(11.0f,22.2f,31.2f,0f,11.0f,31.2f,40.2f,500.3f,6.2f));
        ArrayList<Double> doubleList=new ArrayList<>(Arrays.asList(11.0,22.2,31.2,40.2,500.3,0.0,6.2));
        ArrayList<String> stringList=new ArrayList<>(Arrays.asList("alagu","siva","bala","siva","abc"));
        ArrayList<Character> characterList=new ArrayList<>(Arrays.asList('a','c','z','f','d','z','z'));



        System.out.println("Printing List");
        //Print using for each
        integersList.stream().forEach(n->System.out.print(n+" "));
        System.out.println();
        longList.stream().forEach(n->System.out.print(n+" "));
        System.out.println();
        floatList.stream().forEach(n->System.out.print(n+" "));
        System.out.println();
        doubleList.stream().forEach(n->System.out.print(n+" "));
        System.out.println();
        characterList.stream().forEach(n->System.out.print(n+" "));
        System.out.println();
        stringList.stream().forEach(n->System.out.print(n+" "));



        System.out.println();
        System.out.println();
        System.out.println("Sorted List");
        //sorting
        integersList.stream().sorted().forEach(n->System.out.print(n+" "));
        System.out.println();
        longList.stream().sorted().forEach(n->System.out.print(n+" "));
        System.out.println();
        floatList.stream().sorted().forEach(n->System.out.print(n+" "));
        System.out.println();
        doubleList.stream().sorted().forEach(n->System.out.print(n+" "));
        System.out.println();
        characterList.stream().sorted().forEach(n->System.out.print(n+" "));
        System.out.println();
        stringList.stream().sorted().forEach(n->System.out.print(n+" "));



        System.out.println();
        System.out.println();
        System.out.println("Removing duplicates");
        //Remove duplicates
        integersList.stream().distinct().forEach(n->System.out.print(n+" "));
        System.out.println();
        longList.stream().distinct().forEach(n->System.out.print(n+" "));
        System.out.println();
        floatList.stream().distinct().forEach(n->System.out.print(n+" "));
        System.out.println();
        doubleList.stream().distinct().forEach(n->System.out.print(n+" "));
        System.out.println();
        characterList.stream().distinct().forEach(n->System.out.print(n+" "));
        System.out.println();
        stringList.stream().distinct().forEach(n->System.out.print(n+" "));






        //filter
        System.out.println();
        System.out.println();
        System.out.println("filter List for only even numbers");
        integersList.stream().filter(n->n>0).forEach(n->System.out.print(n+" "));
        System.out.println();
        longList.stream().filter(n->n!=0).forEach(n->System.out.print(n+" "));
        System.out.println();
        floatList.stream().filter(n->n%2==0).forEach(n->System.out.print(n+" "));
        System.out.println();
        doubleList.stream().filter(n->n!=0).forEach(n->System.out.print(n+" "));
        System.out.println();
        characterList.stream().filter(n->n=='z').forEach(n->System.out.print(n+" "));
        System.out.println();
        stringList.stream().filter(n->n.startsWith("a")).forEach(n->System.out.print(n+" "));





        //map
        System.out.println();
        System.out.println();
        System.out.println("Mapping List for squaring numbers");
        integersList.stream().map(n->n*n).forEach(n->System.out.print(n+" "));
        System.out.println();
        longList.stream().map(n->n*n*n).forEach(n->System.out.print(n+" "));
        System.out.println();
        floatList.stream().map(n->n+n).forEach(n->System.out.print(n+" "));
        System.out.println();
        doubleList.stream().map(n->n-n).forEach(n->System.out.print(n+" "));
        System.out.println();
        characterList.stream().map(n->n+"," +n).forEach(n->System.out.print(n+"    "));
        System.out.println();
        stringList.stream().map(n->n+"," +n).forEach(n->System.out.print(n+"    "));


        System.out.println();
        System.out.println();
        System.out.println("Limiting List for only 3 elements");
        //limit
        integersList.stream().limit(3).forEach(n->System.out.print(n+" "));
        System.out.println();
        longList.stream().limit(4).forEach(n->System.out.print(n+" "));
        System.out.println();
        floatList.stream().limit(3).forEach(n->System.out.print(n+" "));
        System.out.println();
        doubleList.stream().limit(1).forEach(n->System.out.print(n+" "));
        System.out.println();
        characterList.stream().limit(3).forEach(n->System.out.print(n+" "));
        System.out.println();
        stringList.stream().limit(2).forEach(n->System.out.print(n+" "));



        System.out.println();
        System.out.println();
        System.out.println("skip 2 elements in the list");
        //skip
        integersList.stream().skip(3).forEach(n->System.out.print(n+" "));
        System.out.println();
        longList.stream().skip(2).forEach(n->System.out.print(n+" "));
        System.out.println();
        floatList.stream().skip(1).forEach(n->System.out.print(n+" "));
        System.out.println();
        doubleList.stream().skip(3).forEach(n->System.out.print(n+" "));
        System.out.println();
        characterList.stream().skip(31).forEach(n->System.out.print(n+" "));
        System.out.println();
        stringList.stream().skip(1).forEach(n->System.out.print(n+" "));
    }
}
