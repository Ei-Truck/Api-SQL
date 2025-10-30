package com.apisql.ApiSQL.repository.view;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VisaoBasicaViagemRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Object[]> buscarVisaoBasicaViagem(Integer id) {
        return em.createNativeQuery("SELECT * FROM vw_visao_basica_viagem WHERE id_viagem = ?1")
                .setParameter(1, id)
                .getResultList();
    }
}
