package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.SegmentoRequestDTO;
import com.apisql.ApiSQL.dto.SegmentoResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.Segmento;
import com.apisql.ApiSQL.repository.SegmentoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        throw new ResourceNotFoundException("Segmento não encontrado com ID: " + id);
    }

    @Transactional
    public SegmentoResponseDTO save(SegmentoRequestDTO dto) {
        Segmento segmento = objectMapper.convertValue(dto, Segmento.class);
        Segmento savedSegmento = segmentoRepository.save(segmento);
        return objectMapper.convertValue(savedSegmento, SegmentoResponseDTO.class);
    }

    @Transactional
    public SegmentoResponseDTO update(Integer id, SegmentoRequestDTO dto) {
        Segmento segmento = segmentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Segmento com id:" + id + " não encontrado para atualização"));

        segmento.setNome(dto.getNome());
        segmento.setUpdatedAt(LocalDateTime.now());

        Segmento updatedSegmento = segmentoRepository.save(segmento);
        return objectMapper.convertValue(updatedSegmento, SegmentoResponseDTO.class);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!segmentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Segmento com id:" + id + " não encontrado para exclusão");
        }
        segmentoRepository.deleteById(id);
    }
}