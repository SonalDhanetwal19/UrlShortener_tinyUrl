package com.phoenix.UrlShortener.service;

import org.springframework.stereotype.Service;

@Service
public class BaseConversion {

    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] allowedChars = allowedString.toCharArray();
    private int base = allowedChars.length;
    public String encode(long id)
    {
        var encodedString = new StringBuilder();
        if(id == 0)
        {
            System.out.println(" allowedChars[0] "+allowedChars[0]);
            return String.valueOf(allowedChars[0]);
        }
        while(id > 0) {
                System.out.println(" id % base "+id % base);
               encodedString.append(allowedChars[(int) id % base]);
            System.out.println(" id - before: "+id);
               id = id / base;
            System.out.println(" id - after: "+id);
        }
        System.out.println("encodedString.reverse().toString() : "+encodedString.reverse().toString());
        return encodedString.reverse().toString();
    }

    public long decode(String shortUrl)
    {
        var shortUrlCharacters = shortUrl.toCharArray();
        int urlLength = shortUrlCharacters.length;

        var decoded = 0;

        var counter = 1;
        for(int i = 0; i<urlLength; i++)
        {
            decoded+= allowedString.indexOf(shortUrlCharacters[i]) * Math.pow(base, urlLength-counter);
            counter++;

        }
        System.out.println("decoded value = "+decoded);
        return decoded;
    }
}
