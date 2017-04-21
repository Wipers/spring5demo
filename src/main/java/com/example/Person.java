package com.example;

import lombok.Data;

/**
 * Created by wiper on 2017/4/21.
 */
@Data
public class Person{

    private  String name;

    public Person(String name) {
        this.name = name;
    }
}
