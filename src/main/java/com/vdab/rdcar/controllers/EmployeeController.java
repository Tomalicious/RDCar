package com.vdab.rdcar.controllers;
import com.vdab.rdcar.domain.*;
import com.vdab.rdcar.repositories.AppointmentRepository;
import com.vdab.rdcar.services.AppointmentService;
import com.vdab.rdcar.services.CarService;
import com.vdab.rdcar.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private CarService carService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(value = "/")
    public String showEmployeePage(Model model) {
        model.addAttribute("allEmployees", employeeService.getEmployees());
        model.addAttribute("newAppointment", new Appointment());
        return "index";
    }

    @GetMapping(value= "/removeEmployee/{id}")
    public String deleteAnEmployee(@PathVariable("id") Long id) {
            carService.deleteLease(carService.getLeasedByEmployeeId(id).getId());
            employeeService.removeById(id);

        return "redirect:/";
    }

    @GetMapping(value = "/editEmployee/{id}")
    public String showEditPage(@PathVariable("id") Long id, Model model){
        model.addAttribute("editEmployee",employeeService.findById(id));
        model.addAttribute("functionLevels" ,  FunctionLevels.values());
        model.addAttribute("newEmployee" , new Employee());
        return "edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String editEmployee(@ModelAttribute Employee newEmployee){
        employeeService.updateEmployee(newEmployee);
        return "redirect:/";
    }


    @PostMapping(value = "/makeAppointment")
    public String editAppointment(@ModelAttribute Appointment appointment){
        appointmentService.makeAppointment(appointment);
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
