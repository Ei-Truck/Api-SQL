package com.apisql.ApiSQL.repository.view;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotoristaPontuacaoMensalRepository {

    @PersistenceContext
    EntityManager em;

    public List<Object[]> buscarPontuacaoMensalMotoristas(){
        return em.createNativeQuery("SELECT ranking_pontuacao, motorista, id_unidade, unidade, id_segmento, segmento, pontuacao_ultimo_mes FROM vw_motorista_pontuacao_mensal").getResultList();
    }
}