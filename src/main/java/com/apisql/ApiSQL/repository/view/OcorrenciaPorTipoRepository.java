package com.apisql.ApiSQL.repository.view;
import com.apisql.ApiSQL.security.AuthorizationUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OcorrenciaPorTipoRepository {
    @PersistenceContext
    EntityManager em;

    private final AuthorizationUser authorizationUser;

    public OcorrenciaPorTipoRepository(AuthorizationUser authorizationUser) {
        this.authorizationUser = authorizationUser;
    }


    public List<Object[]> buscarOcorrenciaPorTipo(HttpServletRequest request) {
        String filtro = authorizationUser.gerarFiltroAutorizacao(request);
        String sql = "SELECT * FROM vw_ocorrencias_por_tipo";
        if (filtro != null) {
            sql += " WHERE " + filtro;
        }
        return em.createNativeQuery(sql).getResultList();
    }
}
