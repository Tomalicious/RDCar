package com.vdab.rdcar.domain;


import com.vdab.rdcar.repositories.FunctionLevelConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = FunctionLevelConverter.class)
    private FunctionLevels category;

    private String co2;

    private String brand;

    private String model;

    private String pack;

    private String fuel;

    private String fuelTank;

    private String towingBracket;

    private String typeRim;

    private String maxKmYear;

    private String idealKm;

    private String maxKm;

    private String listPrice;

    private String benefitMonth;

    private String upgradeAmount;

    private String downgradeAmount;

    @Lob
    private Byte[] image;
}


