package com.apisql.ApiSQL.repository.view;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OcorrenciasPorGravidadeRepository {

    @PersistenceContext
    EntityManager em;

    public List<Object[]> buscarOcorrenciasPorGravidade(){
        return em.createNativeQuery("SELECT total_ocorrencias, gravidade, mes, ano FROM vw_ocorrencias_por_gravidade").getResultList();
    }
}