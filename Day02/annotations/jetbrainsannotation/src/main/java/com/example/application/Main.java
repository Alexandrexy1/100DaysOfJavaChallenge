package com.example.application;

import com.example.entities.Person;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person();

        System.out.println(person1.aboutMe("Hello"));
    }
}