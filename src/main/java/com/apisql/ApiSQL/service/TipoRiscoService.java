package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.TipoRiscoRequestDTO;
import com.apisql.ApiSQL.dto.TipoRiscoResponseDTO;
import com.apisql.ApiSQL.model.TipoRisco;
import com.apisql.ApiSQL.repository.TipoRiscoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TipoRiscoService {

    private final TipoRiscoRepository tipoRiscoRepository;
    private final ObjectMapper objectMapper;

    public TipoRiscoService(TipoRiscoRepository tipoRiscoRepository, ObjectMapper objectMapper) {
        this.tipoRiscoRepository = tipoRiscoRepository;
        this.objectMapper = objectMapper;
    }

    public List<TipoRiscoResponseDTO> findAll() {
        List<TipoRisco> riscos = tipoRiscoRepository.findAll();
        return riscos.stream()
                .map(r -> objectMapper.convertValue(r, TipoRiscoResponseDTO.class))
                .toList();
    }

    public TipoRiscoResponseDTO findById(Integer id) {
        Optional<TipoRisco> response = tipoRiscoRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), TipoRiscoResponseDTO.class);
        }
        throw new EntityNotFoundException("TipoRisco não encontrado com ID: " + id);
    }

    @Transactional
    public TipoRiscoResponseDTO save(TipoRiscoRequestDTO dto) {
        TipoRisco tipoRisco = new TipoRisco();
        tipoRisco.setNome(dto.getNome());
        tipoRisco.setDescricao(dto.getDescricao());

        TipoRisco savedTipoRisco = tipoRiscoRepository.save(tipoRisco);
        return objectMapper.convertValue(savedTipoRisco, TipoRiscoResponseDTO.class);
    }

    @Transactional
    public TipoRiscoResponseDTO update(Integer id, TipoRiscoRequestDTO dto) {
        TipoRisco tipoRisco = tipoRiscoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoRisco com id:" + id + " não encontrado"));

        tipoRisco.setNome(dto.getNome());
        tipoRisco.setDescricao(dto.getDescricao());
        tipoRisco.setUpdatedAt(LocalDateTime.now());

        TipoRisco updatedTipoRisco = tipoRiscoRepository.save(tipoRisco);
        return objectMapper.convertValue(updatedTipoRisco, TipoRiscoResponseDTO.class);
    }

    public void deleteById(Integer id) {
        if (!tipoRiscoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoRisco com id:" + id + " não encontrado para exclusão");
        }
        tipoRiscoRepository.deleteById(id);
    }
}