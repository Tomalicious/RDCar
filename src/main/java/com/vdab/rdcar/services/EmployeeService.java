package com.vdab.rdcar.services;
import com.vdab.rdcar.domain.Employee;
import com.vdab.rdcar.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }

    public void removeById(Long id) {
        employeeRepository.removeById(id);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.updateEmployee(employee);

    }

    public void addEmployee(Employee newEmployee) {
       employeeRepository.addEmployee(newEmployee);
    }

    public void addCarToHistory(Long id , Long carId) {
        employeeRepository.addCarToHistory(id , carId);
    }
}
