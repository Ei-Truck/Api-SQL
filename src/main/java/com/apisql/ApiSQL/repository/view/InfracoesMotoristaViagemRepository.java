package com.apisql.ApiSQL.repository.view;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InfracoesMotoristaViagemRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Object[]> buscarMetricasGerais(Long idViagem) {
        return em.createNativeQuery("""
            SELECT 
                *
            FROM vw_infracoes_motoristas_viagens
            WHERE id_viagem = ?1
        """)
                .setParameter(1, idViagem)
                .getResultList();
    }
}