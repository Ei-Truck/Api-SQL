package com.apisql.ApiSQL.repository.view;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RelatorioSemanalInfracoesRepository {

    @PersistenceContext
    EntityManager em;

    public List<Object[]> buscarRelatorioSamanalInfracoes(){
        return em.createNativeQuery("SELECT dia_semana, total_infracoes FROM vw_relatorio_semanal_infracoes").getResultList();
    }
}
