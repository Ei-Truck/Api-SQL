package com.apisql.ApiSQL.repository.view;

import com.apisql.ApiSQL.security.AuthorizationUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VariacaoMesPassadoPorMesAnoRepository {

    @PersistenceContext
    EntityManager em;

    private final AuthorizationUser authorizationUser;

    public VariacaoMesPassadoPorMesAnoRepository(AuthorizationUser authorizationUser) {
        this.authorizationUser = authorizationUser;
    }

    public List<Object[]> buscarVariacaoMesPassado(HttpServletRequest request) {
        String filtro = authorizationUser.gerarFiltroAutorizacao(request);
        String sql = "SELECT * FROM vw_variacao_mes_passado_por_mes_ano";
        if (filtro != null) {
            sql += " WHERE " + filtro;
        }
        return em.createNativeQuery(sql).getResultList();
    }
}