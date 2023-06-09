package com.ms.bpp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class Sender {

    private final WebClient webClient;

    @Autowired
    RestTemplate restTemplate;

    public Sender(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .build();
    }

    /*public void send(String url, HttpHeaders headers, String json) {

        log.info("Calling Consumer BAP {}", url);
        try {
            Mono<String> response = this.webClient.post()
                    .uri(url)
                    .headers(h -> {
                        h.addAll(headers);
                    })
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(json), String.class)
                    .retrieve()
                    .bodyToMono(String.class);
            String responseData = response.block();
            log.info("response from post call: {}", responseData);

        } catch (Exception e) {
            log.error("Exception while calling the post at url {}", url);
            log.error("Exception is {}", e.getMessage());
        }

    }*/

    public void send(String url, HttpHeaders headers, String json) {

        log.info("Calling Consumer BAP {}", url);
        try {
          ResponseEntity<String> response= restTemplate.postForEntity(url,json, String.class);
            log.info("response from post call: {}", response.getStatusCode());
        } catch (Exception e) {
            log.error("Exception while calling the post at url {}", url);
            log.error("Exception is {}", e.getMessage());
        }

    }

}
