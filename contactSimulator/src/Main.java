import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Contact> contacts;
    private static Scanner scanner;
    private static int id = 0;

    public static void main(String[] args) {
        contacts = new ArrayList<>();
        showInitialOptions();
    }

    private static void showInitialOptions() {
        System.out.println("select: " + "\n\t1. Manage Contacts" + "\n\t2. Messages" + "\n\t3. Quit");
        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                manageContacts();
                break;
            case 2:
                manageMesssages();
                break;
            default:
                break;
        }
    }

    private static void manageMesssages() {
        System.out.println("select: " + "\n\t1. Show all Messages" + "\n\t2. Send new Messages" + "\n\t3. Go Back");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showAllMessages();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void sendNewMessage() {
        System.out.println("send to who?");
        String name = scanner.next();
        if (name.equals("")) {
            sendNewMessage();
        } else {
            boolean exist = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    exist = true;
                }
            }
            if (exist) {
                System.out.println("enter text");
                String text = scanner.next();
                id++;
                Message newMsg = new Message(text, name, id);
                for (Contact c : contacts) {
                    if (c.getName().equals(name)) {
                        ArrayList<Message> newMessages = c.getMessages();
                        newMessages.add(newMsg);
                        c.setMessages(newMessages);
                    }
                }

            } else {
                System.out.println("no such contact");
            }
        }
        showInitialOptions();
    }

    private static void showAllMessages() {
        ArrayList<Message> allMessages = new ArrayList<>();
        for (Contact c : contacts) {
            allMessages.addAll(c.getMessages());
        }
        if (allMessages.size() > 0) {
            for (Message m : allMessages) {
                m.getDetails();
                System.out.println("---------");
            }
        } else {
            System.out.println("no messages");
        }
        showInitialOptions();
    }

    private static void manageContacts() {
        System.out.println("select: " + "\n\t1. Show all Contacts" + "\n\t2. Add new contact" + "\n\t3. Search Contacts"
                + "\n\t4. Delete a Contact" + "\n\t5. Back");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showAllContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                searchForContact();
                break;
            case 4:
                deleteContact();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void deleteContact() {
        System.out.println("enter contact name");
        String name = scanner.next();
        if (name.equals("")) {
            deleteContact();
        } else {
            boolean exist = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    contacts.remove(c);
                    exist = true;
                }
            }
            if (!exist) {
                System.out.println("No Contact Found");
            }
        }
        showInitialOptions();
    }

    private static void searchForContact() {
        System.out.println("enter contact name");
        String name = scanner.next();
        if (name.equals("")) {
            searchForContact();
        } else {
            boolean exist = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    c.getDetails();
                    exist = true;
                }
            }
            if (!exist) {
                System.out.println("No Contact Found");
            }
        }
        showInitialOptions();
    }

    private static void showAllContacts() {
        for (Contact c : contacts) {
            c.getDetails();
            System.out.println("----------");
        }
        showInitialOptions();
    }

    private static void addNewContact() {
        System.out.println("Enter Name");
        String name = scanner.next();
        System.out.println("Enter Number");
        String number = scanner.next();
        System.out.println("Enter Email");
        String email = scanner.next();
        if (name.equals("") || number.equals("") || email.equals("")) {
            System.out.println("enter all info");
            addNewContact();
        } else {
            boolean exist = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    exist = true;
                }
            }
            if (exist) {
                System.out.println("Contact Already Exists");
                addNewContact();
            } else {
                Contact contact = new Contact(name, number, email);
                contacts.add(contact);
                System.out.println("added new contact");
            }
        }
        showInitialOptions();
    }

}
