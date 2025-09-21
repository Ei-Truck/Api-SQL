package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.ViagemDTO;
import com.apisql.ApiSQL.model.Caminhao;
import com.apisql.ApiSQL.model.Viagem;
import com.apisql.ApiSQL.repository.CaminhaoRepository;
import com.apisql.ApiSQL.repository.ViagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;
    private final CaminhaoRepository caminhaoRepository;

    @Autowired
    public ViagemService(ViagemRepository viagemRepository, CaminhaoRepository caminhaoRepository) {
        this.viagemRepository = viagemRepository;
        this.caminhaoRepository = caminhaoRepository;
    }

    public List<ViagemDTO> getAllViagens() {
        List<Viagem> viagens = viagemRepository.findAll();
        return viagens.stream()
                .map(ViagemDTO::new)
                .collect(Collectors.toList());
    }

    public ViagemDTO createViagem(ViagemDTO viagemDTO) {
        Caminhao caminhao = caminhaoRepository.findById(viagemDTO.getCaminhao().getId())
                .orElseThrow(() -> new EntityNotFoundException("Caminhão não encontrado com o ID: " + viagemDTO.getCaminhao().getId()));

        Viagem viagem = new Viagem();
        viagem.setDtHrInicio(viagemDTO.getDtHrInicio());
        viagem.setDtHrFim(viagemDTO.getDtHrFim());
        viagem.setKmViagem(viagemDTO.getKmViagem());
        viagem.setCaminhao(caminhao);
        viagem.setWasAnalyzed(false);

        Viagem savedViagem = viagemRepository.save(viagem);

        return new ViagemDTO(savedViagem);
    }
}
