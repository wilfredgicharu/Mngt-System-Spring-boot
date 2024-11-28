package com.fred.schoolmanagement.utils;


import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class NumberGenerator {

    // generating a random token
    public String generateRandomNumber(){
        Random random = new Random();
        int token = 10000 + random.nextInt(900000);
        return String.valueOf(token);
    }

    //generating sequential 4 digit number with prefix b5

    public String generateSequentialNumber(int currentSequentialNumber){
        return String.format("BS%04d", currentSequentialNumber);
    }


}
