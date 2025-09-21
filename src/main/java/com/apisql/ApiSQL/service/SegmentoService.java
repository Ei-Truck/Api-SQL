package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.model.Segmento;
import com.apisql.ApiSQL.repository.SegmentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SegmentoService {

    private final SegmentoRepository segmentoRepository;

    public SegmentoService(SegmentoRepository segmentoRepository) {
        this.segmentoRepository = segmentoRepository;
    }

    public List<Segmento> findAll() {
        return segmentoRepository.findAll();
    }

    public Optional<Segmento> findById(Integer id) {
        return segmentoRepository.findById(id);
    }

    public Segmento save(Segmento segmento) {
        return segmentoRepository.save(segmento);
    }

    public void deleteById(Integer id) {
        segmentoRepository.deleteById(id);
    }
}
