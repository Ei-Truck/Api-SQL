package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.model.TipoInfracao;
import com.apisql.ApiSQL.repository.TipoInfracaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoInfracaoService {

    private final TipoInfracaoRepository repository;

    public TipoInfracaoService(TipoInfracaoRepository repository) {
        this.repository = repository;
    }

    public List<TipoInfracao> findAll() {
        return repository.findAll();
    }

    public Optional<TipoInfracao> findById(Integer id) {
        return repository.findById(id);
    }

    public TipoInfracao save(TipoInfracao tipoInfracao) {
        return repository.save(tipoInfracao);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
