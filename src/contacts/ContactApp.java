package contacts;

import util.Input;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class ContactApp {
    public static void main(String[] args) {
        ArrayList<Contact> contacts = parseContacts("data", "contacts.txt");
        Input input = new Input();
        int userInput = 1;
        String newContactName;
        String newContactNumber;
        String newContactEmail;
        String deleteContactName;
        String userSearch;

        System.out.printf("\n%-28s |*| %-27s |*| %-28s\n", "+--------***********-------+", "Welcome to Contacts Manager", "+--------***********--------+");

        while(true) {
            System.out.printf("%-28s |*| %-27s |*| %-28s\n", "1 - See Contacts", "     2 - Sort Contacts", "3 - Add Contact");
            System.out.printf("%-28s |*| %-27s |*| %-28s\n", "4 - Delete Contact", "    5 - Search Contacts", "6 - Exit");
            userInput = input.getInt("Selection: ");
            if(userInput == 1) {
                printContacts(contacts);
            } else if (userInput == 2) {
                ArrayList<Contact> temporaryContactList = (ArrayList<Contact>) contacts.clone();
                printContacts(sortContacts(temporaryContactList));
            } else if (userInput == 3) {
                newContactName = input.getString("New Contact Name: ");
                if (checkDuplicateNames(contacts, newContactName)) {
                    String overWriteName = input.getString("This name already exists. Would you like to overwrite it? (y/n): ");
                    if (overWriteName.toLowerCase().equals("n")) {
                        continue;
                    }
                }
                deleteContact(contacts, newContactName);
                newContactNumber = input.getString("New Contact Phone Number: ");
                newContactEmail = input.getString("New Contact Email: ");
                Contact newContact = new Contact(newContactName, newContactNumber, newContactEmail);
                contacts.add(newContact);
            } else if (userInput == 4) {
                deleteContactName = input.getString("Which contact would you like to Delete?: ");
                deleteContact(contacts, deleteContactName);
            } else if (userInput == 5) {
                userSearch = input.getString("Enter a name: ");
                if(!searchContacts(contacts, userSearch)) {
                    System.out.println("Sorry we couldn't find that contact.\n");
                }
            } else if (userInput == 6) {
                System.out.println("Goodbye.");
                updateContactsFile(contacts);
                break;
            }
        }

    }

    public static boolean searchContacts(ArrayList<Contact> inputContacts, String searchName) {
        boolean contactFound = false;
        for (Contact contact : inputContacts) {
            if(searchName.equals(contact.getName())) {
                contact.printContact();
                System.out.println("");
                contactFound = true;
            }
        }
        return contactFound;
    }

    public static ArrayList<Contact> sortContacts(ArrayList<Contact> inputContacts) {
        inputContacts.sort(Comparator.comparing(Contact::getName));
        return inputContacts;
    }

    public static ArrayList<Contact> parseContacts(String inputDirectory, String inputFilename) {
        ArrayList<Contact> returnContacts = new ArrayList<>();
        try {
            Path contactFile = Paths.get(inputDirectory, inputFilename);
            List<String> contactLines = Files.readAllLines(contactFile);
            for(String line : contactLines) {
                String[] contactInfo = line.split(",");
                Contact contactToBeAdded = new Contact(contactInfo[0], contactInfo[1], contactInfo[2]);
                returnContacts.add(contactToBeAdded);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return returnContacts;
    }

    public static void updateContactsFile(ArrayList<Contact> inputContacts) {
        List<String> contactsOverwrite = new ArrayList<>();
        for(Contact contact : inputContacts) {
            String contactString = contact.getName() + "," + contact.getNumber() + "," + contact.getEmail();
            contactsOverwrite.add(contactString);
        }
        try {
            Path contactsPath = Paths.get("data", "contacts.txt");
            Files.write(contactsPath, contactsOverwrite);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }..

    public static void printContacts(ArrayList<Contact> inputContacts){
        System.out.println("");
        System.out.printf("%-28s |*| %-27s |*| %-20s\n", "            Name", "           Number", "            Email");
        System.out.printf("%-28s |*| %-27s |*| %-20s\n", "+--------***********-------+", "+-------************------+", "+--------***********--------+");
        for(Contact contact : inputContacts) {
            contact.printContact();
        }
        System.out.printf("%-28s |*| %-27s |*| %-20s\n", "+--------***********-------+", "+-------************------+", "+--------***********--------+");
    }

    public static boolean checkDuplicateNames(ArrayList<Contact> inputContacts, String inputName) {
        for(Contact contact : inputContacts) {
            if(inputName.equals(contact.getName())) {
                return true;
            }
        }
        return false;
    }

    public static void deleteContact(ArrayList<Contact> inputContacts, String toDelete) {
        inputContacts.removeIf(contact -> contact.getName().equals(toDelete));
    }

}
