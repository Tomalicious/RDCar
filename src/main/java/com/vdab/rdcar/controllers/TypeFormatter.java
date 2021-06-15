package com.vdab.rdcar.controllers;

import com.vdab.rdcar.domain.Car;
import com.vdab.rdcar.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;


@Component
public class TypeFormatter implements Formatter<Car>{

    @Autowired
    private CarService carService;

    @Override
    public Car parse(String text, Locale locale) throws ParseException {
        return carService.findById(Long.valueOf(text));
    }

    @Override
    public String print(Car object, Locale locale) {
        return (object != null ? String.valueOf(object.getId()) : "");
    }
}
