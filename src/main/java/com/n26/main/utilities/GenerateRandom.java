package com.n26.main.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateRandom {
    
    public static String randomString(int length){
        return RandomStringUtils.randomAlphabetic(length).toLowerCase();
    }

    public static int randomNumber(int count){
        return Integer.parseInt(RandomStringUtils.randomNumeric(count));
    }
    
}
