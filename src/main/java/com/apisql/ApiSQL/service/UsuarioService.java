package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.model.Usuario;
import com.apisql.ApiSQL.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final S3Client s3Client;


    public UsuarioService(UsuarioRepository usuarioRepository, S3Client s3Client) {
        this.usuarioRepository = usuarioRepository;
        this.s3Client = s3Client;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deletar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public String uploadFoto(Integer usuarioId, Path arquivo) {
        String key = "perfil/" + usuarioId + "/" + arquivo.getFileName().toString();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket("eitruck")
                .key(key)
                .build();

        PutObjectResponse response = s3Client.putObject(request, arquivo);

        return "https://eitruck.s3.sa-east-1.amazonaws.com/" + key;
    }

    public Usuario atualizarFoto(Integer usuarioId, Path arquivo) {

        // COLOCAR EXCEÇÃO JOAO
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String url = uploadFoto(usuarioId, arquivo);
        usuario.setUrlFoto(url);

        return usuarioRepository.save(usuario);
    }

}
