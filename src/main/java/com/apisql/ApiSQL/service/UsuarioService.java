package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.UsuarioResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.Usuario;
import com.apisql.ApiSQL.repository.CargoRepository;
import com.apisql.ApiSQL.repository.UnidadeRepository;
import com.apisql.ApiSQL.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDateTime;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UnidadeRepository unidadeRepository;
    private final CargoRepository cargoRepository;
    private final ObjectMapper objectMapper;
    private final S3Client s3Client;
    private final PasswordEncoder passwordEncoder;


    public UsuarioService(UsuarioRepository usuarioRepository, ObjectMapper objectMapper, UnidadeRepository unidadeRepository, CargoRepository cargoRepository, S3Client s3Client, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.objectMapper = objectMapper;
        this.unidadeRepository = unidadeRepository;
        this.cargoRepository = cargoRepository;
        this.s3Client = s3Client;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponseDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(u -> objectMapper.convertValue(u, UsuarioResponseDTO.class))
                .toList();
    }

    public UsuarioResponseDTO findById(Integer id) {
        Optional<Usuario> response = usuarioRepository.findById(id);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), UsuarioResponseDTO.class);
        }
        throw new ResourceNotFoundException("Usuário não encontrado com ID: " + id);
    }

    public UsuarioResponseDTO findByTelefone(String telefone) {
        Optional<Usuario> response = usuarioRepository.findByTelefone(telefone);
        if (response.isPresent()) {
            return objectMapper.convertValue(response.get(), UsuarioResponseDTO.class);
        }
        throw new ResourceNotFoundException("Usuário não encontrado com telefone: " + telefone);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário com id:" + id + " não encontrado para exclusão");
        }
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public UsuarioResponseDTO atualizarSenha(Integer usuarioId, String novaSenha) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + usuarioId));

        String senhaCriptografada = passwordEncoder.encode(novaSenha);
        usuario.setHashSenha(senhaCriptografada);

        return  objectMapper.convertValue(usuarioRepository.save(usuario), UsuarioResponseDTO.class);
    }

    public String uploadFoto(Integer usuarioId, Path arquivo) {
        String key = "perfil/" + usuarioId + "/profile_" + usuarioId + ".jpg";

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket("eitruck")
                .key(key)
                .build();

        PutObjectResponse response = s3Client.putObject(request, arquivo);

        return "https://eitruck.s3.sa-east-1.amazonaws.com/" + key;
    }

    public UsuarioResponseDTO atualizarFoto(Integer usuarioId, Path arquivo) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + usuarioId + " para atualizar foto."));

        String url = uploadFoto(usuarioId, arquivo);
        usuario.setUrlFoto(url);

        return objectMapper.convertValue(usuarioRepository.save(usuario), UsuarioResponseDTO.class);
    }

}
