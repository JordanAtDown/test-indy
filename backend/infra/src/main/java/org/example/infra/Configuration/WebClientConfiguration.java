package org.example.infra.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration {

    private final APIConfiguration api;

    @Bean
    public WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(api.getUrl())
                .build();
    }
}
