package com.apisql.ApiSQL.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KeepAliveTask {

    private static final String URL = "https://api-sql-qa.onrender.com/ping";

    @Scheduled(fixedRate = 600000) // a cada 10 minutos
    public void pingMyself() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(URL, String.class);
            System.out.println("KeepAlive: " + response);
        } catch (Exception e) {
            System.err.println("Erro no KeepAlive: " + e.getMessage());
        }
    }
}
