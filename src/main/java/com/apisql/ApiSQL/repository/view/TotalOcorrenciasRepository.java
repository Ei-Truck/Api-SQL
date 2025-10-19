package com.apisql.ApiSQL.repository.view;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TotalOcorrenciasRepository {

    @PersistenceContext
    EntityManager em;

    public List<Object[]> buscarTotalOcorrencias(){
        return em.createNativeQuery("SELECT total_ocorrencias, mes, ano FROM vw_total_ocorrencias").getResultList();
    }
}