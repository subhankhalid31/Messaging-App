import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MessagingApp app = new MessagingApp();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("--- Messaging System ---");

        while (choice != 7) {
            System.out.println("\n1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Delete Contact");
            System.out.println("4. Start Chat");
            System.out.println("5. View Chat History");
            System.out.println("6. Delete Last Message");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter contact name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter contact number: ");
                    String number = scanner.nextLine();
                    app.addNewContact(name, number);
                    break;

                case 2:
                    app.displayContacts();
                    break;

                case 3:
                    System.out.print("Enter contact name to delete: ");
                    String deleteName = scanner.nextLine();
                    app.removeContact(deleteName);
                    break;

                case 4:
                    System.out.print("Enter contact name to chat with: ");
                    String chatName = scanner.nextLine();
                    System.out.print("Enter your name: ");
                    String senderName = scanner.nextLine();
                    app.initiateChat(chatName, senderName);
                    break;

                case 5:
                    System.out.print("Enter contact name to view chat history: ");
                    String historyName = scanner.nextLine();
                    app.displayChatHistory(historyName);
                    break;

                case 6:
                    System.out.print("Enter contact name to delete the last message: ");
                    String messageContactName = scanner.nextLine();
                    app.deleteLastMessage(messageContactName);
                    break;

                case 7:
                    System.out.println("Exiting the messaging system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
