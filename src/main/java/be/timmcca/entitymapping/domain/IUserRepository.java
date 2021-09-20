package be.timmcca.entitymapping.domain;

import java.util.stream.Stream;

public interface IUserRepository {
    public void add(User user);
    public User get(int id);
    public Stream<User> getAll();
}
