package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    public void printInfo() {
        System.out.println("BookRepository managed via @Repository scan annotation.");
    }
}
