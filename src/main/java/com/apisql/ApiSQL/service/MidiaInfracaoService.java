package com.apisql.ApiSQL.service;

import com.apisql.ApiSQL.dto.MidiaConcatenadaResponseDTO;
import com.apisql.ApiSQL.dto.MidiaInfracaoRequestDTO;
import com.apisql.ApiSQL.dto.MidiaInfracaoResponseDTO;
import com.apisql.ApiSQL.dto.ViagemResponseDTO;
import com.apisql.ApiSQL.exception.ResourceNotFoundException;
import com.apisql.ApiSQL.model.*;
import com.apisql.ApiSQL.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MidiaInfracaoService {

    private final MidiaInfracaoRepository repository;
    private final ViagemRepository viagemRepository;
    private final InfracaoRepository infracaoRepository;
    private final MotoristaRepository motoristaRepository;
    private final CaminhaoRepository caminhaoRepository;
    private final ObjectMapper objectMapper;
    private final S3Client s3Client;

    public MidiaInfracaoService(MidiaInfracaoRepository repository, ViagemRepository viagemRepository, InfracaoRepository infracaoRepository, MotoristaRepository motoristaRepository, ObjectMapper objectMapper, S3Client s3Client, CaminhaoRepository caminhaoRepository) {
        this.repository = repository;
        this.viagemRepository = viagemRepository;
        this.infracaoRepository = infracaoRepository;
        this.motoristaRepository = motoristaRepository;
        this.objectMapper = objectMapper;
        this.s3Client = S3Client.builder()
                .region(Region.of(System.getenv("AWS_REGION"))) // ou use @Value("${AWS_REGION}")
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(
                                System.getenv("AWS_ACCESS_KEY"), // ou @Value("${AWS_ACCESS_KEY_ID}")
                                System.getenv("AWS_SECRET_KEY")
                        )
                ))
                .build();
        this.caminhaoRepository = caminhaoRepository;
    }

    public List<MidiaInfracaoResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(m -> objectMapper.convertValue(m, MidiaInfracaoResponseDTO.class))
                .toList();
    }

    public MidiaInfracaoResponseDTO findById(Integer id) {
        MidiaInfracao midia = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mídia de Infração não encontrada com ID: " + id));
        return objectMapper.convertValue(midia, MidiaInfracaoResponseDTO.class);
    }

    @Transactional
    public MidiaInfracaoResponseDTO save(MidiaInfracaoRequestDTO dto) {
        Viagem viagem = viagemRepository.findById(dto.getIdViagem())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Viagem inválido."));
        Infracao infracao = infracaoRepository.findById(dto.getIdInfracao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Infração inválido."));
        Motorista motorista = motoristaRepository.findById(dto.getIdMotorista())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Motorista inválido."));

        MidiaInfracao midia = new MidiaInfracao();
        midia.setViagem(viagem);
        midia.setInfracao(infracao);
        midia.setMotorista(motorista);
        midia.setUrl(dto.getUrl());
        midia.setIsConcat(dto.getIsConcat());
        midia.setTransactionMade(dto.getTransactionMade());
        midia.setUpdatedAt(LocalDateTime.now());

        MidiaInfracao savedMidia = repository.save(midia);
        return objectMapper.convertValue(savedMidia, MidiaInfracaoResponseDTO.class);
    }

    @Transactional
    public MidiaInfracaoResponseDTO update(Integer id, MidiaInfracaoRequestDTO dto) {
        MidiaInfracao midia = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mídia de Infração com id:" + id + " não encontrada para atualização"));

        Viagem viagem = viagemRepository.findById(dto.getIdViagem())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Viagem inválido."));
        Infracao infracao = infracaoRepository.findById(dto.getIdInfracao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Infração inválido."));
        Motorista motorista = motoristaRepository.findById(dto.getIdMotorista())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Motorista inválido."));

        midia.setViagem(viagem);
        midia.setInfracao(infracao);
        midia.setMotorista(motorista);
        midia.setUrl(dto.getUrl());
        midia.setIsConcat(dto.getIsConcat());
        midia.setTransactionMade(dto.getTransactionMade());
        midia.setUpdatedAt(LocalDateTime.now());

        MidiaInfracao updatedMidia = repository.save(midia);
        return objectMapper.convertValue(updatedMidia, MidiaInfracaoResponseDTO.class);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Mídia de Infração com id:" + id + " não encontrada para exclusão");
        }
        repository.deleteById(id);
    }
    public String uploadFoto(Integer viagemId, String nomeMidia, MultipartFile arquivo) throws IOException {
        String key = "infracoes/" + viagemId + "/" + nomeMidia + ".mp4";
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket("eitruck    ")
                .key(key)
                .build();

        s3Client.putObject(request, RequestBody.fromBytes(arquivo.getBytes()));

        return "https://eitruck.s3.sa-east-1.amazonaws.com/" + key;
    }

    @Transactional
    public MidiaInfracaoResponseDTO inserirMidia(Integer motoristaId, MultipartFile arquivo) throws IOException {
        Motorista motorista = motoristaRepository.findById(motoristaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Motorista inválido."));
        Caminhao caminhao = caminhaoRepository.findById(1)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Motorista inválido."));
        Viagem novaViagem = new Viagem();

        Infracao infracao = new Infracao();

        infracao.setMotorista(motorista);
        infracao.setViagem(novaViagem);
        Infracao novaInfracao = infracaoRepository.save(infracao);
        Caminhao caminhaoMock = new Caminhao();
        caminhaoMock.setId(1);
        novaViagem.setCaminhao(caminhaoMock);

        Localidade destino = new Localidade();
        destino.setId(1);
        destino.setUfEstado("SP");
        novaViagem.setDestino(destino);
        novaViagem.setOrigem(destino);
        novaViagem.setKmViagem("100");
        novaViagem.setWasAnalyzed(false);
        novaViagem.setDtHrInicio(LocalDateTime.now());
        novaViagem.setDtHrFim(LocalDateTime.now().plusMinutes(5));
        novaViagem.setCaminhao(caminhao);
        Viagem viagemSalva = viagemRepository.save(novaViagem);

        MidiaInfracao midia = new MidiaInfracao();
        midia.setMotorista(motorista);
        midia.setViagem(viagemSalva);
        midia.setInfracao(infracao);
        midia.setTransactionMade("FALSE");
        midia.setUpdatedAt(LocalDateTime.now());

        String nomeMidia = "midia_" + motoristaId + "_" + viagemSalva.getId() + "_" + novaInfracao.getId() + "_" + System.currentTimeMillis();
        String url = uploadFoto(viagemSalva.getId(), nomeMidia, arquivo);
        midia.setUrl(url);

        MidiaInfracao savedMidia = repository.save(midia);
        return objectMapper.convertValue(savedMidia, MidiaInfracaoResponseDTO.class);
    }

}