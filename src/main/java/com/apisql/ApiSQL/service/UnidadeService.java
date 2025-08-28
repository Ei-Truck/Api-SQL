package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.model.Unidade;
import com.apisql.ApiSQL.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;

    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public List<Unidade> findAll() {
        return unidadeRepository.findAll();
    }

    public Optional<Unidade> findById(Integer id) {
        return unidadeRepository.findById(id);
    }

    public Unidade save(Unidade unidade) {
        return unidadeRepository.save(unidade);
    }

    public void deleteById(Integer id) {
        unidadeRepository.deleteById(id);
    }
}