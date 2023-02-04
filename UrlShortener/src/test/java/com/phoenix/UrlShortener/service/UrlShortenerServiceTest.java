package com.phoenix.UrlShortener.service;

import com.phoenix.UrlShortener.dto.UrlLongRequest;
import com.phoenix.UrlShortener.entity.Url;
import com.phoenix.UrlShortener.repository.UrlRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import java.net.URL;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlShortenerServiceTest {

    @Mock
    UrlRepository mockUrlRepository;

    @Mock
    BaseConversion mockBaseConversion;

    @InjectMocks
    UrlShortenerService mockUrlShortenerService;

    @Test
    public void convertToShortUrlTest()
    {
        var url = new Url();
        url.setLongUrl("https://github.com/AnteMarin/UrlShortener-API");
        url.setCreatedDate(new Date());
        url.setId(7);

        when(mockUrlRepository.save(Mockito.any(Url.class))).thenReturn(url);
        when(mockBaseConversion.encode(url.getId())).thenReturn("f");

        var urlRequest = new UrlLongRequest();
        urlRequest.setLongUrl("https://github.com/AnteMarin/UrlShortener-API");
        Assertions.assertEquals("f", mockUrlShortenerService.convertToShortUrl(urlRequest));

    }

    @Test
    public void getOriginalUrlTest() {

        when(mockBaseConversion.decode("h")).thenReturn((long) 7);
        var url = new Url();
        url.setLongUrl("https://github.com/AnteMarin/UrlShortener-API");
        url.setId(7);
        url.setCreatedDate(new Date());

        when(mockUrlRepository.findById((long)7)).thenReturn(java.util.Optional.of(url));
        Assertions.assertEquals("https://github.com/AnteMarin/UrlShortener-API",mockUrlShortenerService.getOriginalUrl("h"));

    }


}