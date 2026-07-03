package com.library.repository;

public class BookRepository {
    public void execute(String injectionType) {
        System.out.println("BookRepository executed via: " + injectionType);
    }
}
