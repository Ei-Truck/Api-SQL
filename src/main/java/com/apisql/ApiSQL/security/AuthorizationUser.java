package com.apisql.ApiSQL.security;

import com.apisql.ApiSQL.model.enums.CargoTipo;
import com.apisql.ApiSQL.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationUser {

    private final JwtProvider jwtProvider;

    public AuthorizationUser(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    public Usuario getUsuarioLogado(HttpServletRequest request) {
        return jwtProvider.getUsuarioLogado(request);
    }

    public String gerarFiltroAutorizacao(HttpServletRequest request) {
        Usuario usuario = getUsuarioLogado(request);
        if (usuario == null) {
            throw new RuntimeException("Usuário não autenticado");
        }
        CargoTipo cargoTipo = CargoTipo.valueOf(usuario.getCargo().getNome().toUpperCase().replace(" ", "_"));
        switch (cargoTipo) {
            case ADMINISTRADOR, GERENTE_ANALISE:
                return null;
            case ANALISTA_SEGMENTO:
                return "id_segmento = " + usuario.getUnidade().getSegmento().getId();
            case ANALISTA_REGIONAL:
                return "id_localidade = " + usuario.getUnidade().getLocalidade().getId();
            case ANALISTA_LOCAL:
                return "id_unidade = " + usuario.getUnidade().getId();
            default:
                throw new RuntimeException("Cargo não reconhecido: " + usuario.getCargo().getNome());
        }
    }
}
