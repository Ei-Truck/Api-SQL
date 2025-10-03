package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.LoginUsuarioResponseDTO;
import com.apisql.ApiSQL.openapi.LoginUsuarioOpenApi;
import com.apisql.ApiSQL.service.LoginUsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
public class LoginUsuarioController implements LoginUsuarioOpenApi {

    private final LoginUsuarioService service;

    public LoginUsuarioController(LoginUsuarioService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<LoginUsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }


    @Override
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<LoginUsuarioResponseDTO>> listarPorUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(service.listarPorUsuario(idUsuario));
    }
}
