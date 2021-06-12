package com.vdab.rdcar.domain;


import lombok.Getter;

@Getter
public enum FunctionLevels {
        A(2 , "2"),
        B(3 , "3"),
        C(4 , "4"),
        D(5 , "5"),
        E(6 ,"6"),
        F(7 , "6+");



    private final Integer id;
    private final String name;


    FunctionLevels(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
