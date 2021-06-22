package com.vdab.rdcar.services;


import com.vdab.rdcar.domain.Appointment;
import com.vdab.rdcar.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {


    @Autowired
    AppointmentRepository appointmentRepository;

    public void makeAppointment(Appointment appointment) {
        appointmentRepository.makeAppointment(appointment);
    }
}
