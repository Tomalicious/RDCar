package com.vdab.rdcar.services;


import com.vdab.rdcar.domain.Car;
import com.vdab.rdcar.domain.Employee;
import com.vdab.rdcar.domain.FunctionLevels;
import com.vdab.rdcar.domain.LeasedCar;
import com.vdab.rdcar.repositories.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CarService {

    @Autowired
    private JavaMailSender jms;

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

    @Scheduled(cron = "0 0/1 * * * *")
    public void notifyEmployee() {
        System.out.println("notify");
        List<Employee> checkMileage = employeeService.getEmployees();
        if (!checkMileage.isEmpty()) {
            for (Employee e : checkMileage) {
                if (e.getCurrentCar() != null) {
                    Integer mileage = Integer.parseInt(e.getCurrentCarMileage());
                    Integer maxMileage = Integer.parseInt(e.getCurrentCar().getMaxKm());
                    if (mileage > maxMileage) {
                        SimpleMailMessage email = new SimpleMailMessage();
                        email.setFrom("tototonique@gmail.com");
                        email.setTo(e.getEmail());
                        email.setSubject("Order Car");
                        email.setText("You have exceeded your max car mileage , please contact me to order a new car!");
                        jms.send(email);
                    } else if ((mileage / 30000) >= e.getAmountOfMaintenances() + 1) {
                        e.setAmountOfMaintenances(e.getAmountOfMaintenances() + 1);

                        SimpleMailMessage email = new SimpleMailMessage();
                        email.setFrom("tototonique@gmail.com");
                        email.setTo(e.getEmail());
                        email.setSubject("Car Maintenance");
                        email.setText("You are obliged to go on a Maintenance with your car");
                        jms.send(email);
                    }
                }
            }
        }
    }
//
//    @Scheduled(cron = "0 0/1 * * * *")
//    public void incrementMileage() {
//        List<Employee> employeeList = employeeService.getEmployees();
//        for (Employee e : employeeList) {
//            Integer mileage = Integer.parseInt(e.getCurrentCarMileage());
//            mileage += 2000;
//            String updatedMileage = mileage.toString();
//            e.setCurrentCarMileage(updatedMileage);
//        }
//    }

    public void newLease(LeasedCar newLease) {
        carRepository.newLease(newLease);
    }

    public List<LeasedCar> getLeased() {
        return carRepository.getLeased();
    }

    public LeasedCar getLeasedById(Long leaseId) {
        return carRepository.getLeasedById(leaseId);
    }

    public void updateLease(LeasedCar newLease) {
        carRepository.updateLease(newLease);
    }

    public void deleteLease(Long leaseId) {
        carRepository.deleteLease(leaseId);
    }

    public LeasedCar getLeasedByEmployeeId(Long id) {
         List<LeasedCar> leases = carRepository.getLeasedByEmployeeId(id);
         if(leases == null) {
             return null;
         }else{
             List<LeasedCar> noNull = leases.stream().filter(leasedCar -> leasedCar.getEmployee() != null).collect(Collectors.toList());
             return noNull.stream().filter(leasedCar -> leasedCar.getEmployee().getId() == id).findFirst().get();
         }
    }

    public List<LeasedCar> getLeasedAssigned() {
        List<LeasedCar> leasedCars = carRepository.getLeased();
        List<LeasedCar> filteredLease = leasedCars.stream().filter(leasedCar -> leasedCar.getEmployee() != null).collect(Collectors.toList());
        return filteredLease;
    }

    public List<LeasedCar> getLeasedNotAssigned() {
        List<LeasedCar> leasedCars = carRepository.getLeased();
        List<LeasedCar> filteredLease = leasedCars.stream().filter(leasedCar -> leasedCar.getEmployee() == null).collect(Collectors.toList());
        return filteredLease;
    }
}
