package com.apisql.ApiSQL.repository;

import com.apisql.ApiSQL.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RegistroRepository extends JpaRepository<Registro, Integer> {

    @Query("SELECT r FROM Registro r WHERE r.viagem.id = :idViagem AND r.motorista.id = :idMotorista")
    Optional<Registro> findByIdViagemAndIdMotorista(Integer idViagem, Integer idMotorista);
}