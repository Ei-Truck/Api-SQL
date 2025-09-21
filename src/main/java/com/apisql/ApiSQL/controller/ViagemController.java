package com.apisql.ApiSQL.controller;


import com.apisql.ApiSQL.model.Viagem;
import com.apisql.ApiSQL.service.ViagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viagens")
@Tag(name = "Viagens", description = "API para gerenciamento de viagens")
public class ViagemController {

    private final ViagemService viagemService;

    public ViagemController(ViagemService viagemService) {
        this.viagemService = viagemService;
    }

    @GetMapping
    @Operation(summary = "Listar todas as viagens")
    public ResponseEntity<List<Viagem>> listarTodas() {
        return ResponseEntity.ok(viagemService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar uma viagem por ID")
    public ResponseEntity<Viagem> buscarPorId(@PathVariable Integer id) {
        return viagemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar uma nova viagem")
    public ResponseEntity<Viagem> salvar(@RequestBody Viagem viagem) {
        return ResponseEntity.ok(viagemService.salvar(viagem));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma viagem existente")
    public ResponseEntity<Viagem> atualizar(@PathVariable Integer id, @RequestBody Viagem viagem) {
        return ResponseEntity.ok(viagemService.atualizar(id, viagem));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma viagem")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        viagemService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
