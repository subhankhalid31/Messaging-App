import java.util.Scanner;

public class MessagingApp {
    private String[][] contactList;
    private Text[][] conversationHistory;

    private int totalContacts;
    private int[] messageCounts;

    public MessagingApp() {
        contactList = new String[100][3];
        conversationHistory = new Text[100][100];
        messageCounts = new int[100];
        totalContacts = 0;
    }

    public void addNewContact(String name, String phoneNumber) {
        contactList[totalContacts][0] = Integer.toString(totalContacts + 1);
        contactList[totalContacts][1] = name;
        contactList[totalContacts][2] = phoneNumber;

        totalContacts++;
        System.out.println("Contact added successfully!");
    }

    public void displayContacts() {
        System.out.println("Contact List:");

        for (int i = 0; i < totalContacts; i++) {
            System.out.println("ID: " + contactList[i][0] + ", Name: " + contactList[i][1] + ", Number: " + contactList[i][2]);
        }
    }

    public void removeContact(String name) {
        int contactIndex = findContactByName(name);
        if (contactIndex != -1) {
            for (int i = contactIndex; i < totalContacts - 1; i++) {
                contactList[i] = contactList[i + 1];
                messageCounts[i] = messageCounts[i + 1];
                conversationHistory[i] = conversationHistory[i + 1];
            }
            totalContacts--;
            System.out.println("Contact removed successfully!");
        } else {
            System.out.println("No such contact saved.");
        }
    }

    public void initiateChat(String name, String senderName) {
        int contactIndex = findContactByName(name);
        if (contactIndex != -1) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your messages (Enter 0 to stop):");

            while (true) {
                String inputMessage = scanner.nextLine();
                if (inputMessage.equals("0")) break;

                Text newText = new Text(inputMessage, senderName, name);
                conversationHistory[contactIndex][messageCounts[contactIndex]] = newText;

                messageCounts[contactIndex]++;
                System.out.println("Message has been sent.");
            }
        } else {
            System.out.println("No such contact found.");
        }
    }

    public void displayChatHistory(String name) {
        int contactIndex = findContactByName(name);
        if (contactIndex != -1) {
            System.out.println("Chat history with " + contactList[contactIndex][1] + ":");

            for (int i = messageCounts[contactIndex] - 1; i >= 0; i--) {
                System.out.println(conversationHistory[contactIndex][i].getDetails());
                conversationHistory[contactIndex][i].markAsRead();
            }
        } else {
            System.out.println("No such contact found.");
        }
    }

    
    public void deleteLastMessage(String contactName) {
        int contactIndex = findContactByName(contactName);
        if (contactIndex != -1) {
            if (messageCounts[contactIndex] > 0) {
                conversationHistory[contactIndex][messageCounts[contactIndex] - 1] = null;  
                messageCounts[contactIndex]--;
                System.out.println("Last message deleted successfully!");
            } else {
                System.out.println("No messages to delete.");
            }
        } else {
            System.out.println("No such contact found.");
        }
    }

    private int findContactByName(String name) {
        for (int i = 0; i < totalContacts; i++) {
            if (contactList[i][1].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private int findContactByNumber(String number) {
        for (int i = 0; i < totalContacts; i++) {
            if (contactList[i][2].equals(number)) {
                return i;
            }
        }
        return -1;
    }
}
