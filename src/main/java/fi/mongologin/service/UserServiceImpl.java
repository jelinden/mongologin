package fi.mongologin.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.mongologin.domain.User;
import fi.mongologin.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
    
    @Resource(name="userRepository")
    private UserRepository userRepository;
    
    public void insert(User user) {
        userRepository.insert(user);
    }
    
    public void update(User user) {
        userRepository.update(user);
    }
    
    public User findUser(String userName) {
        return userRepository.findUser(userName);
    }
    
    public void delete(User user) {
        userRepository.delete(user);
    }
    
    public boolean checkPassword(String userName, String passwordHash) {
        User user = userRepository.findUser(userName);
        return user.getPassword().equals(passwordHash);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}