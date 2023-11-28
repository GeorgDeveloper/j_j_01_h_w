package org.example;


import java.util.Random;

public class RandomNumber {

    public static Integer getRandomNumber(int end){
        Random random = new Random();
        return random.nextInt(end);
    }
}
