package com.apisql.ApiSQL.repository;

import com.apisql.ApiSQL.model.Segmento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentoRepository extends JpaRepository<Segmento, Long> {
}
