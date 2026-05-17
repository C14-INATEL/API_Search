package com.api_search.project.client;

import com.api_search.project.response.FindByEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j // annotation that will automatically log the data
public class FindByEmailClient {
    private final WebClient webClient;
    private final String apiKey;

    public FindByEmailClient(@Value("${hibp.base-url}") String baseUrl, @Value("${hibp.api-key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
        this.apiKey = apiKey;
    }

    public Flux<FindByEmailResponse> findByEmailResponseFlux(String email){
        log.info("Searching email -> [{}] in hibp ...", email);
        return  webClient
                .get()
                .uri("/breachedAccount/{email}?truncateResponse=false", email)
                .header("hibp-api-key", apiKey)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new RuntimeException("Erro 5xx: servidor indisponível"))
                )
                .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(new RuntimeException("Verify the parameters ")))
                .bodyToFlux(FindByEmailResponse.class);

    }

}
