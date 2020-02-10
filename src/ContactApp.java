import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class ContactApp {
    public static void main(String[] args) {

        ArrayList<Contact> contacts = parseContacts("data", "contacts.txt");

        Scanner userInput = new Scanner(System.in);
        System.out.printf("%-20s |*| %-27s |*| %20s\n", "     **********     ", "Welcome to Contacts Manager", "     **********     ");
        System.out.printf("%-20s |*| %-27s |*| %20s\n", "        Name        ", "          Number           ", "       Email        ");

        printContacts(contacts);
//        updateContactsFile(contacts);

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
    }

    public static void printContacts(ArrayList<Contact> inputContacts){
        System.out.println("");
        for(Contact contact : inputContacts) {
            contact.printContact();
        }
    }


}
