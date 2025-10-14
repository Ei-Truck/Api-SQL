package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.InfracaoRequestDTO;
import com.apisql.ApiSQL.dto.InfracaoResponseDTO;
import com.apisql.ApiSQL.model.Infracao;
import com.apisql.ApiSQL.model.Motorista;
import com.apisql.ApiSQL.model.TipoInfracao;
import com.apisql.ApiSQL.model.Viagem;
import com.apisql.ApiSQL.repository.InfracaoRepository;
import com.apisql.ApiSQL.repository.MotoristaRepository;
import com.apisql.ApiSQL.repository.TipoInfracaoRepository;
import com.apisql.ApiSQL.repository.ViagemRepository;
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
public class InfracaoService {

    private final InfracaoRepository infracaoRepository;
    private final ViagemRepository viagemRepository;
    private final MotoristaRepository motoristaRepository;
    private final TipoInfracaoRepository tipoInfracaoRepository;
    private final ObjectMapper objectMapper;

    public InfracaoService(InfracaoRepository infracaoRepository, ObjectMapper objectMapper,
                           ViagemRepository viagemRepository, MotoristaRepository motoristaRepository,
                           TipoInfracaoRepository tipoInfracaoRepository) {
        this.infracaoRepository = infracaoRepository;
        this.objectMapper = objectMapper;
        this.viagemRepository = viagemRepository;
        this.motoristaRepository = motoristaRepository;
        this.tipoInfracaoRepository = tipoInfracaoRepository;
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
        throw new EntityNotFoundException("Infração não encontrada com ID: " + id);
    }

    @Transactional
    public InfracaoResponseDTO save(InfracaoRequestDTO dto) {
        Viagem viagem = viagemRepository.findById(dto.getIdViagem())
                .orElseThrow(() -> new EntityNotFoundException("Viagem não encontrada com ID: " + dto.getIdViagem()));

        Motorista motorista = motoristaRepository.findById(dto.getIdMotorista())
                .orElseThrow(() -> new EntityNotFoundException("Motorista não encontrado com ID: " + dto.getIdMotorista()));

        TipoInfracao tipoInfracao = tipoInfracaoRepository.findById(dto.getIdTipoInfracao())
                .orElseThrow(() -> new EntityNotFoundException("TipoInfração não encontrado com ID: " + dto.getIdTipoInfracao()));

        Infracao infracao = new Infracao();
        infracao.setViagem(viagem);
        infracao.setMotorista(motorista);
        infracao.setDtHrEvento(dto.getDtHrEvento());
        infracao.setTipoInfracao(tipoInfracao);
        infracao.setLatitude(dto.getLatitude());
        infracao.setLongitude(dto.getLongitude());
        infracao.setVelocidadeKmh(dto.getVelocidadeKmh());
        Infracao savedInfracao = infracaoRepository.save(infracao);
        return objectMapper.convertValue(savedInfracao, InfracaoResponseDTO.class);
    }

    @Transactional
    public InfracaoResponseDTO update(Integer id, InfracaoRequestDTO dto) {
        Infracao infracao = infracaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Infração com id:" + id + " não encontrada"));

        Viagem viagem = viagemRepository.findById(dto.getIdViagem())
                .orElseThrow(() -> new EntityNotFoundException("Viagem não encontrada com ID: " + dto.getIdViagem()));

        Motorista motorista = motoristaRepository.findById(dto.getIdMotorista())
                .orElseThrow(() -> new EntityNotFoundException("Motorista não encontrado com ID: " + dto.getIdMotorista()));

        TipoInfracao tipoInfracao = tipoInfracaoRepository.findById(dto.getIdTipoInfracao())
                .orElseThrow(() -> new EntityNotFoundException("TipoInfração não encontrado com ID: " + dto.getIdTipoInfracao()));

        infracao.setViagem(viagem);
        infracao.setMotorista(motorista);
        infracao.setDtHrEvento(dto.getDtHrEvento());
        infracao.setTipoInfracao(tipoInfracao);
        infracao.setLatitude(dto.getLatitude());
        infracao.setLongitude(dto.getLongitude());
        infracao.setVelocidadeKmh(dto.getVelocidadeKmh());
        infracao.setUpdatedAt(LocalDateTime.now());

        Infracao updatedInfracao = infracaoRepository.save(infracao);
        return objectMapper.convertValue(updatedInfracao, InfracaoResponseDTO.class);
    }

    public void deleteById(Integer id) {
        if (!infracaoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Infração com id:" + id + " não encontrada para exclusão");
        }
        infracaoRepository.deleteById(id);
    }
}