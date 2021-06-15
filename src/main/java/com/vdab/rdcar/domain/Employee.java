package com.vdab.rdcar.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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

    private Integer functionLevel;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @Column
//    @JoinTable(name = "employee_car_history",
//            joinColumns = {@JoinColumn(name = "employee_id")},
//            inverseJoinColumns = {@JoinColumn(name = "car_id")
//            })
    private List<Car> historyCars = new ArrayList<>();


    @OneToOne
    private Car currentCar;

}
