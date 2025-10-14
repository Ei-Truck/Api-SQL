package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.CargoRequestDTO;
import com.apisql.ApiSQL.dto.CargoResponseDTO;
import com.apisql.ApiSQL.openapi.CargoOpenApi;
import com.apisql.ApiSQL.service.CargoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargos")
public class CargoController implements CargoOpenApi {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CargoResponseDTO>> findAll() {
        List<CargoResponseDTO> cargos = cargoService.findAll();
        return ResponseEntity.ok(cargos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CargoResponseDTO> findById(@PathVariable Integer id) {
        CargoResponseDTO cargo = cargoService.findById(id);
        return ResponseEntity.ok(cargo);
    }

    @Override
    @PostMapping
    public ResponseEntity<CargoResponseDTO> save(@Valid @RequestBody CargoRequestDTO dto) {
        CargoResponseDTO savedCargo = cargoService.save(dto);
        return new ResponseEntity<>(savedCargo, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CargoResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody CargoRequestDTO dto) {
        CargoResponseDTO updatedCargo = cargoService.update(id, dto);
        return ResponseEntity.ok(updatedCargo);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        cargoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}