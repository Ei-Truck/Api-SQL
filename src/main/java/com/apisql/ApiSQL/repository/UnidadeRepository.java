package com.apisql.ApiSQL.repository;

import com.apisql.ApiSQL.controller.UnidadeController;
import com.apisql.ApiSQL.model.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
}
