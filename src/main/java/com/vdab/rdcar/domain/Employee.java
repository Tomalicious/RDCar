package com.vdab.rdcar.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Employee implements Serializable {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    @ManyToOne
    private Car car;

    private Integer age;

    @Temporal(TemporalType.DATE)
    private Date hireDate;

    private Integer functionLevel;

    @ManyToMany
    private List<Car> historyCars;

}
