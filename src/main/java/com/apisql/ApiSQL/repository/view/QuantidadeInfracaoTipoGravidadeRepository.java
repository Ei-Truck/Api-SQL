package com.apisql.ApiSQL.repository.view;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuantidadeInfracaoTipoGravidadeRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Object[]> buscarQntdInfracoes(Integer idViagem) {
        return em.createNativeQuery("SELECT tipo_leve, tipo_media, tipo_grave, tipo_gravissima FROM vw_quantidade_infracao_tipo_gravidade WHERE id_viagem = ?1")
                .setParameter(1, idViagem)
                .getResultList();
    }
}