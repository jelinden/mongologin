package fi.mongologin.service;

import fi.mongologin.domain.User;

public interface UserService {
    public void insert(User user);
    public void update(User user);
    public User findUser(String userName);
    public void delete(User user);
    public boolean checkPassword(String userName, String passwordHash);
}