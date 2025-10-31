package com.apisql.ApiSQL.repository;

import com.apisql.ApiSQL.dto.LocalidadeResponseDTO;
import com.apisql.ApiSQL.model.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocalidadeRepository extends JpaRepository<Localidade, Integer> {

    @Query("""
    SELECT new com.apisql.ApiSQL.dto.LocalidadeResponseDTO(
            MIN(l.id), l.ufEstado
        )
    FROM Localidade l
    GROUP BY l.ufEstado
    """)
    List<LocalidadeResponseDTO> findLocalidadesUnicas();


}
