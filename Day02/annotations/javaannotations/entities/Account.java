package entities;

import entities.exceptions.InsufficientFundsException;

public abstract class Account {
    public String name;
    public String number;
    public Double balance;

    public Account() {}

    public Account(String name, String number, double balance) {
        this.name = name;
        this.number = number;
        this.balance = balance;     
    }

    public abstract void withdraw(double value) throws InsufficientFundsException;

    public abstract void deposit(double value);

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Double getBalance() {
        return balance;
    }

    public void setName(String value) {
        name = value;
    }

    public void setNumber(String value) {
        number = value;
    }

    public void setBalance(double value) {
        balance = value;
    }
}