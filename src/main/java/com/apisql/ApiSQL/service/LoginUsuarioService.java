package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.LoginUsuarioRequestDTO;
import com.apisql.ApiSQL.dto.LoginUsuarioResponseDTO;
import com.apisql.ApiSQL.model.LoginUsuario;
import com.apisql.ApiSQL.repository.LoginUsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginUsuarioService {

    private final ObjectMapper mapper = new ObjectMapper();

    private final LoginUsuarioRepository repository;

    public LoginUsuarioService(LoginUsuarioRepository repository) {
        this.repository = repository;
    }

    private LoginUsuarioResponseDTO toResponse(LoginUsuario login) {
        return mapper.convertValue(login, LoginUsuarioResponseDTO.class);
    }

    private LoginUsuario toEntity(LoginUsuarioRequestDTO dto) {
        return mapper.convertValue(dto, LoginUsuario.class);
    }

    public List<LoginUsuarioResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<LoginUsuarioResponseDTO> listarPorUsuario(Integer idUsuario) {
        return repository.findByUsuarioId(idUsuario).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public LoginUsuarioResponseDTO salvar(LoginUsuarioRequestDTO dto) {
        LoginUsuario login = toEntity(dto);
        return toResponse(repository.save(login));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
