package com.library;

import com.library.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService serviceConstructor = (BookService) context.getBean("bookServiceConstructor");
        serviceConstructor.testInjection("Constructor Injection");

        BookService serviceSetter = (BookService) context.getBean("bookServiceSetter");
        serviceSetter.testInjection("Setter Injection");

        context.close();
    }
}
