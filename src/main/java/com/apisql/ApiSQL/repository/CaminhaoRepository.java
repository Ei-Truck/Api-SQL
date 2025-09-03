package com.apisql.ApiSQL.repository;

import com.apisql.ApiSQL.model.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaminhaoRepository extends JpaRepository<Caminhao, Integer> {
}
