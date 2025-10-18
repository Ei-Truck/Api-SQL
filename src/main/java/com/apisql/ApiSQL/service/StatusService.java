package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.StatusRequestDTO;
import com.apisql.ApiSQL.dto.StatusResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.Status;
import com.apisql.ApiSQL.repository.StatusRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    private final StatusRepository statusRepository;
    private final ObjectMapper objectMapper;

    public StatusService(StatusRepository statusRepository, ObjectMapper objectMapper) {
        this.statusRepository = statusRepository;
        this.objectMapper = objectMapper;
    }

    public List<StatusResponseDTO> findAll() {
        List<Status> statusList = statusRepository.findAll();
        return statusList.stream()
                .map(s -> objectMapper.convertValue(s, StatusResponseDTO.class))
                .toList();
    }

    public StatusResponseDTO findById(Integer id) {
        Optional<Status> response = statusRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), StatusResponseDTO.class);
        }
        throw new ResourceNotFoundException("Status não encontrado com ID: " + id);
    }

    @Transactional
    public StatusResponseDTO save(StatusRequestDTO dto) {
        Status status = new Status();
        status.setNome(dto.getNome());
        if (dto.getIsInactive() != null) {
            status.setIsInactive(dto.getIsInactive());
        }
        Status savedStatus = statusRepository.save(status);
        return objectMapper.convertValue(savedStatus, StatusResponseDTO.class);
    }

    @Transactional
    public StatusResponseDTO update(Integer id, StatusRequestDTO dto) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status com id:" + id + " não encontrado para atualização"));

        status.setNome(dto.getNome());
        if (dto.getIsInactive() != null) {
            status.setIsInactive(dto.getIsInactive());
        }

        Status updatedStatus = statusRepository.save(status);
        return objectMapper.convertValue(updatedStatus, StatusResponseDTO.class);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!statusRepository.existsById(id)) {
            throw new ResourceNotFoundException("Status com id:" + id + " não encontrado para exclusão");
        }
        statusRepository.deleteById(id);
    }
}