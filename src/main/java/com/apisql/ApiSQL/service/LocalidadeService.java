package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.LocalidadeRequestDTO;
import com.apisql.ApiSQL.dto.LocalidadeResponseDTO;
import com.apisql.ApiSQL.model.Localidade;
import com.apisql.ApiSQL.repository.LocalidadeRepository;
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
public class LocalidadeService {

    private final LocalidadeRepository localidadeRepository;
    private final ObjectMapper objectMapper;

    public LocalidadeService(LocalidadeRepository localidadeRepository, ObjectMapper objectMapper) {
        this.localidadeRepository = localidadeRepository;
        this.objectMapper = objectMapper;
    }

    public List<LocalidadeResponseDTO> findAll() {
        List<Localidade> localidades = localidadeRepository.findAll();
        return localidades.stream()
                .map(l -> objectMapper.convertValue(l, LocalidadeResponseDTO.class))
                .toList();
    }

    public LocalidadeResponseDTO findById(Integer id) {
        Optional<Localidade> response = localidadeRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), LocalidadeResponseDTO.class);
        }
        throw new EntityNotFoundException("Localidade não encontrada com ID: " + id);
    }

    @Transactional
    public LocalidadeResponseDTO save(LocalidadeRequestDTO dto) {
        Localidade localidade = new Localidade();
        localidade.setCep(dto.getCep());
        localidade.setNumeroRua(dto.getNumeroRua());
        localidade.setUfEstado(dto.getUfEstado());
        localidade.setNome(dto.getNome());

        Localidade savedLocalidade = localidadeRepository.save(localidade);
        return objectMapper.convertValue(savedLocalidade, LocalidadeResponseDTO.class);
    }

    @Transactional
    public LocalidadeResponseDTO update(Integer id, LocalidadeRequestDTO dto) {
        Localidade localidade = localidadeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Localidade com id:" + id + " não encontrada"));

        localidade.setCep(dto.getCep());
        localidade.setNumeroRua(dto.getNumeroRua());
        localidade.setUfEstado(dto.getUfEstado());
        localidade.setNome(dto.getNome());
        localidade.setUpdatedAt(LocalDateTime.now());

        Localidade updatedLocalidade = localidadeRepository.save(localidade);
        return objectMapper.convertValue(updatedLocalidade, LocalidadeResponseDTO.class);
    }

    public void deleteById(Integer id) {
        if (!localidadeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Localidade com id:" + id + " não encontrada para exclusão");
        }
        localidadeRepository.deleteById(id);
    }
}