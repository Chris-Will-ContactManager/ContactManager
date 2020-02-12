package contacts;

public class Contact {
    private String name;
    private String number;
    private String email;

    public Contact (String inputName, String inputNumber, String inputEmail) {
        this.name = inputName;
        this.number = inputNumber;
        this.email = inputEmail;
    }

    public void printContact() {
        System.out.printf("%-28s |*| %-27s |*| %-28s\n", this.name, "        "+ this.number + "      ", this.email);
    }

    public void setName(String inputName) {
        this.name = inputName;
    }

    public void setNumber(String inputNumber) {
        this.number = inputNumber;
    }

    public void setEmail(String inputEmail) {
        this.email = inputEmail;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNumber() {
        return this.number;
    }

}
