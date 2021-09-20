package be.timmcca.entitymapping.infrastructure.users;

import be.timmcca.entitymapping.domain.BankAccount;
import be.timmcca.entitymapping.domain.User;
import be.timmcca.entitymapping.infrastructure.users.UserEntity.IState;

public class Initializing implements UserEntity.IState {
    private String name;
    private int checkingBalance;
    private int savingsBalance;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getCheckingBalance() {
        return checkingBalance;
    }

    @Override
    public void setCheckingBalance(int balance) {
        this.checkingBalance = balance;
    }

    @Override
    public int getSavingsBalance() {
        return savingsBalance;
    }

    @Override
    public void setSavingsBalance(int balance) {
        this.savingsBalance = balance;
    }

    @Override
    public IState createDomainObject() {
        return new Sealed(new User(name, new BankAccount(checkingBalance), new BankAccount(savingsBalance)));
    }

    @Override
    public User getDomainObject() {
        throw new IllegalStateException("Domain object not created");
    }
    
}
