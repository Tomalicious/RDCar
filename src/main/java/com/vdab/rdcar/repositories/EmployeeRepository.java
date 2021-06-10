package com.vdab.rdcar.repositories;


import com.vdab.rdcar.domain.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Employee> getEmployees() {
        return entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
    }
}
