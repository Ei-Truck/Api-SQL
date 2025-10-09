package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.ViagemRequestDTO;
import com.apisql.ApiSQL.dto.ViagemResponseDTO;
import com.apisql.ApiSQL.model.Caminhao;
import com.apisql.ApiSQL.model.Localidade;
import com.apisql.ApiSQL.model.Usuario;
import com.apisql.ApiSQL.model.Viagem;
import com.apisql.ApiSQL.repository.CaminhaoRepository;
import com.apisql.ApiSQL.repository.LocalidadeRepository;
import com.apisql.ApiSQL.repository.UsuarioRepository;
import com.apisql.ApiSQL.repository.ViagemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;
    private final CaminhaoRepository caminhaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LocalidadeRepository localidadeRepository;
    private final ObjectMapper objectMapper;

    public ViagemService(ViagemRepository viagemRepository, ObjectMapper objectMapper, CaminhaoRepository caminhaoRepository, UsuarioRepository usuarioRepository, LocalidadeRepository localidadeRepository) {
        this.viagemRepository = viagemRepository;
        this.objectMapper = objectMapper;
        this.caminhaoRepository = caminhaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.localidadeRepository = localidadeRepository;
    }

    public List<ViagemResponseDTO> findAll() {
        List<Viagem> viagens = viagemRepository.findAll();
        return viagens.stream()
                .map(v -> objectMapper.convertValue(v, ViagemResponseDTO.class))
                .toList();
    }

    public ViagemResponseDTO findById(Integer id) {
        Optional<Viagem> response = viagemRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), ViagemResponseDTO.class);
        }
        throw new EntityNotFoundException("Viagem não encontrada com ID: " + id);
    }

    @Transactional
    public ViagemResponseDTO save(ViagemRequestDTO dto) {
        Caminhao caminhao = caminhaoRepository.findById(dto.getIdCaminhao())
                .orElseThrow(() -> new EntityNotFoundException("Caminhao não encontrado com ID: " + dto.getIdCaminhao()));

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + dto.getIdUsuario()));

        Localidade origem = localidadeRepository.findById(dto.getIdOrigem())
                .orElseThrow(() -> new EntityNotFoundException("Localidade de Origem não encontrada com ID: " + dto.getIdOrigem()));

        Localidade destino = localidadeRepository.findById(dto.getIdDestino())
                .orElseThrow(() -> new EntityNotFoundException("Localidade de Destino não encontrada com ID: " + dto.getIdDestino()));

        Viagem viagem = new Viagem();
        viagem.setCaminhao(caminhao);
        viagem.setUsuario(usuario);
        viagem.setOrigem(origem);
        viagem.setDestino(destino);
        viagem.setDtHrInicio(dto.getDtHrInicio());
        viagem.setDtHrFim(dto.getDtHrFim());
        viagem.setKmViagem(dto.getKmViagem());
        if (dto.getWasAnalyzed() != null) {
            viagem.setWasAnalyzed(dto.getWasAnalyzed());
        }

        Viagem savedViagem = viagemRepository.save(viagem);
        return objectMapper.convertValue(savedViagem, ViagemResponseDTO.class);
    }

    @Transactional
    public ViagemResponseDTO update(Integer id, ViagemRequestDTO dto) {
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem com id:" + id + " não encontrada"));

        Caminhao caminhao = caminhaoRepository.findById(dto.getIdCaminhao())
                .orElseThrow(() -> new EntityNotFoundException("Caminhao não encontrado com ID: " + dto.getIdCaminhao()));

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + dto.getIdUsuario()));

        Localidade origem = localidadeRepository.findById(dto.getIdOrigem())
                .orElseThrow(() -> new EntityNotFoundException("Localidade de Origem não encontrada com ID: " + dto.getIdOrigem()));

        Localidade destino = localidadeRepository.findById(dto.getIdDestino())
                .orElseThrow(() -> new EntityNotFoundException("Localidade de Destino não encontrada com ID: " + dto.getIdDestino()));

        viagem.setCaminhao(caminhao);
        viagem.setUsuario(usuario);
        viagem.setOrigem(origem);
        viagem.setDestino(destino);
        viagem.setDtHrInicio(dto.getDtHrInicio());
        viagem.setDtHrFim(dto.getDtHrFim());
        viagem.setKmViagem(dto.getKmViagem());
        if (dto.getWasAnalyzed() != null) {
            viagem.setWasAnalyzed(dto.getWasAnalyzed());
        }
        viagem.setUpdatedAt(LocalDateTime.now());

        Viagem updatedViagem = viagemRepository.save(viagem);
        return objectMapper.convertValue(updatedViagem, ViagemResponseDTO.class);
    }

    public void deleteById(Integer id) {
        if (!viagemRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem com id:" + id + " não encontrada para exclusão");
        }
        viagemRepository.deleteById(id);
    }
}