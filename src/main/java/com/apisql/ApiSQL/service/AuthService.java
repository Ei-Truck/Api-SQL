package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.LoginUsuarioRequestDTO;
import com.apisql.ApiSQL.dto.LoginUsuarioResponseDTO;
import com.apisql.ApiSQL.model.LoginUsuario;
import com.apisql.ApiSQL.repository.LoginUsuarioRepository;
import com.apisql.ApiSQL.repository.UsuarioRepository;
import com.apisql.ApiSQL.security.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder; // Importação essencial
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final LoginUsuarioRepository loginUsuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    // PasswordEncoder injetado no construtor
    public AuthService(
            UsuarioRepository usuarioRepository,
            LoginUsuarioRepository loginUsuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtProvider jwtProvider
    ) {
        this.usuarioRepository = usuarioRepository;
        this.loginUsuarioRepository = loginUsuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public LoginUsuarioResponseDTO login(LoginUsuarioRequestDTO req) {
        var user = usuarioRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Credenciais inválidas"));

        if (!passwordEncoder.matches(req.getSenha(), user.getHashSenha())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }

        loginUsuarioRepository.save(new LoginUsuario(user.getId()));
        String token = jwtProvider.generateToken(user);

        return new LoginUsuarioResponseDTO(token, user);
    }
}