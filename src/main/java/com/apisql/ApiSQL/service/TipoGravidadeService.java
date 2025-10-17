package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.TipoGravidadeRequestDTO;
import com.apisql.ApiSQL.dto.TipoGravidadeResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.TipoGravidade;
import com.apisql.ApiSQL.repository.TipoGravidadeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TipoGravidadeService {

    private final TipoGravidadeRepository tipoGravidadeRepository;
    private final ObjectMapper objectMapper;

    public TipoGravidadeService(TipoGravidadeRepository tipoGravidadeRepository, ObjectMapper objectMapper) {
        this.tipoGravidadeRepository = tipoGravidadeRepository;
        this.objectMapper = objectMapper;
    }

    public List<TipoGravidadeResponseDTO> findAll() {
        List<TipoGravidade> tipos = tipoGravidadeRepository.findAll();
        return tipos.stream()
                .map(t -> objectMapper.convertValue(t, TipoGravidadeResponseDTO.class))
                .toList();
    }

    public TipoGravidadeResponseDTO findById(Integer id) {
        Optional<TipoGravidade> response = tipoGravidadeRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), TipoGravidadeResponseDTO.class);
        }
        throw new ResourceNotFoundException("TipoGravidade não encontrada com ID: " + id);
    }

    @Transactional
    public TipoGravidadeResponseDTO save(TipoGravidadeRequestDTO dto) {
        TipoGravidade tipo = objectMapper.convertValue(dto, TipoGravidade.class);
        TipoGravidade savedTipo = tipoGravidadeRepository.save(tipo);
        return objectMapper.convertValue(savedTipo, TipoGravidadeResponseDTO.class);
    }

    @Transactional
    public TipoGravidadeResponseDTO update(Integer id, TipoGravidadeRequestDTO dto) {
        TipoGravidade tipo = tipoGravidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoGravidade com id:" + id + " não encontrada para atualização"));

        tipo.setNome(dto.getNome());
        tipo.setUpdatedAt(LocalDateTime.now());

        TipoGravidade updatedTipo = tipoGravidadeRepository.save(tipo);
        return objectMapper.convertValue(updatedTipo, TipoGravidadeResponseDTO.class);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!tipoGravidadeRepository.existsById(id)) {
            throw new ResourceNotFoundException("TipoGravidade com id:" + id + " não encontrada para exclusão");
        }
        tipoGravidadeRepository.deleteById(id);
    }
}