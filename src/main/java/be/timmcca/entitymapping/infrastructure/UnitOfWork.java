package be.timmcca.entitymapping.infrastructure;

import org.hibernate.Session;
import org.hibernate.Transaction;

import be.timmcca.entitymapping.domain.IUserRepository;
import be.timmcca.entitymapping.domain.common.IUnitOfWork;
import be.timmcca.entitymapping.infrastructure.users.UserRepository;

public class UnitOfWork implements IUnitOfWork {
    private final Session session;
    private final Transaction transaction;
    private IUserRepository userRepository = null;

    public UnitOfWork(Session session) {
        this.session = session;
        transaction = session.beginTransaction();
    }

    @Override
    public IUserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepository(session);
        }
        return userRepository;
    }

    @Override
    public void commit() {
        transaction.commit();
    }
    
}
