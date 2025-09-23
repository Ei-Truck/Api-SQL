package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.model.Infracao;
import com.apisql.ApiSQL.repository.InfracaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfracaoService {

    private final InfracaoRepository infracaoRepository;

    public InfracaoService(InfracaoRepository infracaoRepository) {
        this.infracaoRepository = infracaoRepository;
    }

    public List<Infracao> findAll() {
        return infracaoRepository.findAll();
    }

    public Optional<Infracao> findById(Integer id) {
        return infracaoRepository.findById(id);
    }

    public Infracao save(Infracao infracao) {
        return infracaoRepository.save(infracao);
    }

    public void delete(Integer id) {
        infracaoRepository.deleteById(id);
    }
}

