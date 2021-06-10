package com.vdab.rdcar.domain;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;


@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car implements Serializable {

    @Id
    private Long id;

    private String category;

    private String currentCar;


}


