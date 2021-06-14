package com.vdab.rdcar.domain;


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

    private String aCategory;

    private String bCo2;

    private String cBrand;

    private String dModel;

    private String ePack;

    private String fFuel;

    private String gFuelTank;

    private String hTowingBracket;

    private String iTypeRim;

    private String jMaxKmYear;

    private String kIdealKm;

    private String lMaxKm;

    private String mListPrice;

    private String nBenefitMonth;

    private String oUpgradeAmount;

    private String pDowngradeAmount;

}


