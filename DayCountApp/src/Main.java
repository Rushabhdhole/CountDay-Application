import java.time.DayOfWeek; // Importing DayOfWeek enum for day calculations
import java.time.LocalDate; // Importing LocalDate for date operations
import java.time.format.DateTimeParseException; // Importing for handling date format exceptions
import java.time.temporal.ChronoUnit; // Importing ChronoUnit for date arithmetic
import javax.swing.JOptionPane; // Importing JOptionPane for user input/output

class DayCountApp { // Class declaration
    public static void main(String[] args) { // Main method
        try { // Handling exceptions
            // Get start date from user input
            String startDateInput = JOptionPane.showInputDialog(null, "Enter the start date (YYYY-MM-DD):");
            // Prompting user for start date
            LocalDate startDate = LocalDate.parse(startDateInput);
            // Parsing user input to LocalDate object

            // Get end date from user input
            String endDateInput = JOptionPane.showInputDialog(null, "Enter the end date (YYYY-MM-DD):");
            // Prompting user for end date
            LocalDate endDate = LocalDate.parse(endDateInput);
            // Parsing user input to LocalDate object

            if (endDate.isBefore(startDate)) { // Checking if end date is before start date
                JOptionPane.showMessageDialog(null, "Error: End date is before the start date."); // Displaying error message
            } else { // If end date is after start date
                // Calculate the difference between the dates
                long daysBetween = ChronoUnit.DAYS.between(startDate, endDate); // Calculating days between start and end date

                // Calculate weekdays count
                long weekdaysBetween = calculateWeekdays(startDate, endDate); // Calculating weekdays between start and end date

                // Calculate years count
                long yearsBetween = ChronoUnit.YEARS.between(startDate, endDate); // Calculating years between start and end date

                // Calculate remaining days after subtracting complete years
                LocalDate tempDate = startDate.plusYears(yearsBetween); // Adding years to start date
                long remainingDays = ChronoUnit.DAYS.between(tempDate, endDate); // Calculating remaining days

                // Check if start or end date falls within a leap year
                boolean startLeapYear = startDate.isLeapYear(); // Checking if start date is in a leap year
                boolean endLeapYear = endDate.isLeapYear(); // Checking if end date is in a leap year

                // Prepare the message to display
                StringBuilder message = new StringBuilder(); // Creating StringBuilder to build message
                message.append("Number of days between ").append(startDate).append(" and ").append(endDate).append(": ").append(daysBetween).append(" days.\n"); // Adding days between dates to message
                message.append("Number of weekdays between ").append(startDate).append(" and ").append(endDate).append(": ").append(weekdaysBetween).append(" weekdays.\n"); // Adding weekdays between dates to message
                message.append("Number of years between ").append(startDate).append(" and ").append(endDate).append(": ").append(yearsBetween).append(" years and ").append(remainingDays).append(" days.\n"); // Adding years and remaining days between dates to message
                message.append("Start date is in a leap year: ").append(startLeapYear).append("\n"); // Adding start date leap year info to message
                message.append("End date is in a leap year: ").append(endLeapYear); // Adding end date leap year info to message

                // Display the result in a pop-up window
                JOptionPane.showMessageDialog(null, message.toString()); // Displaying message in a dialog box
            }
        } catch (DateTimeParseException | NullPointerException e) { // Catching exceptions related to date parsing or null input
            JOptionPane.showMessageDialog(null, "Invalid date format or input. Please use the format YYYY-MM-DD."); // Displaying error message for invalid input
        }
    }

    // Method to calculate weekdays count between two dates
    private static long calculateWeekdays(LocalDate startDate, LocalDate endDate) { // Method definition
        long weekdays = 0; // Variable to store count of weekdays
        LocalDate tempDate = startDate; // Initializing temporary date with start date
        while (!tempDate.isAfter(endDate)) { // Loop until temporary date is after end date
            if (tempDate.getDayOfWeek() != DayOfWeek.SATURDAY && tempDate.getDayOfWeek() != DayOfWeek.SUNDAY) { // Checking if temporary date is not Saturday or Sunday
                weekdays++; // Incrementing weekdays count
            }
            tempDate = tempDate.plusDays(1); // Moving to the next day
        }
        return weekdays; // Returning count of weekdays
    }
}
