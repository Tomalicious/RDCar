package com.vdab.rdcar.repositories;


import com.vdab.rdcar.domain.Appointment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class AppointmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void makeAppointment(Appointment appointment) {
        entityManager.persist(appointment);
    }
}
