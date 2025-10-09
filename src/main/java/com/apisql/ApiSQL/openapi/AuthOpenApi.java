package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.LoginUsuarioRequestDTO;
import com.apisql.ApiSQL.dto.LoginUsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping; // ADICIONADO
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
public interface AuthOpenApi {
    @Operation(summary = "Realiza login do usuário e gera JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping
    ResponseEntity<LoginUsuarioResponseDTO> login(@RequestBody LoginUsuarioRequestDTO request);
}