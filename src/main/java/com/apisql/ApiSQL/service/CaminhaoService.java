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

    public List<Caminhao> listarTodos() {
        return caminhaoRepository.findAll();
    }

    public Optional<Caminhao> buscarPorId(Integer id) {
        return caminhaoRepository.findById(id);
    }

    public Caminhao salvar(Caminhao caminhao) {
        return caminhaoRepository.save(caminhao);
    }

    public Caminhao atualizar(Integer id, Caminhao caminhaoAtualizado) {
        return caminhaoRepository.findById(id)
                .map(caminhao -> {
                    caminhao.setChassi(caminhaoAtualizado.getChassi());
                    caminhao.setSegmento(caminhaoAtualizado.getSegmento());
                    caminhao.setUnidade(caminhaoAtualizado.getUnidade());
                    caminhao.setPlaca(caminhaoAtualizado.getPlaca());
                    caminhao.setModelo(caminhaoAtualizado.getModelo());
                    caminhao.setAnoFabricacao(caminhaoAtualizado.getAnoFabricacao());
                    caminhao.setNumeroFrota(caminhaoAtualizado.getNumeroFrota());
                    caminhao.setTransactionMade(caminhaoAtualizado.getTransactionMade());
                    caminhao.setUpdatedAt(caminhaoAtualizado.getUpdatedAt());
                    caminhao.setIsInactive(caminhaoAtualizado.getIsInactive());
                    return caminhaoRepository.save(caminhao);
                })
                .orElseThrow(() -> new RuntimeException("Caminhão não encontrado com id: " + id));
    }

    public void deletar(Integer id) {
        caminhaoRepository.deleteById(id);
    }
}
