package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("The Hobbit", "J.R.R. Tolkien", "12345");
        Book book2 = new Book("LOTR", "J.R.R. Tolkien", "12345");
        System.out.println(book1.equals(book2)); // Should print true
        System.out.println(book1); // Should print book details
    }
}