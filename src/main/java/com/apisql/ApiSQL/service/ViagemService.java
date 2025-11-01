package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.ViagemPatchDTO;
import com.apisql.ApiSQL.dto.ViagemRequestDTO;
import com.apisql.ApiSQL.dto.ViagemResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.Viagem;
import com.apisql.ApiSQL.model.Caminhao;
import com.apisql.ApiSQL.model.Usuario;
import com.apisql.ApiSQL.model.Localidade;
import com.apisql.ApiSQL.repository.ViagemRepository;
import com.apisql.ApiSQL.repository.CaminhaoRepository;
import com.apisql.ApiSQL.repository.UsuarioRepository;
import com.apisql.ApiSQL.repository.LocalidadeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public ViagemService(ViagemRepository viagemRepository, CaminhaoRepository caminhaoRepository, UsuarioRepository usuarioRepository, LocalidadeRepository localidadeRepository, ObjectMapper objectMapper) {
        this.viagemRepository = viagemRepository;
        this.caminhaoRepository = caminhaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.localidadeRepository = localidadeRepository;
        this.objectMapper = objectMapper;
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
        throw new ResourceNotFoundException("Viagem não encontrada com ID: " + id);
    }

    @Transactional
    public ViagemResponseDTO save(ViagemRequestDTO dto) {
        Caminhao caminhao = caminhaoRepository.findById(dto.getIdCaminhao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Caminhão inválido."));
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Usuário inválido."));
        Localidade origem = localidadeRepository.findById(dto.getIdOrigem())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Localidade Origem inválido."));
        Localidade destino = localidadeRepository.findById(dto.getIdDestino())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Localidade Destino inválido."));

        Viagem viagem = new Viagem();
        viagem.setCaminhao(caminhao);
        viagem.setUsuario(usuario);
        viagem.setOrigem(origem);
        viagem.setDestino(destino);
        viagem.setDtHrInicio(dto.getDtHrInicio());
        viagem.setDtHrFim(dto.getDtHrFim());
        viagem.setKmViagem(dto.getKmViagem());

        Viagem savedViagem = viagemRepository.save(viagem);
        return objectMapper.convertValue(savedViagem, ViagemResponseDTO.class);
    }

    @Transactional
    public ViagemResponseDTO update(Integer id, ViagemRequestDTO dto) {
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viagem com id:" + id + " não encontrada para atualização"));

        Caminhao caminhao = caminhaoRepository.findById(dto.getIdCaminhao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Caminhão inválido."));
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Usuário inválido."));
        Localidade origem = localidadeRepository.findById(dto.getIdOrigem())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Localidade Origem inválido."));
        Localidade destino = localidadeRepository.findById(dto.getIdDestino())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Localidade Destino inválido."));

        viagem.setCaminhao(caminhao);
        viagem.setUsuario(usuario);
        viagem.setOrigem(origem);
        viagem.setDestino(destino);
        viagem.setDtHrInicio(dto.getDtHrInicio());
        viagem.setDtHrFim(dto.getDtHrFim());
        viagem.setKmViagem(dto.getKmViagem());
        viagem.setUpdatedAt(LocalDateTime.now());

        Viagem updatedViagem = viagemRepository.save(viagem);
        return objectMapper.convertValue(updatedViagem, ViagemResponseDTO.class);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!viagemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Viagem com id:" + id + " não encontrada para exclusão");
        }
        viagemRepository.deleteById(id);
    }

    @Transactional
    public ViagemResponseDTO checkAnalyzed(Integer id, ViagemPatchDTO dto) {
        Viagem viagemExistente = viagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viagem com id:" + id + " não encontrada para exclusão"));

        if (dto.getWasAnalyzed() != null) {
            viagemExistente.setWasAnalyzed(dto.getWasAnalyzed());
            viagemRepository.save(viagemExistente);
        }
        return objectMapper.convertValue(viagemExistente, ViagemResponseDTO.class);
    }
}