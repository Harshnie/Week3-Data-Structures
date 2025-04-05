import java.util.Scanner;

class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    int seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket head = null;

    // Add a ticket at the end of the circular linked list
    public void addTicket(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            newTicket.next = head; // Point to itself (circular)
        } else {
            Ticket temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTicket;
            newTicket.next = head; // Point the last node back to the head
        }
    }

    // Remove a ticket by Ticket ID
    public void removeTicket(int ticketId) {
        if (head == null) {
            System.out.println("No tickets to remove.");
            return;
        }

        Ticket temp = head;
        Ticket prev = null;

        // If the ticket to be removed is the head
        if (head.ticketId == ticketId) {
            if (head.next == head) {
                head = null; // Only one ticket left in the list
            } else {
                // Find the last ticket and update its next pointer to head's next
                while (temp.next != head) {
                    temp = temp.next;
                }
                head = head.next;
                temp.next = head; // Update last node's next to new head
            }
            System.out.println("Ticket with ID " + ticketId + " removed.");
            return;
        }

        // Search for the ticket in the circular list
        while (temp.next != head) {
            prev = temp;
            temp = temp.next;
            if (temp.ticketId == ticketId) {
                prev.next = temp.next;
                System.out.println("Ticket with ID " + ticketId + " removed.");
                return;
            }
        }

        System.out.println("Ticket with ID " + ticketId + " not found.");
    }

    // Display all tickets in the circular list
    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket temp = head;
        do {
            System.out.println("Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head); // Loop back to head for circular nature
    }

    // Search for a ticket by Customer Name or Movie Name
    public void searchTicket(String query) {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }

        Ticket temp = head;
        boolean found = false;

        do {
            if (temp.customerName.equalsIgnoreCase(query) || temp.movieName.equalsIgnoreCase(query)) {
                System.out.println("Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No ticket found with the provided query.");
        }
    }

    // Calculate the total number of booked tickets
    public void countTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        int count = 0;
        Ticket temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Total number of booked tickets: " + count);
    }
}

public class TicketReservationSystemApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketReservationSystem system = new TicketReservationSystem();
        int choice;

        do {
            System.out.println("\n--- Online Ticket Reservation System ---");
            System.out.println("1. Add a New Ticket");
            System.out.println("2. Remove a Ticket by ID");
            System.out.println("3. Display All Tickets");
            System.out.println("4. Search for a Ticket by Customer Name or Movie Name");
            System.out.println("5. Count Total Booked Tickets");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            int ticketId, seatNumber;
            String customerName, movieName, bookingTime;

            switch (choice) {
                case 1:
                    System.out.print("Enter Ticket ID: ");
                    ticketId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    customerName = scanner.nextLine();
                    System.out.print("Enter Movie Name: ");
                    movieName = scanner.nextLine();
                    System.out.print("Enter Seat Number: ");
                    seatNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Booking Time: ");
                    bookingTime = scanner.nextLine();
                    system.addTicket(ticketId, customerName, movieName, seatNumber, bookingTime);
                    break;
                case 2:
                    System.out.print("Enter Ticket ID to Remove: ");
                    ticketId = scanner.nextInt();
                    system.removeTicket(ticketId);
                    break;
                case 3:
                    system.displayTickets();
                    break;
                case 4:
                    System.out.print("Enter Customer Name or Movie Name to Search: ");
                    String query = scanner.nextLine();
                    system.searchTicket(query);
                    break;
                case 5:
                    system.countTickets();
                    break;
                case 0:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
