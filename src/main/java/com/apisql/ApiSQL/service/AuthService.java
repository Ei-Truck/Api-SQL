package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.LoginUsuarioRequestDTO;
import com.apisql.ApiSQL.dto.LoginUsuarioResponseDTO;
import com.apisql.ApiSQL.repository.LoginUsuarioRepository;
import com.apisql.ApiSQL.repository.UsuarioRepository;
import com.apisql.ApiSQL.security.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final LoginUsuarioRepository loginUsuarioRepository;

    public AuthService(
            UsuarioRepository usuarioRepository,
            LoginUsuarioRepository loginUsuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtProvider jwtProvider
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.loginUsuarioRepository = loginUsuarioRepository;
    }

    public LoginUsuarioResponseDTO login(LoginUsuarioRequestDTO req) {
        var user = usuarioRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Credenciais inválidas"));

        if (!passwordEncoder.matches(req.getSenha(), user.getHashSenha())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }

        loginUsuarioRepository.chamarProcedureRegistroLogin(user.getId());

        String token = jwtProvider.generateToken(user);

        return new LoginUsuarioResponseDTO(token, user);
    }
}