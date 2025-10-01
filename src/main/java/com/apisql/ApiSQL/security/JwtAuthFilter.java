package com.apisql.ApiSQL.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService; // O CustomUserDetailsService que você criou

    public JwtAuthFilter(JwtProvider jwtProvider, UserDetailsService userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // 1. Verifica se o cabeçalho Authorization existe e começa com "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Extrai o token JWT (depois de "Bearer ") e o email
        jwt = authHeader.substring(7);
        userEmail = jwtProvider.getEmailFromToken(jwt);

        // 3. Se o email foi encontrado no token E o usuário ainda não está autenticado no Spring Security:
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Carrega os detalhes do usuário (CustomUserDetailsService)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // 4. Valida a assinatura do token (JwtProvider)
            if (jwtProvider.validateToken(jwt)) {

                // Cria o token de autenticação do Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities() // As permissões do usuário
                );

                // Adiciona detalhes da requisição
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 5. Define o usuário como autenticado para esta requisição!
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 6. Continua a cadeia de filtros (permite que a requisição chegue ao Controller)
        filterChain.doFilter(request, response);
    }
}