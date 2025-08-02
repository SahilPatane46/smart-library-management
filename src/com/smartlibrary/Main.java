package com.smartlibrary;

import java.util.Scanner;
import com.smartlibrary.exception.BookNotFoundException;
import com.smartlibrary.model.Book;
import com.smartlibrary.model.User;
import com.smartlibrary.service.AuthenticationService;
import com.smartlibrary.service.LibraryService;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthenticationService auth = new AuthenticationService();
        LibraryService library = new LibraryService();

        System.out.print("👤 Username: ");
        String username = sc.nextLine();
        System.out.print("🔒 Password: ");
        String password = sc.nextLine();

        User currentUser = auth.login(username, password);
        if (currentUser == null) {
            System.out.println("❌ Invalid credentials. Exiting...");
            return;
        }

        System.out.println("✅ Login successful! Welcome " + currentUser.getUsername());

        while (true) {
            System.out.println("\n📚 LIBRARY MENU");
            System.out.println("1. View Books");
            System.out.println("2. Add Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        library.displayBooks();
                        break;
                    case 2:
                        if (currentUser.isAdmin()) {
                            System.out.print("Enter Book ID: ");
                            int id = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter Title: ");
                            String title = sc.nextLine();
                            System.out.print("Enter Author: ");
                            String author = sc.nextLine();
                            Book book = new Book(id, title, author);
                            library.addBook(book);
                        } else {
                            System.out.println("⚠️ Access Denied. Only Admins can add books.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter Book ID to issue: ");
                        int issueId = sc.nextInt();
                        library.issueBook(issueId);
                        break;
                    case 4:
                        System.out.print("Enter Book ID to return: ");
                        int returnId = sc.nextInt();
                        library.returnBook(returnId);
                        break;
                    case 5:
                        System.out.println("👋 Exiting... Thank you!");
                        return;
                    default:
                        System.out.println("⚠️ Invalid choice.");
                }
            } catch (BookNotFoundException e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
    }
}
