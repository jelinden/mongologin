package fi.mongologin.repository;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fi.mongologin.config.AnnotationConfigContextLoader;
import fi.mongologin.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, value = "fi.mongologin.config.MongoTemplateConfig")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    
    private User user = new User();    
    protected static final Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Test
    public void testUserRepositoryInsert() {
        user.setUserName("test");
        user.setEmail("testing@test.com");
        user.setPassword("testpassword");
        userRepository.insert(user);
        Assert.assertNotNull(userRepository.findUser("test"));
    }
    
    @Test
    public void testUserRepositoryUpdate() {
        user.setUserName("test2");
        userRepository.update(user);
        User fetchedUser = userRepository.findUser("test2");
        Assert.assertEquals("test2", fetchedUser.getUserName());
    }
    
    @Test
    public void testUserRepositoryFindUser() {
        User fetchedUser = userRepository.findUser("test2");
        Assert.assertEquals("test2", fetchedUser.getUserName());
    }
    
    @Test
    public void testUserRepositoryDelete() {
        User fetchedUser = userRepository.findUser("test2");
        userRepository.delete(fetchedUser);
        fetchedUser = userRepository.findUser("test2");
        Assert.assertNull(fetchedUser);
    }
}
    
    