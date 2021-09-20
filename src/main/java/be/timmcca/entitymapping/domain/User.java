package be.timmcca.entitymapping.domain;

import be.timmcca.entitymapping.domain.common.IAggregateRoot;

public class User implements IAggregateRoot {
    private final String name;
    private BankAccount checkingAccount;
    private BankAccount savingsAccount;

    public User(String name) {
        this(name, new BankAccount(0), new BankAccount(0));
    }

    public User(String name, BankAccount checkingAccount, BankAccount savingsAccount) {
        this.name = name;
        this.checkingAccount = checkingAccount;
        this.savingsAccount = savingsAccount;
    }

    public String getName() {
        return name;
    }

    public BankAccount getCheckingAccount() {
        return checkingAccount;
    }

    public BankAccount getSavingsAccount() {
        return savingsAccount;
    }

    public void depositToChecking(int amount) {
        checkingAccount = checkingAccount.deposit(amount);
    }

    public void depositToSavings(int amount) {
        savingsAccount = savingsAccount.deposit(amount);
    }

    public void transferToChecking(int amount) {
        savingsAccount = savingsAccount.withdraw(amount);
        checkingAccount = checkingAccount.deposit(amount);
    }

    public void transferToSavings(int amount) {
        checkingAccount = checkingAccount.withdraw(amount);
        savingsAccount = savingsAccount.deposit(amount);
    }
}
