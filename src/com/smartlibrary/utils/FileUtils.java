package com.smartlibrary.utils;

import com.smartlibrary.model.Book;
import com.smartlibrary.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static final String BOOK_FILE = "data/books.dat";
    private static final String USER_FILE = "data/users.dat";


    public static List<Book> loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOK_FILE))) {
            return (List<Book>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void saveBooks(List<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOK_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println("⚠️ Error saving books: " + e.getMessage());
        }
    }

    public static List<User> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            return (List<User>) ois.readObject();
        } catch (Exception e) {
            // Default users if file not found
            List<User> defaultUsers = new ArrayList<>();
            defaultUsers.add(new User("admin", "admin123", true));
            defaultUsers.add(new User("student", "pass123", false));
            saveUsers(defaultUsers);
            return defaultUsers;
        }
    }

    public static void saveUsers(List<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("⚠️ Error saving users: " + e.getMessage());
        }
    }
}
