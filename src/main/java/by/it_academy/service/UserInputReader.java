package by.it_academy.service;

import by.it_academy.model.User;

import java.util.Scanner;

public class UserInputReader {

    public static User createUser() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        boolean valid = true;
        do {
            try {
                System.out.println("Enter user's name: ");
                user.setUserName(scanner.nextLine());
                System.out.println("Enter user's address: ");
                user.setAddress(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid data, Try again");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        } while (!valid);
        return user;
    }
}
