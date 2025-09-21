package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.model.Caminhao;
import com.apisql.ApiSQL.repository.CaminhaoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CaminhaoService {

    private final CaminhaoRepository caminhaoRepository;

    public CaminhaoService(CaminhaoRepository caminhaoRepository) {
        this.caminhaoRepository = caminhaoRepository;
    }

    public List<Caminhao> findAll() {
        return caminhaoRepository.findAll();
    }

    public Optional<Caminhao> findById(Integer id) {
        return caminhaoRepository.findById(id);
    }

    public Caminhao save(Caminhao caminhao) {
        return caminhaoRepository.save(caminhao);
    }

    public void deleteById(Integer id) {
        caminhaoRepository.deleteById(id);
    }
}
