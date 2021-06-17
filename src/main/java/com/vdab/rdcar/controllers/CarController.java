package com.vdab.rdcar.controllers;

import com.vdab.rdcar.domain.Car;
import com.vdab.rdcar.domain.Employee;
import com.vdab.rdcar.services.CarService;
import com.vdab.rdcar.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/editCurrent/{id}")
    public String addCurrent(@PathVariable("id") Long id , Model model) {
        List<Car> historyList = employeeService.findById(id).getHistoryCars();
        model.addAttribute("historyList" , historyList);
        model.addAttribute("editEmployee",employeeService.findById(id));
        model.addAttribute("newEmployee" , new Employee());
        return "editCurrent";
    }

    @PostMapping(value = "/editCurrent/{id}")
    public String addCurrentCar( @PathVariable("id") Long id ,@ModelAttribute Employee newEmployee) {
        Employee employee = employeeService.findById(id);
        employee.setCurrentCar(newEmployee.getCurrentCar());
//        employee.setCurrentCar(newCar);
        employeeService.updateEmployee(employee);
        return "redirect:/";
    }

    @GetMapping(value = "/orderCar/{id}")
    public String showOrderCar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("allAvailableCars", carService.getCarsAvailable(id));
        model.addAttribute("editEmployee" ,employeeService.findById(id));
        return "orderCar";
    }

    @GetMapping(value = "/orderedCar/{id}/{carId}")
    public String addNewCar( @PathVariable("id") Long id , @PathVariable("carId") Long carId) {
        Employee ifEmp = employeeService.findById(id);
        Car ifCar = carService.findById(carId);
        if(ifCar.getCategory().ordinal() < ifEmp.getFunctionLevel().ordinal()) {
            return "forward:/downgradePage/" + id + "/" + carId ;
        }else if(ifCar.getCategory().ordinal() > ifEmp.getFunctionLevel().ordinal()){
            return "forward:/upgradePage/" + id + "/" + carId ;
        }else {
            employeeService.addCarToHistory(id, carId);
            Employee employee = employeeService.findById(id);
            employee.setCurrentCar(carService.findById(carId));
            employee.setAmountOfMaintenances(0);
            employee.setCurrentCarMileage("0");
            employeeService.updateEmployee(employee);
            return "redirect:/";
        }
    }

    @GetMapping(value = "/upgradePage/{id}/{carId}")
    public String showUpgradepage(@PathVariable("id") Long id,@PathVariable("carId") Long carId, Model model) {
        String upgradeAmount = carService.findById(carId).getUpgradeAmount();
        Float upgrade = Float.parseFloat(upgradeAmount);
        Float upgraded = Math.round(upgrade*100.0F)/100.0F;
        model.addAttribute("editEmployee" ,employeeService.findById(id));
        model.addAttribute("up",upgraded );
        model.addAttribute("car", carService.findById(carId));
        return "upgradePage";
    }

    @GetMapping(value = "/downgradePage/{id}/{carId}")
    public String showDowngrade(@PathVariable("id") Long id ,@PathVariable("carId") Long carId, Model model) {
        String downgradeAmount = carService.findById(carId).getDowngradeAmount();
        Float downgrade = Float.parseFloat(downgradeAmount);
        Float downgraded = Math.round(downgrade*100.0F)/100.0F;
        model.addAttribute("editEmployee" ,employeeService.findById(id));
        model.addAttribute("down",downgraded );
        model.addAttribute("car", carService.findById(carId));
        return "downgradePage";
    }

    @GetMapping(value = "/orderedCarUpDown/{id}/{carId}")
    public String addNewCarUpgradedOrDowngraded( @PathVariable("id") Long id , @PathVariable("carId") Long carId) {
            employeeService.addCarToHistory(id, carId);
            Employee employee = employeeService.findById(id);
            employee.setCurrentCar(carService.findById(carId));
            employee.setAmountOfMaintenances(0);
            employee.setCurrentCarMileage("0");
            employeeService.updateEmployee(employee);
            return "redirect:/";
    }


    @GetMapping(value = "/editMileageCurrent/{id}")
    public String showEditMileage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("editEmployee" ,employeeService.findById(id));
        model.addAttribute("newCar" , new Car());

        return "editMileage";
    }

    @PostMapping(value = "/editMileage/{id}")
    public String editEmployee(@PathVariable("id") Long id ,  @ModelAttribute Employee newEmployee){
        Employee employee = employeeService.findById(id);
        employee.setCurrentCarMileage(newEmployee.getCurrentCarMileage());
        employeeService.updateEmployee(employee);
        return "redirect:/";
    }



}
