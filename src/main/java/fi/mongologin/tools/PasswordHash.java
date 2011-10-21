package fi.mongologin.tools;

import java.math.BigInteger;
import java.security.MessageDigest;

public class PasswordHash {
    
    public static String getPasswordHash(String password) throws java.security.NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(password.getBytes(), 0 ,password.length());
        return new BigInteger(1, m.digest()).toString(16);
    }
}