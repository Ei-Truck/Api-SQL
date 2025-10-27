package com.apisql.ApiSQL.repository.view;

import com.apisql.ApiSQL.security.AuthorizationUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RelatorioSimplesViagemRepository {

    @PersistenceContext
    private EntityManager em;

    private final AuthorizationUser authorizationUser;

    public RelatorioSimplesViagemRepository(AuthorizationUser authorizationUser) {
        this.authorizationUser = authorizationUser;
    }

    public List<Object[]> buscarRelatorioSimplesViagem(HttpServletRequest request) {
        String filtro = authorizationUser.gerarFiltroAutorizacao(request);
        String sql = "SELECT * FROM vw_relatorio_simples_viagem";
        if (filtro != null) {
            sql += " WHERE " + filtro;
        }
        return em.createNativeQuery(sql).getResultList();
    }
}
