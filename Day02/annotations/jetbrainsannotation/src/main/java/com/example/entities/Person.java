package com.example.entities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.ApiStatus.Experimental;
import org.jetbrains.annotations.ApiStatus.Internal;

public class Person {
    @NotNull
    private String name;

    @NotNull
    @Range(from = 10, to = 100)
    private Integer age;

    @Nullable
    private String job;

    @Nullable
    private Double salary;

    public Person () {}

    public Person (String name, Integer age, String job, Double salary) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null.");
        if (age == null | age < 10 | age > 110) throw new IllegalAccessError("Age must be between 10 and 100.");
        this.name = name;
        this.age = age;
        this.job = job;
        this.salary = salary;
    }

    @Experimental
    public String aboutMe(@NotNull String message) {
        return message + " my name is " + name + " and im " + age + " old" + " and i work with " + job + " my salary is around " + netSalary();
    }

    @Internal
    private Double netSalary() {
        return salary - 300;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getJob() {
        return job;
    }

    public Double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
