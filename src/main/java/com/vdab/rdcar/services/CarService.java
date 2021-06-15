package com.vdab.rdcar.services;


import com.vdab.rdcar.domain.Car;
import com.vdab.rdcar.domain.Employee;
import com.vdab.rdcar.domain.FunctionLevels;
import com.vdab.rdcar.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CarRepository carRepository;

    public List<Car> getCars() {
        return carRepository.getCars();
    }


    public Car findById(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> getCarsAvailable(Long id) {
        FunctionLevels lower;
        FunctionLevels upper;
        Employee emp = employeeService.findById(id);

        // lvl2 > emp.lvl < lvl4
        List<FunctionLevels> functionLevels = getFunctionLevelsBetweenBounds(emp);
//        if (emp.getFunctionLevel() == FunctionLevels.A) {
//            lower = FunctionLevels.A;
//        }else if(emp.getFunctionLevel() == FunctionLevels.F){
//            lower = FunctionLevels.E;
//            upper = FunctionLevels.F;
//        } else {
//            lower = FunctionLevels.values()[emp.getFunctionLevel().ordinal() - 1];
//            upper = FunctionLevels.values()[emp.getFunctionLevel().ordinal() + 1];
//        }
        return carRepository.getCarsAvailable(functionLevels);
    }

    protected List<FunctionLevels> getFunctionLevelsBetweenBounds(Employee emp) {
//        List<FunctionLevels> functionLevels = new ArrayList<>();
//        if (emp.getFunctionLevel() == FunctionLevels.A) {
//            functionLevels.add(FunctionLevels.A);
//            functionLevels.add(FunctionLevels.B);
//        } else if (emp.getFunctionLevel() == FunctionLevels.F) {
//            functionLevels.add(FunctionLevels.F);
//            functionLevels.add(FunctionLevels.E);
//        } else {
//            functionLevels.add(emp.getFunctionLevel());
//            functionLevels.add(FunctionLevels.values()[emp.getFunctionLevel().ordinal() + 1]);
//            functionLevels.add(FunctionLevels.values()[emp.getFunctionLevel().ordinal() - 1]);
//        }
        List<FunctionLevels> functionLevels = Arrays.stream(FunctionLevels.values()).filter(functionLevels1 -> functionLevels1.ordinal() >= emp.getFunctionLevel().ordinal() - 1).filter(functionLevels1 -> functionLevels1.ordinal() <= emp.getFunctionLevel().ordinal() + 1).collect(Collectors.toList());
        return functionLevels;
    }
}
