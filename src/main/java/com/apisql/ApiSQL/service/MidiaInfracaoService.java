package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.MidiaInfracaoRequestDTO;
import com.apisql.ApiSQL.dto.MidiaInfracaoResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.Infracao;
import com.apisql.ApiSQL.model.MidiaInfracao;
import com.apisql.ApiSQL.model.Motorista;
import com.apisql.ApiSQL.model.Viagem;
import com.apisql.ApiSQL.repository.InfracaoRepository;
import com.apisql.ApiSQL.repository.MidiaInfracaoRepository;
import com.apisql.ApiSQL.repository.MotoristaRepository;
import com.apisql.ApiSQL.repository.ViagemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MidiaInfracaoService {

    private final MidiaInfracaoRepository repository;
    private final ViagemRepository viagemRepository;
    private final InfracaoRepository infracaoRepository;
    private final MotoristaRepository motoristaRepository;
    private final ObjectMapper objectMapper;

    public MidiaInfracaoService(MidiaInfracaoRepository repository, ViagemRepository viagemRepository, InfracaoRepository infracaoRepository, MotoristaRepository motoristaRepository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.viagemRepository = viagemRepository;
        this.infracaoRepository = infracaoRepository;
        this.motoristaRepository = motoristaRepository;
        this.objectMapper = objectMapper;
    }

    public List<MidiaInfracaoResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(m -> objectMapper.convertValue(m, MidiaInfracaoResponseDTO.class))
                .toList();
    }

    public MidiaInfracaoResponseDTO findById(Integer id) {
        MidiaInfracao midia = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mídia de Infração não encontrada com ID: " + id));
        return objectMapper.convertValue(midia, MidiaInfracaoResponseDTO.class);
    }

    @Transactional
    public MidiaInfracaoResponseDTO save(MidiaInfracaoRequestDTO dto) {
        Viagem viagem = viagemRepository.findById(dto.getIdViagem())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Viagem inválido."));
        Infracao infracao = infracaoRepository.findById(dto.getIdInfracao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Infração inválido."));
        Motorista motorista = motoristaRepository.findById(dto.getIdMotorista())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Motorista inválido."));

        MidiaInfracao midia = new MidiaInfracao();
        midia.setViagem(viagem);
        midia.setInfracao(infracao);
        midia.setMotorista(motorista);
        midia.setUrl(dto.getUrl());
        midia.setIsConcat(dto.getIsConcat());
        midia.setTransactionMade(dto.getTransactionMade());
        midia.setUpdatedAt(LocalDateTime.now());

        MidiaInfracao savedMidia = repository.save(midia);
        return objectMapper.convertValue(savedMidia, MidiaInfracaoResponseDTO.class);
    }

    @Transactional
    public MidiaInfracaoResponseDTO update(Integer id, MidiaInfracaoRequestDTO dto) {
        MidiaInfracao midia = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mídia de Infração com id:" + id + " não encontrada para atualização"));

        Viagem viagem = viagemRepository.findById(dto.getIdViagem())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Viagem inválido."));
        Infracao infracao = infracaoRepository.findById(dto.getIdInfracao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Infração inválido."));
        Motorista motorista = motoristaRepository.findById(dto.getIdMotorista())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Motorista inválido."));

        midia.setViagem(viagem);
        midia.setInfracao(infracao);
        midia.setMotorista(motorista);
        midia.setUrl(dto.getUrl());
        midia.setIsConcat(dto.getIsConcat());
        midia.setTransactionMade(dto.getTransactionMade());
        midia.setUpdatedAt(LocalDateTime.now());

        MidiaInfracao updatedMidia = repository.save(midia);
        return objectMapper.convertValue(updatedMidia, MidiaInfracaoResponseDTO.class);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Mídia de Infração com id:" + id + " não encontrada para exclusão");
        }
        repository.deleteById(id);
    }
}