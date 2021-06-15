package com.vdab.rdcar.repositories;


import com.vdab.rdcar.domain.Car;
import com.vdab.rdcar.domain.Employee;
import com.vdab.rdcar.domain.FunctionLevels;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Car> getCars() {
        return entityManager.createQuery("select e from Car e", Car.class).getResultList();
    }

    public Car findById(Long carId) {
        TypedQuery<Car> query = entityManager.createQuery("select c from Car c where c.id = :id" , Car.class);
        query.setParameter("id" , carId);
        return query.getSingleResult();
    }

    public List<Car> getCarsAvailable(List<FunctionLevels> functionLevels) {
        TypedQuery<Car> query = entityManager.createQuery("select c from Car c where c.category in (:funtions)" , Car.class);
        query.setParameter("funtions" , functionLevels);
        return query.getResultList();
    }
}
