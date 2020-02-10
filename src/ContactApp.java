import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;


public class ContactApp {
    public static void main(String[] args) {
//        System.out.printf("%20%s | %s20 | %s20\n", "Name", "Number", "E-Mail");
//        Contact testContact = new Contact("Daniel", "999-000-0000", "danf@gmail.com");
//        testContact.printContact();
//        Contact testContactTwo = new Contact("Will", "999-000-4400", "will@gmail.com");
//        testContactTwo.printContact();

        ArrayList<Contact> contacts = parseContacts("data", "contacts.txt");
        System.out.println("");
        for(Contact contact : contacts) {
            contact.printContact();
        }


        Contact newContact = new Contact("Dave Chappelle", "333-333-3333", "dcp@gmail.com");
        contacts.add(newContact);
        updateContactsFile(contacts);

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
}
