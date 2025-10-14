package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.StatusRequestDTO;
import com.apisql.ApiSQL.dto.StatusResponseDTO;
import com.apisql.ApiSQL.openapi.StatusOpenApi;
import com.apisql.ApiSQL.service.StatusService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController implements StatusOpenApi {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<StatusResponseDTO>> findAll() {
        List<StatusResponseDTO> statusList = statusService.findAll();
        return ResponseEntity.ok(statusList);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<StatusResponseDTO> findById(@PathVariable Integer id) {
        StatusResponseDTO status = statusService.findById(id);
        return ResponseEntity.ok(status);
    }

    @Override
    @PostMapping
    public ResponseEntity<StatusResponseDTO> save(@Valid @RequestBody StatusRequestDTO dto) {
        StatusResponseDTO savedStatus = statusService.save(dto);
        return new ResponseEntity<>(savedStatus, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<StatusResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody StatusRequestDTO dto) {
        StatusResponseDTO updatedStatus = statusService.update(id, dto);
        return ResponseEntity.ok(updatedStatus);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        statusService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}