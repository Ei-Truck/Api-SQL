package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.TipoInfracaoRequestDTO;
import com.apisql.ApiSQL.dto.TipoInfracaoResponseDTO;
import com.apisql.ApiSQL.model.TipoGravidade;
import com.apisql.ApiSQL.model.TipoInfracao;
import com.apisql.ApiSQL.repository.TipoGravidadeRepository;
import com.apisql.ApiSQL.repository.TipoInfracaoRepository;
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
public class TipoInfracaoService {

    private final TipoInfracaoRepository tipoInfracaoRepository;
    private final TipoGravidadeRepository tipoGravidadeRepository;
    private final ObjectMapper objectMapper;

    public TipoInfracaoService(TipoInfracaoRepository tipoInfracaoRepository, ObjectMapper objectMapper, TipoGravidadeRepository tipoGravidadeRepository) {
        this.tipoInfracaoRepository = tipoInfracaoRepository;
        this.objectMapper = objectMapper;
        this.tipoGravidadeRepository = tipoGravidadeRepository;
    }

    public List<TipoInfracaoResponseDTO> findAll() {
        List<TipoInfracao> infracoes = tipoInfracaoRepository.findAll();
        return infracoes.stream()
                .map(i -> objectMapper.convertValue(i, TipoInfracaoResponseDTO.class))
                .toList();
    }

    public TipoInfracaoResponseDTO findById(Integer id) {
        Optional<TipoInfracao> response = tipoInfracaoRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), TipoInfracaoResponseDTO.class);
        }
        throw new EntityNotFoundException("TipoInfracao não encontrado com ID: " + id);
    }

    @Transactional
    public TipoInfracaoResponseDTO save(TipoInfracaoRequestDTO dto) {
        TipoGravidade tipoGravidade = tipoGravidadeRepository.findById(dto.getIdTipoGravidade())
                .orElseThrow(() -> new EntityNotFoundException("TipoGravidade não encontrado com ID: " + dto.getIdTipoGravidade()));

        TipoInfracao tipoInfracao = new TipoInfracao();
        tipoInfracao.setNome(dto.getNome());
        tipoInfracao.setPontuacao(dto.getPontuacao());
        tipoInfracao.setTipoGravidade(tipoGravidade);

        TipoInfracao savedTipoInfracao = tipoInfracaoRepository.save(tipoInfracao);
        return objectMapper.convertValue(savedTipoInfracao, TipoInfracaoResponseDTO.class);
    }

    @Transactional
    public TipoInfracaoResponseDTO update(Integer id, TipoInfracaoRequestDTO dto) {
        TipoInfracao tipoInfracao = tipoInfracaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoInfracao com id:" + id + " não encontrado"));

        TipoGravidade tipoGravidade = tipoGravidadeRepository.findById(dto.getIdTipoGravidade())
                .orElseThrow(() -> new EntityNotFoundException("TipoGravidade não encontrado com ID: " + dto.getIdTipoGravidade()));

        tipoInfracao.setNome(dto.getNome());
        tipoInfracao.setPontuacao(dto.getPontuacao());
        tipoInfracao.setTipoGravidade(tipoGravidade);
        tipoInfracao.setUpdatedAt(LocalDateTime.now());

        TipoInfracao updatedTipoInfracao = tipoInfracaoRepository.save(tipoInfracao);
        return objectMapper.convertValue(updatedTipoInfracao, TipoInfracaoResponseDTO.class);
    }

    public void deleteById(Integer id) {
        if (!tipoInfracaoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoInfracao com id:" + id + " não encontrado para exclusão");
        }
        tipoInfracaoRepository.deleteById(id);
    }
}