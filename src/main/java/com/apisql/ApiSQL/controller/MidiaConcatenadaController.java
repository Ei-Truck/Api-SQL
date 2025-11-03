package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.MidiaConcatenadaRequestDTO;
import com.apisql.ApiSQL.dto.MidiaConcatenadaResponseDTO;
import com.apisql.ApiSQL.openapi.MidiaConcatenadaOpenApi;
import com.apisql.ApiSQL.service.MidiaConcatenadaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/midias-concatenadas")
public class MidiaConcatenadaController implements MidiaConcatenadaOpenApi {

    private final MidiaConcatenadaService service;
    public MidiaConcatenadaController(MidiaConcatenadaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<MidiaConcatenadaResponseDTO>> findAll() {
        List<MidiaConcatenadaResponseDTO> midias = service.findAll();
        return ResponseEntity.ok(midias);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MidiaConcatenadaResponseDTO> findById(@PathVariable Integer id) {
        MidiaConcatenadaResponseDTO midia = service.findById(id);
        return ResponseEntity.ok(midia);
    }

    @Override
    @PostMapping
    public ResponseEntity<MidiaConcatenadaResponseDTO> save(@Valid @RequestBody MidiaConcatenadaRequestDTO dto) {
        MidiaConcatenadaResponseDTO savedMidia = service.save(dto);
        return new ResponseEntity<>(savedMidia, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<MidiaConcatenadaResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody MidiaConcatenadaRequestDTO dto) {
        MidiaConcatenadaResponseDTO updatedMidia = service.update(id, dto);
        return ResponseEntity.ok(updatedMidia);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}