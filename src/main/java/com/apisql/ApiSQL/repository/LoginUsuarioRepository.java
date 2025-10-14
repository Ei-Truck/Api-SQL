package com.apisql.ApiSQL.repository;

import com.apisql.ApiSQL.model.LoginUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoginUsuarioRepository extends JpaRepository<LoginUsuario, Integer> {
    List<LoginUsuario> findByUsuarioId(Integer idUsuario);

}
