package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.LoginUsuarioRequestDTO;
import com.apisql.ApiSQL.dto.LoginUsuarioResponseDTO;
import com.apisql.ApiSQL.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login") // Mapeamento base
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Realiza login do usuário e gera JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping
    public ResponseEntity<LoginUsuarioResponseDTO> login(@RequestBody LoginUsuarioRequestDTO request) {
        LoginUsuarioResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}