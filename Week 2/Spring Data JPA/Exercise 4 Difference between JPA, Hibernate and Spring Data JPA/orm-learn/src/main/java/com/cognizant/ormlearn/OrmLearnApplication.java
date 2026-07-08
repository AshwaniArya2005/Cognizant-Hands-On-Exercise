package com.cognizant.ormlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.HibernateEmployeeService;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        HibernateEmployeeService hibernateService = context.getBean(HibernateEmployeeService.class);
        EmployeeService springDataService = context.getBean(EmployeeService.class);

        LOGGER.info("Inside main");

        Employee emp1 = new Employee();
        emp1.setFirstName("John");
        emp1.setLastName("Doe");
        emp1.setSalary(4000);
        hibernateService.addEmployee(emp1);
        LOGGER.debug("Added using Hibernate API: {}", emp1.getFirstName() + " " + emp1.getLastName());

        Employee emp2 = new Employee();
        emp2.setFirstName("Jane");
        emp2.setLastName("Smith");
        emp2.setSalary(5000);
        springDataService.addEmployee(emp2);
        LOGGER.debug("Added using Spring Data JPA: {}", emp2.getFirstName() + " " + emp2.getLastName());
    }
}
