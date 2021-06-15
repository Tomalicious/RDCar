package com.vdab.rdcar.controllers;
import com.vdab.rdcar.domain.Car;
import com.vdab.rdcar.domain.Employee;
import com.vdab.rdcar.domain.FunctionLevels;
import com.vdab.rdcar.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/")
    public String showEmployeePage(Model model) {
        model.addAttribute("allEmployees", employeeService.getEmployees());
        return "index";
    }

    @GetMapping(value= "/removeEmployee/{id}")
    public String deleteAnEmployee(@PathVariable("id") Long id) {
        employeeService.removeById(id);
        return "redirect:/";
    }



    @GetMapping(value = "/editEmployee/{id}")
    public String showEditPage(@PathVariable("id") Long id, Model model){
        List<Car> historyList = employeeService.findById(id).getHistoryCars();
        model.addAttribute("historyList" , historyList);
        model.addAttribute("editEmployee",employeeService.findById(id));
        model.addAttribute("functionLevels" ,  FunctionLevels.values());
        model.addAttribute("newEmployee" ,  Employee newEmployee = new Employee();

        return "edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String editEmployee(@ModelAttribute Employee employee){
        employeeService.updateEmployee(employee);
        return "redirect:/";
    }

    @GetMapping(value = "/addEmployee")
    public String showAddPage(Model model){
        model.addAttribute("newEmployee" , new Employee());
        model.addAttribute("functionLevels" ,  FunctionLevels.values());
        return "addEmployee";
    }

    @PostMapping(value = "/add")
    public String add(@ModelAttribute Employee newEmployee) {
        employeeService.addEmployee(newEmployee);
        return "redirect:/";
    }

}
