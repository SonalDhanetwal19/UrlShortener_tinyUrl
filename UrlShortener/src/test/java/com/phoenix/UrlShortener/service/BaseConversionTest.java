package com.phoenix.UrlShortener.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class BaseConversionTest {

    private BaseConversion baseConversion = new BaseConversion();

        @Test
        public void encodeTest()
        {
           Assertions.assertEquals("k", baseConversion.encode(10));
            Assertions.assertEquals("qb", baseConversion.encode(78));
        }

        @Test
        public void decodeTest()
        {
            Assertions.assertEquals(11, baseConversion.decode("l"));
        }

}