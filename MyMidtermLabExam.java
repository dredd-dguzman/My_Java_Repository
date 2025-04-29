import java.util.Scanner;

public class MyMidtermLabExam {

    // Constants and arrays to store ticket data
    static final int MAX_TICKETS = 5;
    static String[] descriptions = new String[MAX_TICKETS];
    static String[] urgencies = new String[MAX_TICKETS];
    static String[] statuses = new String[MAX_TICKETS];
    static int ticketCount = 0; // Tracks the number of tickets added

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Loop to display the menu repeatedly until the user chooses to exit
        do {
            System.out.println("\n=== IT Ticket Processing System ===");
            System.out.println("1. Add Ticket");
            System.out.println("2. Update Ticket Status");
            System.out.println("3. Show All Tickets");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            // Handle non-integer inputs for menu selection
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number between 1 and 5: ");
                scanner.next(); // discard invalid input
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // clear newline character

            // Use switch-case to handle menu selections
            switch (choice) {
                case 1:
                    addTicket(scanner);
                    break;
                case 2:
                    updateTicketStatus(scanner);
                    break;
                case 3:
                    showTickets();
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option (1-5).");
            }

        } while (choice != 5); // loop until Exit is selected

        scanner.close(); // Close scanner to prevent memory leak
    }

    // Method to add a new ticket
    public static void addTicket(Scanner scanner) {
        // Check if ticket limit has been reached
        if (ticketCount >= MAX_TICKETS) {
            System.out.println("Maximum number of tickets reached.");
            return;
        }

        // Get issue description
        System.out.print("Enter issue description: ");
        String description = scanner.nextLine();

        // Get urgency level (with validation)
        String urgency;
        do {
            System.out.print("Enter urgency level (Low / Medium / High): ");
            urgency = scanner.nextLine();
        } while (!urgency.equalsIgnoreCase("Low") &&
                 !urgency.equalsIgnoreCase("Medium") &&
                 !urgency.equalsIgnoreCase("High"));

        // Store the ticket data with default status "Pending"
        descriptions[ticketCount] = description;
        urgencies[ticketCount] = urgency;
        statuses[ticketCount] = "Pending";
        ticketCount++; // Increment the count

        System.out.println("Ticket added successfully!");
    }

    // Method to update the status of a ticket
    public static void updateTicketStatus(Scanner scanner) {
        // Check if there are tickets to update
        if (ticketCount == 0) {
            System.out.println("No tickets to update.");
            return;
        }

        showTickets(); // Show existing tickets

        System.out.print("Enter ticket number to update (1 to " + ticketCount + "): ");
        int ticketNum = scanner.nextInt();
        scanner.nextLine(); // clear newline

        // Validate ticket number
        if (ticketNum < 1 || ticketNum > ticketCount) {
            System.out.println("Invalid ticket number.");
            return;
        }

        int index = ticketNum - 1;

        // Prevent updates to already resolved tickets
        if (statuses[index].equalsIgnoreCase("Resolved")) {
            System.out.println("This ticket is already resolved and cannot be updated.");
            return;
        }

        // Ask for the new status
        System.out.print("Enter new status (In Progress / Resolved): ");
        String newStatus = scanner.nextLine();

        // Validate new status input
        if (!newStatus.equalsIgnoreCase("In Progress") && !newStatus.equalsIgnoreCase("Resolved")) {
            System.out.println("Invalid status input.");
            return;
        }

        // Update the ticket status
        statuses[index] = newStatus;
        System.out.println("Ticket status updated successfully.");
    }

    // Method to display all tickets
    public static void showTickets() {
        if (ticketCount == 0) {
            System.out.println("No tickets to display.");
            return;
        }

        System.out.println("\n--- Ticket List ---");
        for (int i = 0; i < ticketCount; i++) {
            System.out.println((i + 1) + ". [" + urgencies[i] + "] " + descriptions[i] + " - Status: " + statuses[i]);
        }
    }

    // Method to generate a summary report
    public static void generateReport() {
        int pendingCount = 0;
        int resolvedCount = 0;

        // Count pending/in progress and resolved tickets
        for (int i = 0; i < ticketCount; i++) {
            if (statuses[i].equalsIgnoreCase("Resolved")) {
                resolvedCount++;
            } else {
                pendingCount++;
            }
        }

        // Display report
        System.out.println("\n--- Ticket Report ---");
        System.out.println("Total Tickets: " + ticketCount);
        System.out.println("Pending/In Progress: " + pendingCount);
        System.out.println("Resolved: " + resolvedCount);
    }
}
