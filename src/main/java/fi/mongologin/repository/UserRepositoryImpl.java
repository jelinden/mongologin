package fi.mongologin.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import fi.mongologin.domain.User;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {
   
    @Autowired
    private MongoTemplate mongoTemplate;
    protected static final Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);
    
    public void insert(User user) {
        mongoTemplate.insert(user, "usercollection");
    }

    public void update(User user) {
        mongoTemplate.save(user, "usercollection");
    }

    public User findUser(String userName) {
        return mongoTemplate.findOne(new Query(Criteria.where("userName").is(userName)), User.class, "usercollection");
    }

    public void delete(User user) {
        mongoTemplate.remove(user);
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}