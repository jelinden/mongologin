package fi.mongologin.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.bson.types.ObjectId;
import fi.mongologin.tools.PasswordHash;

@Document(collection="usercollection")
public class User {
  
  protected static final Logger log = LoggerFactory.getLogger(User.class);
    
  @Id
  private ObjectId id;
  private String userName;
  private String email;
  private String password;
  
  public ObjectId getId() {
      return this.id;
  }
  
  public void setId(ObjectId id) {
      this.id = id;
  }
  
  public String getUserName() {
      return this.userName;
  }
  
  public void setUserName(String userName) {
      this.userName = userName;
  }
  
  public String getEmail() {
      return this.email;
  }
  
  public void setEmail(String email) {
      this.email = email;
  }
  
  public String getPassword() {
      return this.password;
  }
  
  public void setPassword(String password) {
      try {
          this.password = PasswordHash.getPasswordHash(password);
      }
      catch(java.security.NoSuchAlgorithmException e) {
          log.error("Password hashing failed! " + e);
      }
  }
  
  public String toString() {
      return "User [id= " + id + ", userName= " + userName + ", email= " + email + ", password= " + password + "]";
  }
  
}