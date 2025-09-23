package com.apisql.ApiSQL.repository;

import com.apisql.ApiSQL.model.Infracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfracaoRepository extends JpaRepository<Infracao, Integer> {
}
