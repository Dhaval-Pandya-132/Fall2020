package com.multithreding.Question2;

import java.math.BigDecimal;

public class Employee {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private BigDecimal salary;

    public Employee(String generateRandomName, int generateRandomAge, BigDecimal generateRandomSalary) {
        this.name= generateRandomName;
        this.age=generateRandomAge;
        this.salary=generateRandomSalary;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
