package com.jimbae.header;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@RestController
public class CacheController {

    @GetMapping(value = "/cache.html")
    private ResponseEntity<String> noCache() {
        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(100L, TimeUnit.MINUTES))
            .body("test");
    }

    @GetMapping(value = "/etag")
    private ResponseEntity<String> etag() {
        return ResponseEntity.ok()
            .eTag("1234")
            .body("test");
    }

    @GetMapping(value = "/expires")
    private ResponseEntity<String> expires() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setDate(ZonedDateTime.now());
        httpHeaders.setExpires(ZonedDateTime.now().plusSeconds(50));
        return ResponseEntity.ok()
            .headers(httpHeaders)
            .body("test");
    }

    public String getDateHeader(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss.SSSSSS Z");
        return ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}
