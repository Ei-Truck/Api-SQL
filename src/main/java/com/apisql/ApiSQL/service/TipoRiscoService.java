package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.model.TipoRisco;
import com.apisql.ApiSQL.repository.TipoRiscoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoRiscoService {

    private final TipoRiscoRepository tipoRiscoRepository;

    public TipoRiscoService(TipoRiscoRepository tipoRiscoRepository) {
        this.tipoRiscoRepository = tipoRiscoRepository;
    }

    public List<TipoRisco> findAll() {
        return tipoRiscoRepository.findAll();
    }

    public Optional<TipoRisco> findById(Integer id) {
        return tipoRiscoRepository.findById(id);
    }

    public TipoRisco save(TipoRisco tipoRisco) {
        return tipoRiscoRepository.save(tipoRisco);
    }

    public void deleteById(Integer id) {
        tipoRiscoRepository.deleteById(id);
    }
}