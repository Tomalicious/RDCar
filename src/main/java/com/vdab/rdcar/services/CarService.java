package com.vdab.rdcar.services;


import com.vdab.rdcar.domain.Car;
import com.vdab.rdcar.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getCars() {
    return carRepository.getCars();
    }


    public Car findById(Long id) {
        return carRepository.findById(id);
    }
}
