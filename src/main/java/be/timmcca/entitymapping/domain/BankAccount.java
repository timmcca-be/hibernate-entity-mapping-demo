package be.timmcca.entitymapping.domain;

import be.timmcca.entitymapping.domain.common.IValueObject;

public class BankAccount implements IValueObject {
    private final int balance;

    public BankAccount(int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Cannot create account with negative balance");
        }
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public BankAccount deposit(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot deposit a negative amount");
        }
        return new BankAccount(balance + amount);
    }

    public BankAccount withdraw(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot withdraw a negative amount");
        }
        return new BankAccount(balance - amount);
    }
}
