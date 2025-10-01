package com.apisql.ApiSQL.repository.view;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class RelatorioSimplesViagemRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Object[]> buscarRelatorioSimplesViagem() {
        return em.createNativeQuery("SELECT * FROM vw_relatorio_simples_viagem").getResultList();
    }
}
