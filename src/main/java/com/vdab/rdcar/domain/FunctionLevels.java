package com.vdab.rdcar.domain;


import lombok.Getter;

@Getter
public enum FunctionLevels {
        A(2 , "2"),//0
        B(3 , "3"),//1
        C(4 , "4"),//2
        D(5 , "5"),//3
        E(6 ,"6"),//4
        F(7 , "6+");//5



    private final Integer id;
    private final String displayName;


    FunctionLevels(Integer id, String displayName){
        this.id = id;
        this.displayName = displayName;
    }
}
