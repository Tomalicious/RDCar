package com.vdab.rdcar.repositories;


import com.vdab.rdcar.domain.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Employee> getEmployees() {
        return entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
    }

    @Transactional
    public void removeById(Long id) {
        Employee emp = findById(id);
        entityManager.remove(emp);
    }


    public Employee findById(Long id) {
        TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e where e.id = :id", Employee.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Transactional
    public void addEmployee(Employee employee) {
        entityManager.persist(employee);
    }
}
