package com.fred.schoolmanagement.utils;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Configuration
public class TokenHasher {

    //hash token
    public static String hashToken(String token){
        return BCrypt.hashpw(token, BCrypt.gensalt());
    }

    //validate a token
    public static boolean validateResetToken(String hashedToken, String resetCode){
        return BCrypt.checkpw(resetCode, hashedToken);
    }

}
