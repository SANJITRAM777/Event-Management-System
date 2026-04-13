import java.util.Scanner;
public class mainapp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        events eventService = new events();

        while (true) {
            System.out.println("\n=== Event Management System ===");
            System.out.println("1. Add Event");
            System.out.println("2. View Events");
            System.out.println("3. Update Event");
            System.out.println("4. Delete Event");
            System.out.println("5. Register Participant");
            System.out.println("6. View Participants by Event");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter event name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter event date: ");
                    String date = sc.nextLine();
                    System.out.print("Enter event location: ");
                    String location = sc.nextLine();
                    eventService.addEvent(name, date, location);
                    break;
                case 2:
                    eventService.viewEvents();
                    break;
                case 3:
                    System.out.print("Enter event ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter new event name: ");
                    String newName = sc.nextLine();
                    eventService.updateEvent(updateId, newName);
                    break;
                case 4:
                    System.out.print("Enter event ID to delete: ");
                    int deleteId = sc.nextInt();
                    eventService.deleteEvent(deleteId);
                    break;
                case 5:
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String pname = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Event ID: ");
                    int eid = sc.nextInt();
                    eventService.registerParticipant(pname, email, eid);
                    break;
                case 6:
                    System.out.print("Enter Event ID: ");
                    int eventId = sc.nextInt();
                    eventService.viewParticipantsByEvent(eventId);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
