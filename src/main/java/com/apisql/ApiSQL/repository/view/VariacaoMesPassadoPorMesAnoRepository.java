package com.apisql.ApiSQL.repository.view;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VariacaoMesPassadoPorMesAnoRepository {

    @PersistenceContext
    EntityManager em;

    public List<Object[]> buscarVariacaoMesPassado(){
        return em.createNativeQuery("SELECT mes, ano, infracoes_mes_atual, infracoes_mes_passado, variacao FROM vw_variacao_mes_passado_por_mes_ano").getResultList();
    }
}