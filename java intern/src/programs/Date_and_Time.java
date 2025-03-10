package programs;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Date_and_Time {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        // Get current local date and time
        LocalDateTime now = LocalDateTime.now();
        LocalDate currentDate = now.toLocalDate();

        // Calculate before and after dates
        LocalDate yesterday = currentDate.minusDays(1);
        LocalDate tomorrow = currentDate.plusDays(1);

        // Print current, previous, and next day
        System.out.println("Current Date: " + currentDate);
        System.out.println("Yesterday's Date: " + yesterday);
        System.out.println("Tomorrow's Date: " + tomorrow);

        // Date Formats
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter format3 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter format4 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-ns");
        DateTimeFormatter format5 = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

        System.out.println("\nFormatted Dates:");
        System.out.println("Format (yyyy-MM-dd)       : " + currentDate.format(format1));
        System.out.println("Format (dd/MM/yyyy)       : " + currentDate.format(format2));
        System.out.println("Format (MM/dd/yyyy)       : " + currentDate.format(format3));
        System.out.println("Format (yyyy-MM-dd-HH-mm-ss-ns): " + now.format(format4));
        System.out.println("Format (Month dd, yyyy)   : " + currentDate.format(format5));

        // Convert Long Value to Date Format
        long timestamp = 0;

        while (true) {
            System.out.println("Enter a long value:");
            String input = scanner.nextLine().trim();
            try {
                timestamp = Long.parseLong(input);
                break;  // Exit the loop if the value is a valid long
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input! Enter a valid long value.");
            }
        }

        // Example long timestamp (milliseconds)
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZonedDateTime utcTime = instant.atZone(ZoneId.of("UTC"));
        ZonedDateTime localTime = instant.atZone(ZoneId.systemDefault());

        System.out.println("\nConvert Long Value to Date:");
        System.out.println("Local Date: " + localTime.format(format1));
        System.out.println("UTC Date  : " + utcTime.format(format1));
        System.out.println("UTC Full Format: " + utcTime);

    }
}

