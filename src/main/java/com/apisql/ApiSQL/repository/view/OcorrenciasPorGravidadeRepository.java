package com.apisql.ApiSQL.repository.view;

import com.apisql.ApiSQL.security.AuthorizationUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OcorrenciasPorGravidadeRepository {

    @PersistenceContext
    EntityManager em;

    private final AuthorizationUser authorizationUser;

    public OcorrenciasPorGravidadeRepository(AuthorizationUser authorizationUser) {
        this.authorizationUser = authorizationUser;
    }


    public List<Object[]> buscarOcorrenciasPorGravidade(HttpServletRequest request) {
        String filtro = authorizationUser.gerarFiltroAutorizacao(request);
        String sql = "SELECT *, (SELECT l.estado FROM tb_localidade l WHERE id_localidade=l.id) FROM vw_ocorrencias_por_gravidade";
        if (filtro != null) {
            sql += " WHERE " + filtro;
        }
        return em.createNativeQuery(sql).getResultList();
    }
}