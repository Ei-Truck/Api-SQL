package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.LoginUsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/login-usuario")
public interface LoginUsuarioOpenApi {

    @Operation(summary = "Lista todos os registros de login")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping // ADICIONADO
    ResponseEntity<List<LoginUsuarioResponseDTO>> listar();

    @Operation(summary = "Lista todos os logins de um usuário específico")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/usuario/{idUsuario}")
    ResponseEntity<List<LoginUsuarioResponseDTO>> listarPorUsuario(@PathVariable Integer idUsuario);
}
