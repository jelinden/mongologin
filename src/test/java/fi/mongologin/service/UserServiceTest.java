package fi.mongologin.service;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import fi.mongologin.domain.User;
import fi.mongologin.repository.UserRepository;

public class UserServiceTest {

    private UserServiceImpl userService;
    private UserRepository userRepository;
    
    private User user = new User();    
    protected static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl();
        userRepository = createStrictMock(UserRepository.class);
        userService.setUserRepository(userRepository);
    }
   
    @Test
    public void testUserServiceFind() {
        user.setUserName("test");
        user.setEmail("testing@test.com");
        user.setPassword("testpassword");

        expect(userRepository.findUser("test")).andReturn(user);
        replay(userRepository);
        Assert.assertNotNull(userService.findUser("test"));
        verify(userRepository);
    }
}