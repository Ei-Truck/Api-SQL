package com.apisql.ApiSQL.repository.view;

import com.apisql.ApiSQL.security.AuthorizationUser;
import com.apisql.ApiSQL.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TotalOcorrenciasRepository {

    @PersistenceContext
    private EntityManager em;

    private final AuthorizationUser authorizationUser;

    public TotalOcorrenciasRepository(AuthorizationUser authorizationUser) {
        this.authorizationUser = authorizationUser;
    }

    public List<Object[]> buscarTotalOcorrencias(HttpServletRequest request) {
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

        String sql = "SELECT mes, ano, id_unidade, id_segmento, id_localidade, total_ocorrencias " +
                "FROM vw_total_ocorrencias WHERE 1=1";

        if (idSegmento != null) sql += " AND id_segmento = " + idSegmento;
        if (idLocalidade != null) sql += " AND id_localidade = " + idLocalidade;
        if (idUnidade != null) sql += " AND id_unidade = " + idUnidade;

        sql += " ORDER BY ano DESC, mes DESC";

        return em.createNativeQuery(sql).getResultList();
    }
}