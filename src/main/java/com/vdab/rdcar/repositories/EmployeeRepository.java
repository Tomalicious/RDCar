package com.vdab.rdcar.repositories;


import com.vdab.rdcar.domain.Car;
import com.vdab.rdcar.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private CarRepository carRepository;

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

    @Transactional
    public void updateEmployee(Employee employee) {
        entityManager.merge(employee);
    }

    @Transactional
    public void addCarToHistory(Long id, Long carId) {
        Employee employee = findById(id);
        List<Car> carList = employee.getHistoryCars();
        carList.add(carRepository.findById(carId));
        employee.setHistoryCars(carList);
        entityManager.merge(employee);
    }
}

