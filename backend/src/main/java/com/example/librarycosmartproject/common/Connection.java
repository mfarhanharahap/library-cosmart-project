package com.example.librarycosmartproject.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@Slf4j
@Component
@RequiredArgsConstructor
public class Connection {

    private final RestTemplate restTemplate;

    public ResponseEntity<String> httpPost(String url, Object request) {
        RequestEntity<Object> requestEntity = null;
        try {
            requestEntity = RequestEntity.post(new URL(url).toURI()).contentType(MediaType.APPLICATION_JSON).body(request);
            return restTemplate.exchange(requestEntity, String.class);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return null;
    }

    public ResponseEntity<String> httpGet(String url) {
        try {
            return restTemplate.getForEntity(url, String.class);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return null;
    }
}
