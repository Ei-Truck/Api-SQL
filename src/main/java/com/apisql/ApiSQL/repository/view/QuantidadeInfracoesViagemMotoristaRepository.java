package com.apisql.ApiSQL.repository.view;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuantidadeInfracoesViagemMotoristaRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Object[]> buscarInfracoesMotorista(Integer id) {
        return em.createNativeQuery("SELECT * FROM vw_qntd_infracoes_viagem_motorista WHERE id_viagem = ?1")
                .setParameter(1, id)
                .getResultList();
    }
}