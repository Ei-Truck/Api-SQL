package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.LoginUsuarioRequestDTO;
import com.apisql.ApiSQL.dto.LoginUsuarioResponseDTO;
import com.apisql.ApiSQL.service.LoginUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logins")
public class LoginUsuarioController {

    private final LoginUsuarioService service;

    public LoginUsuarioController(LoginUsuarioService service) {
        this.service = service;
    }

    @Operation(summary = "Lista todos os registros de login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<LoginUsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Lista todos os logins de um usuário específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<LoginUsuarioResponseDTO>> listarPorUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(service.listarPorUsuario(idUsuario));
    }

    @Operation(summary = "Registra um novo login de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Login registrado com sucesso")
    })
    @PostMapping
    public ResponseEntity<LoginUsuarioResponseDTO> salvar(@RequestBody LoginUsuarioRequestDTO dto) {
        return ResponseEntity.status(201).body(service.salvar(dto));
    }

    @Operation(summary = "Remove um registro de login pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro removido com sucesso")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
