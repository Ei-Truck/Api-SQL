package com.apisql.ApiSQL.repository.view;

import com.apisql.ApiSQL.model.Usuario;
import com.apisql.ApiSQL.security.AuthorizationUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotoristaPontuacaoMensalRepository {

    @PersistenceContext
    EntityManager em;

    private final AuthorizationUser authorizationUser;

    public MotoristaPontuacaoMensalRepository(AuthorizationUser authorizationUser) {
        this.authorizationUser = authorizationUser;
    }

    public List<Object[]> buscarPontuacaoMensalMotoristas(HttpServletRequest request) {
        Usuario usuario = authorizationUser.getUsuarioLogado(request);

        Integer idSegmento = null;
        Integer idLocalidade = null;
        Integer idUnidade = null;

        switch (usuario.getCargo().getNome().toUpperCase().replace(" ", "_")) {
            case "GERENTE_ANALISE":
            case "ADMINISTRADOR":
                break;
            case "ANALISTA_SEGMENTO":
                idSegmento = usuario.getUnidade().getSegmento().getId();
                break;
            case "ANALISTA_REGIONAL":
                idLocalidade = usuario.getUnidade().getLocalidade().getId();
                break;
            case "ANALISTA_LOCAL":
                idUnidade = usuario.getUnidade().getId();
                break;
            default:
                throw new RuntimeException("Cargo não reconhecido: " + usuario.getCargo().getNome());
        }

        String sql = "SELECT ranking_pontuacao, motorista, id_unidade, unidade, id_segmento, segmento, pontuacao_ultimo_mes " +
                "FROM vw_motorista_pontuacao_mensal WHERE 1=1";

        if (idSegmento != null) {
            sql += " AND id_segmento = " + idSegmento;
        }
        if (idLocalidade != null) {
            sql += " AND id_localidade = " + idLocalidade;
        }
        if (idUnidade != null) {
            sql += " AND id_unidade = " + idUnidade;
        }

        sql += " ORDER BY pontuacao_ultimo_mes DESC";

        return em.createNativeQuery(sql).getResultList();
    }
}
