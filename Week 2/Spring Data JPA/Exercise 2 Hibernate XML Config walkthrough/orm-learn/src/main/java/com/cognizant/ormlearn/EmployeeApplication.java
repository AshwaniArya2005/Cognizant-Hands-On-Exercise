package com.cognizant.ormlearn;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.cognizant.ormlearn.model.Employee;

public class EmployeeApplication {

    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        EmployeeApplication app = new EmployeeApplication();
        app.addEmployee("Zara", "Ali", 5000);
        app.addEmployee("Daisy", "Das", 6000);
        app.listEmployees();
        factory.close();
    }

    public Integer addEmployee(String fname, String lname, int salary) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try {
            tx = session.beginTransaction();
            Employee employee = new Employee();
            employee.setFirstName(fname);
            employee.setLastName(lname);
            employee.setSalary(salary);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    public void listEmployees() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM com.cognizant.ormlearn.model.Employee").list();
            for (Object obj : employees) {
                Employee employee = (Employee) obj;
                System.out.println("Employee: " + employee.getFirstName() + " " + employee.getLastName() + " - Salary: " + employee.getSalary());
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
