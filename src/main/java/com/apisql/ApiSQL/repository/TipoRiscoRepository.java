package com.apisql.ApiSQL.repository;

import com.apisql.ApiSQL.model.TipoRisco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRiscoRepository extends JpaRepository<TipoRisco, Long> {
}
