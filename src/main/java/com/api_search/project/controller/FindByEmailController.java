package com.api_search.project.controller;

import com.api_search.project.client.FindByEmailClient;
import com.api_search.project.response.FindByEmailResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
@RequestMapping("/api-search/v1")
public class FindByEmailController {
    FindByEmailClient findByEmailClient;

    @GetMapping("/breached/{email}")
    public Flux<FindByEmailResponse> getFindByEmail(@PathVariable String email){
        return findByEmailClient.findByEmailResponseFlux(email);
    }

}
