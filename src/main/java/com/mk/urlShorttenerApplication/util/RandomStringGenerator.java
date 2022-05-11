package com.mk.urlShorttenerApplication.util;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class RandomStringGenerator {
    @Value("${codeLength}")
    private int codeLength;

    public String generateRandomString() {
        SecureRandom secureRandom = new SecureRandom();

        String generated = " ";


        var letters = "abcdefghijklmnprstuvyzqw123456789"
                .toLowerCase()
                .chars()
                .mapToObj(x -> (char) x)
                .collect(Collectors.toList());

        Collections.shuffle(letters);//karakterleri karma karışık hale getirir

        for (int i = 0; i < codeLength; i++) {
            generated += letters.get(secureRandom.nextInt(letters.size()));

        }
        return generated;
    }
}
