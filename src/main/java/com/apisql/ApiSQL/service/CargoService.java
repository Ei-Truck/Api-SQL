package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.CargoRequestDTO;
import com.apisql.ApiSQL.dto.CargoResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.Cargo;
import com.apisql.ApiSQL.repository.CargoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;
    private final ObjectMapper objectMapper;

    public CargoService(CargoRepository cargoRepository, ObjectMapper objectMapper) {
        this.cargoRepository = cargoRepository;
        this.objectMapper = objectMapper;
    }

    public List<CargoResponseDTO> findAll() {
        List<Cargo> cargos = cargoRepository.findAll();
        return cargos.stream()
                .map(c -> objectMapper.convertValue(c, CargoResponseDTO.class))
                .toList();
    }

    public CargoResponseDTO findById(Integer id) {
        Optional<Cargo> response = cargoRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), CargoResponseDTO.class);
        }
        throw new ResourceNotFoundException("Cargo não encontrado com ID: " + id);
    }

    @Transactional
    public CargoResponseDTO save(CargoRequestDTO dto) {
        Cargo cargo = new Cargo();
        cargo.setNome(dto.getNome());

        Cargo savedCargo = cargoRepository.save(cargo);
        return objectMapper.convertValue(savedCargo, CargoResponseDTO.class);
    }

    @Transactional
    public CargoResponseDTO update(Integer id, CargoRequestDTO dto) {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo com id:" + id + " não encontrado para atualização"));

        cargo.setNome(dto.getNome());
        cargo.setUpdatedAt(LocalDateTime.now());

        Cargo updatedCargo = cargoRepository.save(cargo);
        return objectMapper.convertValue(updatedCargo, CargoResponseDTO.class);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!cargoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cargo com id:" + id + " não encontrado para exclusão");
        }
        cargoRepository.deleteById(id);
    }
}