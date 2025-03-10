package programs;

class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks");
    }
}

public class Typecasting {
    public static void main(String[] args) {
        Animal a = new Dog();  // Upcasting (automatic)
        a.makeSound(); // Allowed
        // a.bark(); // Not allowed (because reference is of type Animal)


        ((Dog) a).bark();//downcasting



        int num = 100;
        double doubleNum = num;  // Automatic conversion from int to double
       // Implicit conversion
        System.out.println("Integer value: " + num);
        System.out.println("Converted Double value: " + doubleNum);


        //Explicit conversion
        double number = 100.99;
        int intNum = (int) number;  // Explicitly casting double to int

        System.out.println("Double value: " + num);
        System.out.println("Converted Integer value: " + intNum);
    }
}

