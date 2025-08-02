package com.smartlibrary.service;

import com.smartlibrary.model.Book;
import com.smartlibrary.utils.FileUtils;
import com.smartlibrary.exception.BookNotFoundException;

import java.util.*;

public class LibraryService {
    private List<Book> books;

    public LibraryService() {
        books = FileUtils.loadBooks();
    }

    public void addBook(Book book) {
        books.add(book);
        FileUtils.saveBooks(books);
        System.out.println("✅ Book added successfully!");
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("⚠️ No books available.");
            return;
        }
        books.forEach(System.out::println);
    }

    public void issueBook(int bookId) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getId() == bookId) {
                if (!book.isIssued()) {
                    book.setIssued(true);
                    FileUtils.saveBooks(books);
                    System.out.println("✅ Book issued successfully!");
                    return;
                } else {
                    System.out.println("⚠️ Book is already issued.");
                    return;
                }
            }
        }
        throw new BookNotFoundException("Book ID not found: " + bookId);
    }

    public void returnBook(int bookId) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getId() == bookId) {
                if (book.isIssued()) {
                    book.setIssued(false);
                    FileUtils.saveBooks(books);
                    System.out.println("✅ Book returned successfully!");
                    return;
                } else {
                    System.out.println("⚠️ Book was not issued.");
                    return;
                }
            }
        }
        throw new BookNotFoundException("Book ID not found: " + bookId);
    }
}
