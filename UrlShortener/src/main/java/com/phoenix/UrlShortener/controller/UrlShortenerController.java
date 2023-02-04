package com.phoenix.UrlShortener.controller;

import com.phoenix.UrlShortener.dto.UrlLongRequest;
import com.phoenix.UrlShortener.service.UrlShortenerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class UrlShortenerController {

    @Autowired
    UrlShortenerService urlShortenerService;

    @ApiOperation(value = "creates short url", notes = "convert new url into shorturl")
    @PostMapping("create-short-url")
    public String longToShortUrl(@RequestBody UrlLongRequest request)
    {

        System.out.println("controller called");
        return urlShortenerService.convertToShortUrl(request);
    }

    @ApiOperation(value = "redirect", notes = "Finds original url from short url using id and redirect to the required page")
    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    @GetMapping(value = "{shortUrl}")
    public ResponseEntity<Void> getShortUrlAndRedirect(@PathVariable String shortUrl)
    {
        System.out.println("getShortUrlAndRedirect controller called");
        var url = urlShortenerService.getOriginalUrl(shortUrl);
        System.out.println("long url: "+url);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
