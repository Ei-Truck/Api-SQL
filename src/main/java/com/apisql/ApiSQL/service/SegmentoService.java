package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.SegmentoRequestDTO;
import com.apisql.ApiSQL.dto.SegmentoResponseDTO;
import com.apisql.ApiSQL.model.Segmento;
import com.apisql.ApiSQL.repository.SegmentoRepository;
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
public class SegmentoService {

    private final SegmentoRepository segmentoRepository;
    private final ObjectMapper objectMapper;

    public SegmentoService(SegmentoRepository segmentoRepository, ObjectMapper objectMapper) {
        this.segmentoRepository = segmentoRepository;
        this.objectMapper = objectMapper;
    }

    public List<SegmentoResponseDTO> findAll() {
        List<Segmento> segmentos = segmentoRepository.findAll();
        return segmentos.stream()
                .map(s -> objectMapper.convertValue(s, SegmentoResponseDTO.class))
                .toList();
    }

    public SegmentoResponseDTO findById(Integer id) {
        Optional<Segmento> response = segmentoRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), SegmentoResponseDTO.class);
        }
        throw new EntityNotFoundException("Segmento não encontrado com ID: " + id);
    }

    @Transactional
    public SegmentoResponseDTO save(SegmentoRequestDTO dto) {
        Segmento segmento = new Segmento();
        segmento.setNome(dto.getNome());
        Segmento savedSegmento = segmentoRepository.save(segmento);
        return objectMapper.convertValue(savedSegmento, SegmentoResponseDTO.class);
    }

    @Transactional
    public SegmentoResponseDTO update(Integer id, SegmentoRequestDTO dto) {
        Segmento segmento = segmentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Segmento com id:" + id + " não encontrado"));

        segmento.setNome(dto.getNome());
        segmento.setUpdatedAt(LocalDateTime.now());

        Segmento updatedSegmento = segmentoRepository.save(segmento);
        return objectMapper.convertValue(updatedSegmento, SegmentoResponseDTO.class);
    }

    public void deleteById(Integer id) {
        if (!segmentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Segmento com id:" + id + " não encontrado para exclusão");
        }
        segmentoRepository.deleteById(id);
    }
}