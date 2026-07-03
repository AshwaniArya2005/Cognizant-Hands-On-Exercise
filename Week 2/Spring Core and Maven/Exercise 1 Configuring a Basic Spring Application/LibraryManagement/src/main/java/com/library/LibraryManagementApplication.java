package com.library;

import com.library.service.BookService;
import com.library.repository.BookRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService service = (BookService) context.getBean("bookService");
        BookRepository repository = (BookRepository) context.getBean("bookRepository");
        service.printServiceInfo();
        repository.printRepositoryInfo();
        context.close();
    }
}
