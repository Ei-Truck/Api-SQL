package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.InfracaoRequestDTO;
import com.apisql.ApiSQL.dto.InfracaoResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.Infracao;
import com.apisql.ApiSQL.model.Viagem;
import com.apisql.ApiSQL.model.Motorista;
import com.apisql.ApiSQL.model.TipoInfracao;
import com.apisql.ApiSQL.repository.InfracaoRepository;
import com.apisql.ApiSQL.repository.ViagemRepository;
import com.apisql.ApiSQL.repository.MotoristaRepository;
import com.apisql.ApiSQL.repository.TipoInfracaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InfracaoService {

    private final InfracaoRepository infracaoRepository;
    private final ViagemRepository viagemRepository;
    private final MotoristaRepository motoristaRepository;
    private final TipoInfracaoRepository tipoInfracaoRepository;
    private final ObjectMapper objectMapper;

    public InfracaoService(InfracaoRepository infracaoRepository, ViagemRepository viagemRepository, MotoristaRepository motoristaRepository, TipoInfracaoRepository tipoInfracaoRepository, ObjectMapper objectMapper) {
        this.infracaoRepository = infracaoRepository;
        this.viagemRepository = viagemRepository;
        this.motoristaRepository = motoristaRepository;
        this.tipoInfracaoRepository = tipoInfracaoRepository;
        this.objectMapper = objectMapper;
    }

    public List<InfracaoResponseDTO> findAll() {
        List<Infracao> infracoes = infracaoRepository.findAll();
        return infracoes.stream()
                .map(i -> objectMapper.convertValue(i, InfracaoResponseDTO.class))
                .toList();
    }

    public InfracaoResponseDTO findById(Integer id) {
        Optional<Infracao> response = infracaoRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), InfracaoResponseDTO.class);
        }
        throw new ResourceNotFoundException("Infração não encontrada com ID: " + id);
    }

    @Transactional
    public InfracaoResponseDTO save(InfracaoRequestDTO dto) {
        Viagem viagem = viagemRepository.findById(dto.getIdViagem())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Viagem inválido."));
        Motorista motorista = motoristaRepository.findById(dto.getIdMotorista())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Motorista inválido."));
        TipoInfracao tipoInfracao = tipoInfracaoRepository.findById(dto.getIdTipoInfracao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID TipoInfração inválido."));

        Infracao infracao = new Infracao();
        infracao.setViagem(viagem);
        infracao.setMotorista(motorista);
        infracao.setTipoInfracao(tipoInfracao);
        infracao.setLatitude(dto.getLatitude());
        infracao.setLongitude(dto.getLongitude());
        infracao.setVelocidadeKmh(dto.getVelocidadeKmh());
        infracao.setDtHrEvento(LocalDateTime.now());

        Infracao savedInfracao = infracaoRepository.save(infracao);
        return objectMapper.convertValue(savedInfracao, InfracaoResponseDTO.class);
    }

    @Transactional
    public InfracaoResponseDTO update(Integer id, InfracaoRequestDTO dto) {
        Infracao infracao = infracaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Infração com id:" + id + " não encontrada para atualização"));

        Viagem viagem = viagemRepository.findById(dto.getIdViagem())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Viagem inválido."));
        Motorista motorista = motoristaRepository.findById(dto.getIdMotorista())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Motorista inválido."));
        TipoInfracao tipoInfracao = tipoInfracaoRepository.findById(dto.getIdTipoInfracao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID TipoInfração inválido."));

        infracao.setViagem(viagem);
        infracao.setMotorista(motorista);
        infracao.setTipoInfracao(tipoInfracao);
        infracao.setLatitude(dto.getLatitude());
        infracao.setLongitude(dto.getLongitude());
        infracao.setVelocidadeKmh(dto.getVelocidadeKmh());
        infracao.setUpdatedAt(LocalDateTime.now());

        Infracao updatedInfracao = infracaoRepository.save(infracao);
        return objectMapper.convertValue(updatedInfracao, InfracaoResponseDTO.class);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!infracaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Infração com id:" + id + " não encontrada para exclusão");
        }
        infracaoRepository.deleteById(id);
    }
}