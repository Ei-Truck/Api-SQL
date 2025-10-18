package com.apisql.ApiSQL.repository;

import com.apisql.ApiSQL.model.LoginUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LoginUsuarioRepository extends JpaRepository<LoginUsuario, Integer> {
    List<LoginUsuario> findByUsuarioId(Integer idUsuario);

    @Transactional
    @Modifying
    @Query(value = "CALL prc_registrar_login_usuario(:idUsuario)", nativeQuery = true)
    void chamarProcedureRegistroLogin(@Param("idUsuario") Integer idUsuario);

}
