package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.RegistroRequestDTO;
import com.apisql.ApiSQL.dto.RegistroResponseDTO;
import com.apisql.ApiSQL.dto.RegistroTratativaPatchDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.Registro;
import com.apisql.ApiSQL.model.Viagem;
import com.apisql.ApiSQL.model.Motorista;
import com.apisql.ApiSQL.repository.RegistroRepository;
import com.apisql.ApiSQL.repository.ViagemRepository;
import com.apisql.ApiSQL.repository.MotoristaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroService {

    private final RegistroRepository registroRepository;
    private final ViagemRepository viagemRepository;
    private final MotoristaRepository motoristaRepository;
    private final ObjectMapper objectMapper;

    public RegistroService(RegistroRepository registroRepository, ViagemRepository viagemRepository, MotoristaRepository motoristaRepository, ObjectMapper objectMapper) {
        this.registroRepository = registroRepository;
        this.viagemRepository = viagemRepository;
        this.motoristaRepository = motoristaRepository;
        this.objectMapper = objectMapper;
    }

    public List<RegistroResponseDTO> findAll() {
        return registroRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public RegistroResponseDTO findById(Integer id) {
        Registro registro = registroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado com ID: " + id));
        return convertToResponseDTO(registro);
    }

    @Transactional
    public RegistroResponseDTO save(RegistroRequestDTO dto) {
        Viagem viagem = viagemRepository.findById(dto.getIdViagem())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Viagem inválido."));

        Motorista motorista = null;
        if (dto.getIdMotorista() != null) {
            motorista = motoristaRepository.findById(dto.getIdMotorista())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Motorista inválido."));
        }

        Registro registro = new Registro();
        registro.setViagem(viagem);
        registro.setMotorista(motorista);
        registro.setTratativa(dto.getTratativa());
        registro.setTransactionMade(dto.getTransactionMade());
        registro.setDtHrRegistro(LocalDateTime.now());
        registro.setUpdatedAt(LocalDateTime.now());

        Registro savedRegistro = registroRepository.save(registro);
        return convertToResponseDTO(savedRegistro);
    }

    @Transactional
    public RegistroResponseDTO update(Integer id, RegistroRequestDTO dto) {
        Registro registro = registroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro com id:" + id + " não encontrado para atualização"));

        Viagem viagem = viagemRepository.findById(dto.getIdViagem())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Viagem inválido."));

        Motorista motorista = null;
        if (dto.getIdMotorista() != null) {
            motorista = motoristaRepository.findById(dto.getIdMotorista())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Motorista inválido."));
        }

        registro.setViagem(viagem);
        registro.setMotorista(motorista);
        registro.setTratativa(dto.getTratativa());
        registro.setTransactionMade(dto.getTransactionMade());
        registro.setUpdatedAt(LocalDateTime.now());

        Registro updatedRegistro = registroRepository.save(registro);
        return convertToResponseDTO(updatedRegistro);
    }

    @Transactional
    public RegistroResponseDTO patchTratativa(Integer idViagem, Integer idMotorista, RegistroTratativaPatchDTO dto) {
        Registro registro = registroRepository.findByIdViagemAndIdMotorista(idViagem, idMotorista)
                .orElseThrow(() -> new ResourceNotFoundException("Registro com id da viagem: " + idViagem + " e id do motorista: " + idMotorista + " não encontrado para atualização"));

        registro.setTratativa(dto.getTratativa());
        registro.setUpdatedAt(LocalDateTime.now());

        Registro updatedRegistro = registroRepository.save(registro);
        return convertToResponseDTO(updatedRegistro);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!registroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Registro com id:" + id + " não encontrado para exclusão");
        }
        registroRepository.deleteById(id);
    }

    private RegistroResponseDTO convertToResponseDTO(Registro registro) {
        RegistroResponseDTO dto = objectMapper.convertValue(registro, RegistroResponseDTO.class);
        if (registro.getViagem() != null) {
            dto.setIdViagem(registro.getViagem().getId());
        }
        if (registro.getMotorista() != null) {
            dto.setIdMotorista(registro.getMotorista().getId());
        }
        return dto;
    }
}