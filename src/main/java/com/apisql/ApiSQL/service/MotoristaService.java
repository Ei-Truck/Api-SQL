package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.MotoristaRequestDTO;
import com.apisql.ApiSQL.dto.MotoristaResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.Motorista;
import com.apisql.ApiSQL.model.Unidade;
import com.apisql.ApiSQL.model.TipoRisco;
import com.apisql.ApiSQL.repository.MotoristaRepository;
import com.apisql.ApiSQL.repository.UnidadeRepository;
import com.apisql.ApiSQL.repository.TipoRiscoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MotoristaService {

    private final MotoristaRepository motoristaRepository;
    private final UnidadeRepository unidadeRepository;
    private final TipoRiscoRepository tipoRiscoRepository;
    private final ObjectMapper objectMapper;

    public MotoristaService(MotoristaRepository motoristaRepository, UnidadeRepository unidadeRepository, TipoRiscoRepository tipoRiscoRepository, ObjectMapper objectMapper) {
        this.motoristaRepository = motoristaRepository;
        this.unidadeRepository = unidadeRepository;
        this.tipoRiscoRepository = tipoRiscoRepository;
        this.objectMapper = objectMapper;
    }

    public List<MotoristaResponseDTO> findAll() {
        List<Motorista> motoristas = motoristaRepository.findAll();
        return motoristas.stream()
                .map(m -> objectMapper.convertValue(m, MotoristaResponseDTO.class))
                .toList();
    }

    public MotoristaResponseDTO findById(Integer id) {
        Optional<Motorista> response = motoristaRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), MotoristaResponseDTO.class);
        }
        throw new ResourceNotFoundException("Motorista não encontrado com ID: " + id);
    }

    @Transactional
    public MotoristaResponseDTO save(MotoristaRequestDTO dto) {
        Unidade unidade = unidadeRepository.findById(dto.getIdUnidade())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Unidade inválido."));
        TipoRisco tipoRisco = tipoRiscoRepository.findById(dto.getIdTipoRisco())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID TipoRisco inválido."));

        Motorista motorista = new Motorista();
        motorista.setCpf(dto.getCpf());
        motorista.setCnh(dto.getCnh());
        motorista.setNomeCompleto(dto.getNomeCompleto());
        motorista.setTelefone(dto.getTelefone());
        motorista.setEmailEmpresa(dto.getEmailEmpresa());
        motorista.setUrlFoto(dto.getUrlFoto());
        motorista.setUnidade(unidade);
        motorista.setTipoRisco(tipoRisco);

        Motorista savedMotorista = motoristaRepository.save(motorista);
        return objectMapper.convertValue(savedMotorista, MotoristaResponseDTO.class);
    }

    @Transactional
    public MotoristaResponseDTO update(Integer id, MotoristaRequestDTO dto) {
        Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Motorista com id:" + id + " não encontrado para atualização"));

        Unidade unidade = unidadeRepository.findById(dto.getIdUnidade())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Unidade inválido."));
        TipoRisco tipoRisco = tipoRiscoRepository.findById(dto.getIdTipoRisco())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID TipoRisco inválido."));

        motorista.setCpf(dto.getCpf());
        motorista.setCnh(dto.getCnh());
        motorista.setNomeCompleto(dto.getNomeCompleto());
        motorista.setTelefone(dto.getTelefone());
        motorista.setEmailEmpresa(dto.getEmailEmpresa());
        motorista.setUrlFoto(dto.getUrlFoto());
        motorista.setUnidade(unidade);
        motorista.setTipoRisco(tipoRisco);

        motorista.setUpdatedAt(LocalDateTime.now());
        Motorista updatedMotorista = motoristaRepository.save(motorista);
        return objectMapper.convertValue(updatedMotorista, MotoristaResponseDTO.class);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!motoristaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Motorista com id:" + id + " não encontrado para exclusão");
        }
        motoristaRepository.deleteById(id);
    }
}