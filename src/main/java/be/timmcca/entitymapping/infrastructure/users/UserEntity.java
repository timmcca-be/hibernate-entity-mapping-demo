package be.timmcca.entitymapping.infrastructure.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import be.timmcca.entitymapping.domain.User;

@Entity
@Table(name = "Users")
public class UserEntity {
    public interface IState {
        public String getName();
        public void setName(String name);
        public int getCheckingBalance();
        public void setCheckingBalance(int balance);
        public int getSavingsBalance();
        public void setSavingsBalance(int balance);

        public IState createDomainObject();
        public User getDomainObject();
    }

    private int id;
    private IState state;

    public UserEntity() {
        state = new Initializing();
    }

    public UserEntity(User domainObject) {
        state = new Sealed(domainObject);
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return state.getName();
    }

    public void setName(String name) {
        state.setName(name);
    }

    public int getCheckingBalance() {
        return state.getCheckingBalance();
    }

    public void setCheckingBalance(int balance) {
        state.setCheckingBalance(balance);
    }

    public int getSavingsBalance() {
        return state.getSavingsBalance();
    }

    public void setSavingsBalance(int balance) {
        state.setSavingsBalance(balance);
    }

    @Transient
    public User getDomainObject() {
        state = state.createDomainObject();
        return state.getDomainObject();
    }
}
