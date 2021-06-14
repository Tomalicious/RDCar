package com.vdab.rdcar.controllers;

import com.vdab.rdcar.domain.Car;
import com.vdab.rdcar.domain.Employee;
import com.vdab.rdcar.services.CarService;
import com.vdab.rdcar.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/addingToEmployee/{id}")
    public String showEmployeePage(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("allCars", carService.getCars());
        model.addAttribute("chosenEmployee" , employee);
        return "addingTo";
    }

    @GetMapping(value = "/addingToHistory/{id}/{carId}")
    public String addedHistory(@PathVariable("id") Long id, @PathVariable("carId") Long carId) {
        employeeService.addCarToHistory(id , carId);
        return "redirect:/";
    }
}
