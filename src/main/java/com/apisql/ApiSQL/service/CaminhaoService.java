package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.CaminhaoRequestDTO;
import com.apisql.ApiSQL.dto.CaminhaoResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.Caminhao;
import com.apisql.ApiSQL.model.Segmento;
import com.apisql.ApiSQL.model.Unidade;
import com.apisql.ApiSQL.repository.CaminhaoRepository;
import com.apisql.ApiSQL.repository.SegmentoRepository;
import com.apisql.ApiSQL.repository.UnidadeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CaminhaoService {

    private final CaminhaoRepository caminhaoRepository;
    private final SegmentoRepository segmentoRepository;
    private final UnidadeRepository unidadeRepository;
    private final ObjectMapper objectMapper;

    public CaminhaoService(CaminhaoRepository caminhaoRepository, SegmentoRepository segmentoRepository, UnidadeRepository unidadeRepository, ObjectMapper objectMapper) {
        this.caminhaoRepository = caminhaoRepository;
        this.segmentoRepository = segmentoRepository;
        this.unidadeRepository = unidadeRepository;
        this.objectMapper = objectMapper;
    }

    public List<CaminhaoResponseDTO> findAll() {
        List<Caminhao> caminhoes = caminhaoRepository.findAll();
        return caminhoes.stream()
                .map(c -> objectMapper.convertValue(c, CaminhaoResponseDTO.class))
                .toList();
    }

    public CaminhaoResponseDTO findById(Integer id) {
        Optional<Caminhao> response = caminhaoRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), CaminhaoResponseDTO.class);
        }
        throw new ResourceNotFoundException("Caminhão não encontrado com ID: " + id);
    }

    @Transactional
    public CaminhaoResponseDTO save(CaminhaoRequestDTO dto) {
        Segmento segmento = segmentoRepository.findById(dto.getIdSegmento())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Segmento inválido."));
        Unidade unidade = unidadeRepository.findById(dto.getIdUnidade())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Unidade inválido."));

        Caminhao caminhao = new Caminhao();
        caminhao.setChassi(dto.getChassi());
        caminhao.setPlaca(dto.getPlaca());
        caminhao.setModelo(dto.getModelo());
        caminhao.setAnoFabricacao(dto.getAnoFabricacao());
        caminhao.setNumeroFrota(dto.getNumeroFrota());
        caminhao.setSegmento(segmento);
        caminhao.setUnidade(unidade);

        Caminhao savedCaminhao = caminhaoRepository.save(caminhao);
        return objectMapper.convertValue(savedCaminhao, CaminhaoResponseDTO.class);
    }

    @Transactional
    public CaminhaoResponseDTO update(Integer id, CaminhaoRequestDTO dto) {
        Caminhao caminhao = caminhaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Caminhão com id:" + id + " não encontrado para atualização"));

        Segmento segmento = segmentoRepository.findById(dto.getIdSegmento())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Segmento inválido."));
        Unidade unidade = unidadeRepository.findById(dto.getIdUnidade())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Unidade inválido."));

        caminhao.setChassi(dto.getChassi());
        caminhao.setPlaca(dto.getPlaca());
        caminhao.setModelo(dto.getModelo());
        caminhao.setAnoFabricacao(dto.getAnoFabricacao());
        caminhao.setNumeroFrota(dto.getNumeroFrota());
        caminhao.setSegmento(segmento);
        caminhao.setUnidade(unidade);

        caminhao.setUpdatedAt(LocalDateTime.now());
        Caminhao updatedCaminhao = caminhaoRepository.save(caminhao);
        return objectMapper.convertValue(updatedCaminhao, CaminhaoResponseDTO.class);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!caminhaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Caminhão com id:" + id + " não encontrado para exclusão");
        }
        caminhaoRepository.deleteById(id);
    }
}