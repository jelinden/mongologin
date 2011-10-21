package fi.mongologin.controller;

import java.util.ArrayList;
import java.util.List;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import fi.mongologin.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:applicationContext.xml" })
public class LoginControllerTest {
    private MongoOperations mongoOps;
    protected static final Logger log = LoggerFactory.getLogger(LoginControllerTest.class);
  
    @Before
    public void setUp() throws Exception {
        List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();    
        serverAddresses.add( new ServerAddress( "localhost", 27017 ) ); 

        MongoOptions options = new MongoOptions();
        options.autoConnectRetry = false; 

        Mongo mongo = new Mongo( serverAddresses, options );
        mongo.slaveOk();
        mongoOps = new MongoTemplate(mongo, "mongodb");
    }
  
    @Test
    public void testMongoDb() {
        User user = new User();
        user.setUserName("test");
        user.setEmail("test.user@test.com");
        user.setPassword("testpassword");
        mongoOps.insert(user, "usercollection");
        
        User newUser = mongoOps.findOne(new Query(where("id").is(user.getId())), User.class);
        Assert.notNull(newUser);
        Assert.isTrue("test".equals(newUser.getUserName()));
    }
    
    @After
    public void removeUser() {
        mongoOps.remove(new Query(where("email").is("test.user@test.com")), User.class);
    }
}