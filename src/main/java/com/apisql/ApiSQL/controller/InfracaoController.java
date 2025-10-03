package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Infracao;
import com.apisql.ApiSQL.openapi.InfracaoOpenApi;
import com.apisql.ApiSQL.service.InfracaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InfracaoController implements InfracaoOpenApi {

    private final InfracaoService infracaoService;

    public InfracaoController(InfracaoService infracaoService) {
        this.infracaoService = infracaoService;
    }

    @Override
    public List<Infracao> getAll() {
        return infracaoService.findAll();
    }

    @Override
    public ResponseEntity<Infracao> getById(Integer id) {
        return infracaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public Infracao create(Infracao infracao) {
        return infracaoService.save(infracao);
    }

    @Override
    public ResponseEntity<Infracao> update(Integer id, Infracao infracao) {
        return infracaoService.findById(id)
                .map(existing -> {
                    existing.setIdViagem(infracao.getIdViagem());
                    existing.setIdMotorista(infracao.getIdMotorista());
                    existing.setDtHrEvento(infracao.getDtHrEvento());
                    existing.setIdTipoInfracao(infracao.getIdTipoInfracao());
                    existing.setLatitude(infracao.getLatitude());
                    existing.setLongitude(infracao.getLongitude());
                    existing.setVelocidadeKmh(infracao.getVelocidadeKmh());
                    existing.setTransactionMade(infracao.getTransactionMade());
                    existing.setUpdatedAt(infracao.getUpdatedAt());
                    existing.setIsInactive(infracao.getIsInactive());
                    return ResponseEntity.ok(infracaoService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        if (infracaoService.findById(id).isPresent()) {
            infracaoService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
