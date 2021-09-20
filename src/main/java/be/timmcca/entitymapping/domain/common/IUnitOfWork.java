package be.timmcca.entitymapping.domain.common;

import be.timmcca.entitymapping.domain.IUserRepository;

public interface IUnitOfWork {
    public IUserRepository getUserRepository();
    public void commit();
}
