package com.vdab.rdcar.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @Transient
    private Long amountYearsService;

    @Enumerated(EnumType.STRING)
    private FunctionLevels functionLevel;

    public Date getHireDate() {
       if(this.hireDate != null) {
           LocalDate now = LocalDate.now();
           LocalDate hiredDate =  Instant.ofEpochMilli(this.hireDate.getTime())
                   .atZone(ZoneId.systemDefault())
                   .toLocalDate();


           this.amountYearsService = ChronoUnit.YEARS.between(hiredDate , now);
       }
        return this.hireDate;

    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @Column
//    @JoinTable(name = "employee_car_history",
//            joinColumns = {@JoinColumn(name = "employee_id")},
//            inverseJoinColumns = {@JoinColumn(name = "car_id")
//            })
    private List<Car> historyCars = new ArrayList<>();


    @OneToOne(cascade = CascadeType.REFRESH)
    private Car currentCar;

}
