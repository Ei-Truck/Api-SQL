package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.CaminhaoRequestDTO;
import com.apisql.ApiSQL.dto.CaminhaoResponseDTO;
import com.apisql.ApiSQL.model.Caminhao;
import com.apisql.ApiSQL.model.Segmento;
import com.apisql.ApiSQL.model.Unidade;
import com.apisql.ApiSQL.repository.CaminhaoRepository;
import com.apisql.ApiSQL.repository.SegmentoRepository;
import com.apisql.ApiSQL.repository.UnidadeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CaminhaoService {

    private final CaminhaoRepository caminhaoRepository;
    private final SegmentoRepository segmentoRepository;
    private final UnidadeRepository unidadeRepository;
    private final ObjectMapper objectMapper;

    public CaminhaoService(CaminhaoRepository caminhaoRepository, ObjectMapper objectMapper, SegmentoRepository segmentoRepository, UnidadeRepository unidadeRepository) {

        this.caminhaoRepository = caminhaoRepository;
        this.objectMapper = objectMapper;
        this.segmentoRepository = segmentoRepository;
        this.unidadeRepository = unidadeRepository;
    }

    public List<CaminhaoResponseDTO> findAll() {
        List<Caminhao> caminhoes = caminhaoRepository.findAll();
        return caminhoes.stream().map(
                c -> objectMapper.convertValue(c, CaminhaoResponseDTO.class)
        ).toList();
    }

    public CaminhaoResponseDTO findById(Integer id) {
        Optional<Caminhao> response = caminhaoRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), CaminhaoResponseDTO.class);
        }
        throw new EntityNotFoundException("Caminhao nao encontrado");
    }

    @Transactional
    public CaminhaoResponseDTO save(CaminhaoRequestDTO dto) {
        Caminhao caminhao = new Caminhao();
        Segmento segmento = segmentoRepository.findById(dto.getIdSegmento())
                .orElseThrow(() -> new EntityNotFoundException("Segmento não encontrado com ID: " + dto.getIdSegmento()));

        Unidade unidade = unidadeRepository.findById(dto.getIdUnidade())
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada com ID: " + dto.getIdUnidade()));

        caminhao.setChassi(dto.getChassi());
        caminhao.setSegmento(segmento);
        caminhao.setUnidade(unidade);
        caminhao.setPlaca(dto.getPlaca());
        caminhao.setModelo(dto.getModelo());
        caminhao.setAnoFabricacao(dto.getAnoFabricacao());
        caminhao.setNumeroFrota(dto.getNumeroFrota());
        Caminhao savedCaminhao = caminhaoRepository.save(caminhao);
        return objectMapper.convertValue(savedCaminhao, CaminhaoResponseDTO.class);
    }

    @Transactional
    public CaminhaoResponseDTO update(Integer id, Caminhao caminhaoAtualizado) {
        Caminhao caminhao = caminhaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Caminhao com id:" + id + " não encontrado"));

        caminhao.setChassi(caminhaoAtualizado.getChassi());
        return objectMapper.convertValue(caminhaoRepository.save(caminhaoAtualizado), CaminhaoResponseDTO.class);
    }


    public void deleteById(Integer id) {
        caminhaoRepository.deleteById(id);
    }


}
