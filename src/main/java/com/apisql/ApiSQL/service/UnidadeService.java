package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.UnidadeRequestDTO;
import com.apisql.ApiSQL.dto.UnidadeResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.Unidade;
import com.apisql.ApiSQL.model.Segmento;
import com.apisql.ApiSQL.model.Localidade;
import com.apisql.ApiSQL.repository.UnidadeRepository;
import com.apisql.ApiSQL.repository.SegmentoRepository;
import com.apisql.ApiSQL.repository.LocalidadeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;
    private final SegmentoRepository segmentoRepository;
    private final LocalidadeRepository localidadeRepository;
    private final ObjectMapper objectMapper;

    public UnidadeService(UnidadeRepository unidadeRepository, SegmentoRepository segmentoRepository, LocalidadeRepository localidadeRepository, ObjectMapper objectMapper) {
        this.unidadeRepository = unidadeRepository;
        this.segmentoRepository = segmentoRepository;
        this.localidadeRepository = localidadeRepository;
        this.objectMapper = objectMapper;
    }

    public List<UnidadeResponseDTO> findAll() {
        List<Unidade> unidades = unidadeRepository.findAll();
        return unidades.stream()
                .map(u -> objectMapper.convertValue(u, UnidadeResponseDTO.class))
                .toList();
    }

    public UnidadeResponseDTO findById(Integer id) {
        Optional<Unidade> response = unidadeRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), UnidadeResponseDTO.class);
        }
        throw new ResourceNotFoundException("Unidade não encontrada com ID: " + id);
    }

    @Transactional
    public UnidadeResponseDTO save(UnidadeRequestDTO dto) {
        Segmento segmento = segmentoRepository.findById(dto.getIdSegmento())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Segmento inválido."));
        Localidade localidade = localidadeRepository.findById(dto.getIdLocalidade())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Localidade inválido."));

        Unidade unidade = new Unidade();
        unidade.setNome(dto.getNome());
        unidade.setSegmento(segmento);
        unidade.setLocalidade(localidade);

        Unidade savedUnidade = unidadeRepository.save(unidade);
        return objectMapper.convertValue(savedUnidade, UnidadeResponseDTO.class);
    }

    @Transactional
    public UnidadeResponseDTO update(Integer id, UnidadeRequestDTO dto) {
        Unidade unidade = unidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unidade com id:" + id + " não encontrada para atualização"));

        Segmento segmento = segmentoRepository.findById(dto.getIdSegmento())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Segmento inválido."));
        Localidade localidade = localidadeRepository.findById(dto.getIdLocalidade())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Localidade inválido."));

        unidade.setNome(dto.getNome());
        unidade.setSegmento(segmento);
        unidade.setLocalidade(localidade);
        unidade.setUpdatedAt(LocalDateTime.now());

        Unidade updatedUnidade = unidadeRepository.save(unidade);
        return objectMapper.convertValue(updatedUnidade, UnidadeResponseDTO.class);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!unidadeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Unidade com id:" + id + " não encontrada para exclusão");
        }
        unidadeRepository.deleteById(id);
    }
}