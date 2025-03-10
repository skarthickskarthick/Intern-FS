package programs;

import java.util.Scanner;

// Interface Example

// ✅ Interface defining behavior
interface Drawable {

    void draw();
   // Abstract method (by default)
}

// ✅ Shape class (providing base logic)
abstract class Shape {
     Shape()
    {
          System.out.println("abstract constructor call");
    }
    abstract double calculateArea();
    // Abstract method
}

// ✅ Circle class implementing Drawable & extending Shape
class Circle extends Shape implements Drawable  {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }

}

// ✅ Rectangle class implementing Drawable & extending Shape
class Rectangle extends Shape implements Drawable {
    private double length, width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double calculateArea() {
        return length * width;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }

}

// ✅ Main class to test interfaces
public class oops_demo {
    public static void main(String[] args) {
        Drawable d1 = new Circle(7);
        d1.draw();
        System.out.println("Circle Area: " + ((Circle) d1).calculateArea()); // Type casting needed

        Shape d2 = new Rectangle(5, 3);

        System.out.println("Rectangle Area: " + d2.calculateArea());
    }
}
