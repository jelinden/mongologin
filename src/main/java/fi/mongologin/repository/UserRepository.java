package fi.mongologin.repository;

import fi.mongologin.domain.User;

public interface UserRepository {
    public void insert(User user);
    public void update(User user);
    public User findUser(String userName);
    public void delete(User user);
}