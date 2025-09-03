package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.model.Localidade;
import com.apisql.ApiSQL.repository.LocalidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalidadeService {

    private final LocalidadeRepository localidadeRepository;

    public LocalidadeService(LocalidadeRepository localidadeRepository) {
        this.localidadeRepository = localidadeRepository;
    }

    public List<Localidade> findAll() {
        return localidadeRepository.findAll();
    }

    public Optional<Localidade> findById(Integer id) {
        return localidadeRepository.findById(id);
    }

    public Localidade save(Localidade localidade) {
        return localidadeRepository.save(localidade);
    }

    public void deleteById(Integer id) {
        localidadeRepository.deleteById(id);
    }
}
