package com.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.services.UrlService;

public class UrlServiceTest {
    private UrlService urlService;

    @BeforeEach
    public void setup() {
        urlService = new UrlService();
    }

    @Test
    public void testShortenUrlAnonymous() {
        String shortUrl = urlService.shortenUrl("http://example.com", null, null);
        assertNotNull(shortUrl);
        assertTrue(shortUrl.contains("http://localhost:8080/"));
    }
}