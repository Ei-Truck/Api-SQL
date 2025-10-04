package com.apisql.ApiSQL.repository.view;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OcorrenciaPorViagemRepository {

    @PersistenceContext
    EntityManager em;

    public List<Object[]> buscarOcorrenciaPorViagem(){
        return em.createNativeQuery("SELECT * FROM vw_ocorrencia_por_viagem").getResultList();
    }
}
