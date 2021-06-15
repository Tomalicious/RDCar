package com.vdab.rdcar.services;
import com.vdab.rdcar.domain.Employee;
import com.vdab.rdcar.domain.FunctionLevels;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;


@SpringBootTest
class CarServiceTest {
    @Autowired
    private CarService carService;


    @Test
    void getCarsAvailable() {
        Employee employee = Employee.builder().id(1L).functionLevel(FunctionLevels.B).build();
        List<FunctionLevels> carsAvailable = carService.getFunctionLevelsBetweenBounds(employee);
        Assertions.assertFalse(carsAvailable.isEmpty());
        carsAvailable.stream().forEach(functionLevels1 -> {
            Assertions.assertTrue(functionLevels1 == FunctionLevels.A || functionLevels1 == FunctionLevels.B || functionLevels1 == FunctionLevels.C);
            Assertions.assertFalse(functionLevels1 == FunctionLevels.F);
        });
    }


    @Test
    void getCarsAvailableIfEmpIsA() {
        Employee employee = Employee.builder().id(1L).functionLevel(FunctionLevels.A).build();
        List<FunctionLevels> carsAvailable = carService.getFunctionLevelsBetweenBounds(employee);
        Assertions.assertFalse(carsAvailable.isEmpty());
        carsAvailable.stream().forEach(functionLevels1 -> {
            Assertions.assertTrue(functionLevels1 == FunctionLevels.B || functionLevels1 == FunctionLevels.A);
            Assertions.assertFalse(functionLevels1 == FunctionLevels.F);
        });
    }
}
