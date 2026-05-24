package com.api_search.project.client;

import com.api_search.project.excepetion.FindByPasswordExcept;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;

@Component
public class FindByPasswordClient {
    private final WebClient webClient;
    private final String apiKey;

    public FindByPasswordClient(@Value("${hibp.base-url}") String baseUrl, @Value("${hibp.api-key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
        this.apiKey = apiKey;
    }

    public String checkPassword(String password) throws FindByPasswordExcept {
        try {
            String hash = sha1(password);
            String prefix = hash.substring(0, 5);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.pwnedpasswords.com/range/" + prefix))
                    .header("hibp-api-key", apiKey)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new FindByPasswordExcept(e.getMessage());
        }

    }

    public String sha1(String input) throws FindByPasswordExcept {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(input.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02X", b));
            }
            return sb.toString();
        }catch (Exception e)
        {
            throw new FindByPasswordExcept(e.getMessage());
        }
    }
}
