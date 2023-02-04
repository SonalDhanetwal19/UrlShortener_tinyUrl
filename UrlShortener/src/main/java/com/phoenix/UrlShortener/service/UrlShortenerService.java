package com.phoenix.UrlShortener.service;

import com.phoenix.UrlShortener.dto.UrlLongRequest;
import com.phoenix.UrlShortener.entity.Url;
import com.phoenix.UrlShortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class UrlShortenerService {

    @Autowired
    UrlRepository urlRepository;
    @Autowired
    BaseConversion baseConversion;
    public String convertToShortUrl(UrlLongRequest request) {
        System.out.println("convertToShortUrl service called");
        var url = new Url();
        url.setLongUrl(request.getLongUrl());
        url.setExpiryDate(request.getExpiryDate());
        url.setCreatedDate(new Date());
        System.out.println("urlshortener service - before save");
        var entity = urlRepository.save(url);
        var encodedUrl = baseConversion.encode(entity.getId());
        System.out.println("encode - url : "+ encodedUrl);
        url.setShortUrl(encodedUrl);
        urlRepository.save(url);
        return encodedUrl;
    }

    public String getOriginalUrl(String shortUrl)
    {
        System.out.println("getOriginalUrl service");
       var id = baseConversion.decode(shortUrl);
        System.out.println(" id for short url : "+ id);
       var entity = urlRepository.findById(id)
                    .orElseThrow(()->new EntityNotFoundException("Entity not found with : "+shortUrl));
        if(entity.getExpiryDate()!= null && entity.getExpiryDate().before(new Date())){
            urlRepository.delete(entity);
            throw new EntityNotFoundException("Link Expired");
        }
        return entity.getLongUrl();
    }
}
