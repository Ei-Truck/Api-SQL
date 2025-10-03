package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.LoginUsuarioRequestDTO;
import com.apisql.ApiSQL.dto.LoginUsuarioResponseDTO;
import com.apisql.ApiSQL.openapi.AuthOpenApi;
import com.apisql.ApiSQL.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/login")
public class AuthController implements AuthOpenApi {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    @PostMapping
    public ResponseEntity<LoginUsuarioResponseDTO> login(@RequestBody LoginUsuarioRequestDTO request) {
        LoginUsuarioResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}