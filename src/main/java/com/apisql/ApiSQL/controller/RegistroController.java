package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.RegistroRequestDTO;
import com.apisql.ApiSQL.dto.RegistroResponseDTO;
import com.apisql.ApiSQL.dto.RegistroTratativaPatchDTO;
import com.apisql.ApiSQL.openapi.RegistroOpenApi;
import com.apisql.ApiSQL.service.RegistroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros")
public class RegistroController implements RegistroOpenApi {

    private final RegistroService registroService;

    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<RegistroResponseDTO>> findAll() {
        List<RegistroResponseDTO> registros = registroService.findAll();
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RegistroResponseDTO> findById(@PathVariable Integer id) {
        RegistroResponseDTO registro = registroService.findById(id);
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping
    public ResponseEntity<RegistroResponseDTO> save(@Valid @RequestBody RegistroRequestDTO dto) {
        RegistroResponseDTO savedRegistro = registroService.save(dto);
        return new ResponseEntity<>(savedRegistro, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<RegistroResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody RegistroRequestDTO registroAtualizado) {
        RegistroResponseDTO updatedRegistro = registroService.update(id, registroAtualizado);
        return ResponseEntity.ok(updatedRegistro);
    }

    @Override
    @PatchMapping("/viagem/{idViagem}/motorista/{idMotorista}")
    public ResponseEntity<RegistroResponseDTO> patchTratativa(@PathVariable Integer idViagem, @PathVariable Integer idMotorista, @Valid @RequestBody RegistroTratativaPatchDTO dto) {
        RegistroResponseDTO updatedRegistro = registroService.patchTratativa(idViagem, idMotorista, dto);
        return ResponseEntity.ok(updatedRegistro);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        registroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}