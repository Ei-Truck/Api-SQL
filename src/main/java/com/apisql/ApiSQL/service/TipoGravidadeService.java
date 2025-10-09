package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.TipoGravidadeRequestDTO;
import com.apisql.ApiSQL.dto.TipoGravidadeResponseDTO;
import com.apisql.ApiSQL.model.TipoGravidade;
import com.apisql.ApiSQL.repository.TipoGravidadeRepository;
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
public class TipoGravidadeService {

    private final TipoGravidadeRepository tipoGravidadeRepository;
    private final ObjectMapper objectMapper;

    public TipoGravidadeService(TipoGravidadeRepository tipoGravidadeRepository, ObjectMapper objectMapper) {
        this.tipoGravidadeRepository = tipoGravidadeRepository;
        this.objectMapper = objectMapper;
    }

    public List<TipoGravidadeResponseDTO> findAll() {
        List<TipoGravidade> gravidades = tipoGravidadeRepository.findAll();
        return gravidades.stream()
                .map(g -> objectMapper.convertValue(g, TipoGravidadeResponseDTO.class))
                .toList();
    }

    public TipoGravidadeResponseDTO findById(Integer id) {
        Optional<TipoGravidade> response = tipoGravidadeRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), TipoGravidadeResponseDTO.class);
        }
        throw new EntityNotFoundException("TipoGravidade não encontrado com ID: " + id);
    }

    @Transactional
    public TipoGravidadeResponseDTO save(TipoGravidadeRequestDTO dto) {
        // Validação adicional para garantir que o ID não existe
        if (tipoGravidadeRepository.existsById(dto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "TipoGravidade com ID:" + dto.getId() + " já existe. O ID deve ser único, pois não é gerado automaticamente.");
        }

        TipoGravidade tipoGravidade = new TipoGravidade();
        tipoGravidade.setId(dto.getId());
        tipoGravidade.setNome(dto.getNome());

        TipoGravidade savedTipoGravidade = tipoGravidadeRepository.save(tipoGravidade);
        return objectMapper.convertValue(savedTipoGravidade, TipoGravidadeResponseDTO.class);
    }

    @Transactional
    public TipoGravidadeResponseDTO update(Integer id, TipoGravidadeRequestDTO dto) {
        TipoGravidade tipoGravidade = tipoGravidadeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoGravidade com id:" + id + " não encontrado"));

        tipoGravidade.setNome(dto.getNome());
        tipoGravidade.setUpdatedAt(LocalDateTime.now());

        TipoGravidade updatedTipoGravidade = tipoGravidadeRepository.save(tipoGravidade);
        return objectMapper.convertValue(updatedTipoGravidade, TipoGravidadeResponseDTO.class);
    }

    public void deleteById(Integer id) {
        if (!tipoGravidadeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoGravidade com id:" + id + " não encontrado para exclusão");
        }
        tipoGravidadeRepository.deleteById(id);
    }
}