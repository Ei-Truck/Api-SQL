package com.apisql.ApiSQL.security;

import com.apisql.ApiSQL.model.Usuario;
import com.apisql.ApiSQL.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Método exigido pelo Spring Security para carregar o usuário pelo email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        // Mapeia seu objeto Usuario para o objeto UserDetails do Spring Security
        return new User(
                usuario.getEmail(),
                usuario.getHashSenha(), // Retorna o Hash da senha!
                Collections.emptyList() // Lista vazia para permissões/roles
        );
    }
}