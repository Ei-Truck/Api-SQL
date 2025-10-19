package com.apisql.ApiSQL.repository.view;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotoristaQuantidadeInfracoesRepository {

    @PersistenceContext
    EntityManager em;

    public List<Object[]> buscarQuantidadeInfracoesMotorista(){
        return em.createNativeQuery("SELECT motorista, quantidade_infracoes, mes, ano FROM vw_motorista_quantidade_infracoes").getResultList();
    }
}