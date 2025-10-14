package com.apisql.ApiSQL.repository.view;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OcorrenciaPorTipoRepository {
    @PersistenceContext
    EntityManager em;

    public List<Object[]> buscarOcorrenciaPorTipo(){
        return em.createNativeQuery("SELECT * FROM vw_ocorrencias_por_tipo").getResultList();
    }
}
