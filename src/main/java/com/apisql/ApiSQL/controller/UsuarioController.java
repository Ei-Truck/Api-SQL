package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.UsuarioResponseDTO;
import com.apisql.ApiSQL.model.Usuario;
import com.apisql.ApiSQL.openapi.UsuarioOpenApi;
import com.apisql.ApiSQL.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements UsuarioOpenApi {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAll() {
        List<UsuarioResponseDTO> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Integer id) {
        UsuarioResponseDTO usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @Override
    @GetMapping("/telefone/{telefone}")
    public ResponseEntity<UsuarioResponseDTO> findByTelefone(@PathVariable String telefone) {
        UsuarioResponseDTO usuario = usuarioService.findByTelefone(telefone);
        return ResponseEntity.ok(usuario);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping("/{id}/foto")
    public ResponseEntity<Usuario> atualizarFoto(
            @PathVariable Integer id,
            @RequestParam("file") MultipartFile file) throws IOException {

        Path tempFile = Files.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile);

        Usuario usuarioAtualizado = usuarioService.atualizarFoto(id, tempFile);

        Files.delete(tempFile);

        return ResponseEntity.ok(usuarioAtualizado);
    }

}

