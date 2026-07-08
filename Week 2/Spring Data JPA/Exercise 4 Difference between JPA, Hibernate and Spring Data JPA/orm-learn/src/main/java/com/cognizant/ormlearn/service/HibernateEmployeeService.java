package com.cognizant.ormlearn.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cognizant.ormlearn.model.Employee;
import jakarta.persistence.EntityManager;

@Service
public class HibernateEmployeeService {

    @Autowired
    private EntityManager entityManager;

    public Integer addEmployee(Employee employee) {
        Session session = entityManager.getEntityManagerFactory().createEntityManager().unwrap(Session.class);
        Transaction tx = null;
        Integer employeeID = null;
        try {
            tx = session.beginTransaction();
            session.persist(employee);
            tx.commit();
            employeeID = employee.getId();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }
}
