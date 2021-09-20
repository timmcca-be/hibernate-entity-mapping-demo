package be.timmcca.entitymapping.application;

import be.timmcca.entitymapping.domain.User;
import be.timmcca.entitymapping.domain.common.IUnitOfWork;

public class BankingService {
    private final IUnitOfWork unitOfWork;

    public BankingService(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public void openAccount(String name) {
        unitOfWork.getUserRepository().add(new User(name));
        unitOfWork.commit();
    }

    public User getUser(int id) {
        var user = unitOfWork.getUserRepository().get(id);
        unitOfWork.commit();
        return user;
    }

    public void depositToChecking(int userID, int amount) {
        var user = unitOfWork.getUserRepository().get(userID);
        user.depositToChecking(amount);
        unitOfWork.commit();
    }

    public void depositToSavings(int userID, int amount) {
        var user = unitOfWork.getUserRepository().get(userID);
        user.depositToSavings(amount);
        unitOfWork.commit();
    }

    public void transferToChecking(int userID, int amount) {
        var user = unitOfWork.getUserRepository().get(userID);
        user.transferToChecking(amount);
        unitOfWork.commit();
    }

    public void transferToSavings(int userID, int amount) {
        var user = unitOfWork.getUserRepository().get(userID);
        user.transferToSavings(amount);
        unitOfWork.commit();
    }
}
