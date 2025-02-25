package programs;

public class Wrapperclass {
    public static void main(String args[])
    {
        //Autoboxing
        byte byteValue = 10;
        Byte byteObj = byteValue;  // Autoboxing
        byte byteUnboxed = byteObj; // Unboxing
        System.out.println("Byte Value: " + byteUnboxed);

        // 2️⃣ Short Wrapper Class
        short shortValue = 200;
        Short shortObj = shortValue;  // Autoboxing
        short shortUnboxed = shortObj; // Unboxing
        System.out.println("Short Value: " + shortUnboxed);

        // 3️⃣ Integer Wrapper Class
        int intValue = 5000;
        Integer intObj = intValue;  // Autoboxing
        int intUnboxed = intObj; // Unboxing
        System.out.println("Integer Value: " + intUnboxed);

        // 4️⃣ Long Wrapper Class
        long longValue = 100000L;
        Long longObj = longValue;  // Autoboxing
        long longUnboxed = longObj; // Unboxing
        System.out.println("Long Value: " + longUnboxed);

        // 5️⃣ Float Wrapper Class
        float floatValue = 3.14f;
        Float floatObj = floatValue;  // Autoboxing
        float floatUnboxed = floatObj; // Unboxing
        System.out.println("Float Value: " + floatUnboxed);

        // 6️⃣ Double Wrapper Class
        double doubleValue = 99.99;
        Double doubleObj = doubleValue;  // Autoboxing
        double doubleUnboxed = doubleObj; // Unboxing
        System.out.println("Double Value: " + doubleUnboxed);

        // 7️⃣ Character Wrapper Class
        char charValue = 'A';
        Character charObj = charValue;  // Autoboxing
        char charUnboxed = charObj; // Unboxing
        System.out.println("Character Value: " + charUnboxed);

        // 8️⃣ Boolean Wrapper Class
        boolean booleanValue = true;
        Boolean booleanObj = booleanValue;  // Autoboxing
        boolean booleanUnboxed = booleanObj; // Unboxing
        System.out.println("Boolean Value: " + booleanUnboxed);

        // Extra: Wrapper class methods
        System.out.println("\nWrapper Class Methods:");
        System.out.println("Integer Max Value: " + Integer.MAX_VALUE);
        System.out.println("Integer Min Value: " + Integer.MIN_VALUE);
        System.out.println("Parsing String to Integer: " + Integer.parseInt("123"));
        System.out.println("Boolean from String: " + Boolean.parseBoolean("true"));
        System.out.println("Double to String: " + Double.toString(456.78));


    }
}
