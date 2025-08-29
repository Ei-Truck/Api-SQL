package com.apisql.ApiSQL.service;


import com.apisql.ApiSQL.model.Viagem;
import com.apisql.ApiSQL.repository.ViagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;

    public ViagemService(ViagemRepository viagemRepository) {
        this.viagemRepository = viagemRepository;
    }

    public List<Viagem> listarTodas() {
        return viagemRepository.findAll();
    }

    public Optional<Viagem> buscarPorId(Integer id) {
        return viagemRepository.findById(id);
    }

    public Viagem salvar(Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    public Viagem atualizar(Integer id, Viagem viagemAtualizada) {
        return viagemRepository.findById(id)
                .map(viagem -> {
                    viagem.setCaminhao(viagemAtualizada.getCaminhao());
                    viagem.setMotorista(viagemAtualizada.getMotorista());
                    viagem.setUsuario(viagemAtualizada.getUsuario());
                    viagem.setOrigem(viagemAtualizada.getOrigem());
                    viagem.setDestino(viagemAtualizada.getDestino());
                    viagem.setDtHrInicio(viagemAtualizada.getDtHrInicio());
                    viagem.setDtHrFim(viagemAtualizada.getDtHrFim());
                    viagem.setTratativa(viagemAtualizada.getTratativa());
                    viagem.setKmViagem(viagemAtualizada.getKmViagem());
                    viagem.setIsInactive(viagemAtualizada.getIsInactive());
                    return viagemRepository.save(viagem);
                })
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada com id: " + id));
    }

    public void deletar(Integer id) {
        viagemRepository.deleteById(id);
    }
}

