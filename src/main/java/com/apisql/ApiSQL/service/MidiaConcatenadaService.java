package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.MidiaConcatenadaRequestDTO;
import com.apisql.ApiSQL.dto.MidiaConcatenadaResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.MidiaConcatenada;
import com.apisql.ApiSQL.model.Motorista;
import com.apisql.ApiSQL.model.Viagem;
import com.apisql.ApiSQL.repository.MidiaConcatenadaRepository;
import com.apisql.ApiSQL.repository.MotoristaRepository;
import com.apisql.ApiSQL.repository.ViagemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import software.amazon.awssdk.core.sync.RequestBody;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MidiaConcatenadaService {

    private final MidiaConcatenadaRepository repository;
    private final ViagemRepository viagemRepository;
    private final MotoristaRepository motoristaRepository;
    private final ObjectMapper objectMapper;


    public MidiaConcatenadaService(
            MidiaConcatenadaRepository repository,
            ViagemRepository viagemRepository,
            MotoristaRepository motoristaRepository,
            ObjectMapper objectMapper
    ) {
        this.repository = repository;
        this.viagemRepository = viagemRepository;
        this.motoristaRepository = motoristaRepository;
        this.objectMapper = objectMapper;
        }

    public List<MidiaConcatenadaResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(m -> objectMapper.convertValue(m, MidiaConcatenadaResponseDTO.class))
                .toList();
    }

    public MidiaConcatenadaResponseDTO findById(Integer id) {
        MidiaConcatenada midia = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mídia Concatenada não encontrada com ID: " + id));
        return objectMapper.convertValue(midia, MidiaConcatenadaResponseDTO.class);
    }

    @Transactional
    public MidiaConcatenadaResponseDTO save(MidiaConcatenadaRequestDTO dto) {
        Viagem viagem = viagemRepository.findById(dto.getIdViagem())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Viagem inválido."));
        Motorista motorista = motoristaRepository.findById(dto.getIdMotorista())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Motorista inválido."));

        MidiaConcatenada midia = new MidiaConcatenada();
        midia.setViagem(viagem);
        midia.setMotorista(motorista);
        midia.setUrl(dto.getUrl());
        midia.setTransactionMade(dto.getTransactionMade());
        midia.setUpdatedAt(LocalDateTime.now());

        MidiaConcatenada savedMidia = repository.save(midia);
        return objectMapper.convertValue(savedMidia, MidiaConcatenadaResponseDTO.class);
    }

    @Transactional
    public MidiaConcatenadaResponseDTO update(Integer id, MidiaConcatenadaRequestDTO dto) {
        MidiaConcatenada midia = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mídia Concatenada com id:" + id + " não encontrada para atualização"));

        Viagem viagem = viagemRepository.findById(dto.getIdViagem())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Viagem inválido."));
        Motorista motorista = motoristaRepository.findById(dto.getIdMotorista())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Motorista inválido."));

        midia.setViagem(viagem);
        midia.setMotorista(motorista);
        midia.setUrl(dto.getUrl());
        midia.setTransactionMade(dto.getTransactionMade());
        midia.setUpdatedAt(LocalDateTime.now());

        MidiaConcatenada updatedMidia = repository.save(midia);
        return objectMapper.convertValue(updatedMidia, MidiaConcatenadaResponseDTO.class);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Mídia Concatenada com id:" + id + " não encontrada para exclusão");
        }
        repository.deleteById(id);
    }


}
