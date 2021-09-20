package be.timmcca.entitymapping.infrastructure.users;

import be.timmcca.entitymapping.domain.User;
import be.timmcca.entitymapping.infrastructure.users.UserEntity.IState;

public class Sealed implements UserEntity.IState {
    private final User domainObject;

    public Sealed(User domainObject) {
        this.domainObject = domainObject;
    }

    @Override
    public String getName() {
        return domainObject.getName();
    }

    @Override
    public void setName(String name) {
        throw new IllegalStateException("Entity is sealed");
    }

    @Override
    public int getCheckingBalance() {
        return domainObject.getCheckingAccount().getBalance();
    }

    @Override
    public void setCheckingBalance(int balance) {
        throw new IllegalStateException("Entity is sealed");
    }

    @Override
    public int getSavingsBalance() {
        return domainObject.getSavingsAccount().getBalance();
    }

    @Override
    public void setSavingsBalance(int balance) {
        throw new IllegalStateException("Entity is sealed");
    }

    @Override
    public IState createDomainObject() {
        return this;
    }

    @Override
    public User getDomainObject() {
        return domainObject;
    }

    
}
