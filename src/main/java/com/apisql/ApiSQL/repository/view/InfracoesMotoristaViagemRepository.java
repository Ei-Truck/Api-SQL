package com.apisql.ApiSQL.repository.view;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InfracoesMotoristaViagemRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Object[]> buscarMetricasGerais(Integer idViagem) {
        return em.createNativeQuery("SELECT id_motorista, id_viagem, id_unidade, id_localidade, nome_motorista, url_midia_concatenada, risco_motorista, quantidade_infracao FROM vw_infracoes_motoristas_viagens WHERE id_viagem = ?1")
                .setParameter(1, idViagem)
                .getResultList();
    }
}