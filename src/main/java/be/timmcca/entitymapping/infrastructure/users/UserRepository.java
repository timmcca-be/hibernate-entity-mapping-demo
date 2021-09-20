package be.timmcca.entitymapping.infrastructure.users;

import java.util.stream.Stream;

import org.hibernate.Session;

import be.timmcca.entitymapping.domain.IUserRepository;
import be.timmcca.entitymapping.domain.User;

public class UserRepository implements IUserRepository {
    private final Session session;

    public UserRepository(Session session) {
        this.session = session;
    }

    @Override
    public void add(User user) {
        session.persist(new UserEntity(user));
    }

    @Override
    public User get(int id) {
        var entity = session.find(UserEntity.class, id);
        return entity.getDomainObject();
    }

    @Override
    public Stream<User> getAll() {
        var query = session.getCriteriaBuilder().createQuery(UserEntity.class);
        query.from(UserEntity.class);
        var entities = session.createQuery(query).getResultStream();
        return entities.map(UserEntity::getDomainObject);
    }
}
