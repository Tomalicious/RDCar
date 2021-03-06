package com.vdab.rdcar.repositories;


import com.vdab.rdcar.domain.Car;
import com.vdab.rdcar.domain.Employee;
import com.vdab.rdcar.domain.FunctionLevels;
import com.vdab.rdcar.domain.LeasedCar;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
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

    @Transactional
    public void newLease(LeasedCar newLease) {
            entityManager.persist(newLease);
        }

    public List<LeasedCar> getLeased() {
        return entityManager.createQuery("select l from LeasedCar l", LeasedCar.class).getResultList();
    }

    public LeasedCar getLeasedById(Long leaseId) {
        TypedQuery<LeasedCar> query = entityManager.createQuery("select l from LeasedCar l where l.id = :id" , LeasedCar.class);
        query.setParameter("id" , leaseId);
        return query.getSingleResult();
    }

    @Transactional
    public void updateLease(LeasedCar newLease) {
        entityManager.merge(newLease);
    }

    @Transactional
    public void deleteLease(Long leaseId) {
        entityManager.remove(getLeasedById(leaseId));
    }


    public List<LeasedCar> getLeasedByEmployeeId(Long id) {
        List<LeasedCar> leases = entityManager.createQuery("select l from LeasedCar l " , LeasedCar.class).getResultList();
        if(leases != null){
        return leases;
        }else{
            return null;
        }
    }
}
