package com.apisql.ApiSQL.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name="Health", description = "Endpoint para confirmação de funcionamento")
public class HealthController {
    @GetMapping("/health")
    @Operation(summary = "Health", description = "Endpoint para confirmação de funcionamento")
    public String health() {
        return "OK";
    }
}
