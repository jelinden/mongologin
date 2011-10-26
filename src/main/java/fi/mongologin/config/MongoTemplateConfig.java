package fi.mongologin.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

import fi.mongologin.repository.UserRepository;
import fi.mongologin.repository.UserRepositoryImpl;

@Configuration
public class MongoTemplateConfig {

  /*
   * Use the standard Mongo driver API to create a com.mongodb.Mongo instance that supports replica sets
   */
  @Bean
  public Mongo mongo() throws Exception {
    
    List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();    
    serverAddresses.add( new ServerAddress( "localhost", 27017 ) );
    
    MongoOptions options = new MongoOptions();
    options.autoConnectRetry = true; 
    
    Mongo mongo  = new Mongo( serverAddresses, options );
    mongo.slaveOk();
    
    return mongo;
  }
  
  @Bean
  public MongoTemplate mongoTemplate() throws Exception {
    return new MongoTemplate(mongo(), "mongodb");
  }
  
  @Bean
  public UserRepository getUserRepository() throws Exception {
    return new UserRepositoryImpl();
  }
}  