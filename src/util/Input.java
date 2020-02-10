package util;

import java.util.Scanner;

public class Input {
    private Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String getString() {
        System.out.print("Enter a string: ");
        return this.scanner.nextLine();
    }

    public String getString(String prompt) {
        System.out.print(prompt);
        return this.scanner.nextLine();
    }

    public boolean yesNo() {
        System.out.print("Yes or No? (y/n): ");
        String userInput = this.scanner.next();
        return userInput.toLowerCase().startsWith("y");
    }

    public boolean yesNo(String prompt) {
        System.out.print(prompt);
        String userInput = this.scanner.next();
        return userInput.toLowerCase().startsWith("y");
    }

    public int getInt() {
        String userInput = "";
        boolean invalid = true;
        int returnInt = 0;
        while(invalid) {
            try {
                System.out.print("Enter an integer: ");
                userInput = this.scanner.nextLine();
                returnInt = Integer.parseInt(userInput);
                invalid = false;
            } catch (NumberFormatException e) {
                System.out.print(userInput + " is not a valid integer. ");
            }
        }
        return returnInt;
    }

    public int getInt(String prompt) {
        String userInput = "";
        boolean invalid = true;
        int returnInt = 0;
        while(invalid) {
            try {
                System.out.print(prompt);
                userInput = this.scanner.nextLine();
                returnInt = Integer.parseInt(userInput);
                invalid = false;
            } catch (NumberFormatException e) {
                System.out.print(userInput + " is not a valid integer. ");
            }
        }
        return returnInt;
    }

    public int getInt(int min, int max) {
        int userInput = 0;
        do {
            System.out.print("Enter an int between " + min + " and " + max + ": ");
            userInput = getInt();
        } while (userInput <= min || userInput >= max);
        return userInput;
    }

    public double getDouble() {
        String userInput = "";
        boolean invalid = true;
        Double returnDouble = 0.0;
        while (invalid) {
            try {
                System.out.print("Enter a double: ");
                userInput = this.scanner.nextLine();
                returnDouble = Double.parseDouble(userInput);
                invalid = false;
            } catch (NumberFormatException e) {
                System.out.println(userInput + " is not a valid double. ");
            }
        }
        return returnDouble;
    }

    public double getDouble(String prompt) {
        String userInput = "";
        boolean invalid = true;
        Double returnDouble = 0.0;
        while (invalid) {
            try {
                System.out.print(prompt);
                userInput = this.scanner.nextLine();
                returnDouble = Double.parseDouble(userInput);
                invalid = false;
            } catch (NumberFormatException e) {
                System.out.println(userInput + " is not a valid double. ");
            }
        }
        return returnDouble;
    }

    public double getDouble(double min, double max) {
        double userInput;
        do {
            System.out.print("Enter a double between " + min + " and " + max + ": ");
            userInput = getDouble();
        } while (userInput <= min || userInput >= max);
        return userInput;
    }

}
