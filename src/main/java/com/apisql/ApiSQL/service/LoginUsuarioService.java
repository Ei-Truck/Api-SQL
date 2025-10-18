package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.LoginUsuarioResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.LoginUsuario;
import com.apisql.ApiSQL.repository.LoginUsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LoginUsuarioService {

    private final ObjectMapper objectMapper;
    private final LoginUsuarioRepository loginUsuarioRepository;

    public LoginUsuarioService(ObjectMapper objectMapper, LoginUsuarioRepository loginUsuarioRepository) {
        this.loginUsuarioRepository = loginUsuarioRepository;
        this.objectMapper = objectMapper;
    }

    public List<LoginUsuarioResponseDTO> findAll() {
        List<LoginUsuario> logs = loginUsuarioRepository.findAll();
        return logs.stream()
                .map(l -> objectMapper.convertValue(l, LoginUsuarioResponseDTO.class))
                .toList();
    }

    public LoginUsuarioResponseDTO findById(Integer id) {
        Optional<LoginUsuario> response = loginUsuarioRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), LoginUsuarioResponseDTO.class);
        }
        throw new ResourceNotFoundException("Log de Login não encontrado com ID: " + id);
    }
}