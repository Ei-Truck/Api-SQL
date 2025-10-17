package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.TipoRiscoRequestDTO;
import com.apisql.ApiSQL.dto.TipoRiscoResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.TipoRisco;
import com.apisql.ApiSQL.repository.TipoRiscoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<TipoRisco> tipos = tipoRiscoRepository.findAll();
        return tipos.stream()
                .map(t -> objectMapper.convertValue(t, TipoRiscoResponseDTO.class))
                .toList();
    }

    public TipoRiscoResponseDTO findById(Integer id) {
        Optional<TipoRisco> response = tipoRiscoRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), TipoRiscoResponseDTO.class);
        }
        throw new ResourceNotFoundException("TipoRisco não encontrado com ID: " + id);
    }

    @Transactional
    public TipoRiscoResponseDTO save(TipoRiscoRequestDTO dto) {
        TipoRisco tipo = objectMapper.convertValue(dto, TipoRisco.class);
        TipoRisco savedTipo = tipoRiscoRepository.save(tipo);
        return objectMapper.convertValue(savedTipo, TipoRiscoResponseDTO.class);
    }

    @Transactional
    public TipoRiscoResponseDTO update(Integer id, TipoRiscoRequestDTO dto) {
        TipoRisco tipo = tipoRiscoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoRisco com id:" + id + " não encontrado para atualização"));

        tipo.setNome(dto.getNome());
        tipo.setDescricao(dto.getDescricao());
        tipo.setUpdatedAt(LocalDateTime.now());

        TipoRisco updatedTipo = tipoRiscoRepository.save(tipo);
        return objectMapper.convertValue(updatedTipo, TipoRiscoResponseDTO.class);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!tipoRiscoRepository.existsById(id)) {
            throw new ResourceNotFoundException("TipoRisco com id:" + id + " não encontrado para exclusão");
        }
        tipoRiscoRepository.deleteById(id);
    }
}