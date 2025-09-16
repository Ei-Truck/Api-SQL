package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.model.TipoGravidade;
import com.apisql.ApiSQL.repository.TipoGravidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoGravidadeService {
    private final TipoGravidadeRepository tipoGravidadeRepository;

    public TipoGravidadeService(TipoGravidadeRepository tipoGravidadeRepository) {
        this.tipoGravidadeRepository = tipoGravidadeRepository;
    }

    public List<TipoGravidade> findAll() {
        return tipoGravidadeRepository.findAll();
    }

    public Optional<TipoGravidade> findById(Integer id) {
        return tipoGravidadeRepository.findById(id);
    }

    public TipoGravidade save(TipoGravidade tipoGravidade) {
        return tipoGravidadeRepository.save(tipoGravidade);
    }

    public void deleteById(Integer id) {
        tipoGravidadeRepository.deleteById(id);
    }
}
