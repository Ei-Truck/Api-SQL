package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.TipoInfracaoRequestDTO;
import com.apisql.ApiSQL.dto.TipoInfracaoResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.TipoInfracao;
import com.apisql.ApiSQL.model.TipoGravidade;
import com.apisql.ApiSQL.repository.TipoInfracaoRepository;
import com.apisql.ApiSQL.repository.TipoGravidadeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public TipoInfracaoService(TipoInfracaoRepository tipoInfracaoRepository, TipoGravidadeRepository tipoGravidadeRepository, ObjectMapper objectMapper) {
        this.tipoInfracaoRepository = tipoInfracaoRepository;
        this.tipoGravidadeRepository = tipoGravidadeRepository;
        this.objectMapper = objectMapper;
    }

    public List<TipoInfracaoResponseDTO> findAll() {
        List<TipoInfracao> tipos = tipoInfracaoRepository.findAll();
        return tipos.stream()
                .map(t -> objectMapper.convertValue(t, TipoInfracaoResponseDTO.class))
                .toList();
    }

    public TipoInfracaoResponseDTO findById(Integer id) {
        Optional<TipoInfracao> response = tipoInfracaoRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), TipoInfracaoResponseDTO.class);
        }
        throw new ResourceNotFoundException("TipoInfracao não encontrada com ID: " + id);
    }

    @Transactional
    public TipoInfracaoResponseDTO save(TipoInfracaoRequestDTO dto) {
        TipoGravidade tipoGravidade = tipoGravidadeRepository.findById(dto.getIdTipoGravidade())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID TipoGravidade inválido."));

        TipoInfracao tipo = new TipoInfracao();
        tipo.setNome(dto.getNome());
        tipo.setPontuacao(dto.getPontuacao());
        tipo.setTipoGravidade(tipoGravidade);

        TipoInfracao savedTipo = tipoInfracaoRepository.save(tipo);
        return objectMapper.convertValue(savedTipo, TipoInfracaoResponseDTO.class);
    }

    @Transactional
    public TipoInfracaoResponseDTO update(Integer id, TipoInfracaoRequestDTO dto) {
        TipoInfracao tipo = tipoInfracaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoInfracao com id:" + id + " não encontrada para atualização"));

        TipoGravidade tipoGravidade = tipoGravidadeRepository.findById(dto.getIdTipoGravidade())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID TipoGravidade inválido."));

        tipo.setNome(dto.getNome());
        tipo.setPontuacao(dto.getPontuacao());
        tipo.setTipoGravidade(tipoGravidade);
        tipo.setUpdatedAt(LocalDateTime.now());

        TipoInfracao updatedTipo = tipoInfracaoRepository.save(tipo);
        return objectMapper.convertValue(updatedTipo, TipoInfracaoResponseDTO.class);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!tipoInfracaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("TipoInfracao com id:" + id + " não encontrada para exclusão");
        }
        tipoInfracaoRepository.deleteById(id);
    }
}