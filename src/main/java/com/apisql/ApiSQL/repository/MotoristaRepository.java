package com.apisql.ApiSQL.repository;

import com.apisql.ApiSQL.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Integer> {

}
