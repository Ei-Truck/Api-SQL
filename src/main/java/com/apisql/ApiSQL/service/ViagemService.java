package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.ViagemResponseDTO;
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

    public List<ViagemResponseDTO> getAllViagens() {
        List<Viagem> viagens = viagemRepository.findAll();
        return viagens.stream()
                .map(ViagemResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ViagemResponseDTO createViagem(ViagemResponseDTO viagemResponseDTO) {
        Caminhao caminhao = caminhaoRepository.findById(viagemResponseDTO.getCaminhao().getId())
                .orElseThrow(() -> new EntityNotFoundException("Caminhão não encontrado com o ID: " + viagemResponseDTO.getCaminhao().getId()));

        Viagem viagem = new Viagem();
        viagem.setDtHrInicio(viagemResponseDTO.getDtHrInicio());
        viagem.setDtHrFim(viagemResponseDTO.getDtHrFim());
        viagem.setKmViagem(viagemResponseDTO.getKmViagem());
        viagem.setCaminhao(caminhao);
        viagem.setWasAnalyzed(false);

        Viagem savedViagem = viagemRepository.save(viagem);

        return new ViagemResponseDTO(savedViagem);
    }
}
