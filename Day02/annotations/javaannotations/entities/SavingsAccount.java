package entities;

import entities.exceptions.InsufficientFundsException;

public class SavingsAccount extends Account {

    @Override
    public void withdraw(double value) throws InsufficientFundsException {
        if (value > balance) throw new UnsupportedOperationException("Value must be less than the balance.");
        balance -= value;
    }

    @Override
    public void deposit(double value) {
        balance += value;
    }

    @Deprecated
    public void oldDeposit(double value) {
        balance += (value - 1);
    }
}
