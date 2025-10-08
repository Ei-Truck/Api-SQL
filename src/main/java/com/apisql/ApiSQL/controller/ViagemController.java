package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.ViagemResponseDTO;

import com.apisql.ApiSQL.dto.view.RelatorioSimplesViagemDTO;
import com.apisql.ApiSQL.openapi.ViagemOpenApi;
import com.apisql.ApiSQL.service.ViagemService;
import com.apisql.ApiSQL.service.view.RelatorioSimplesViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

public class ViagemController implements ViagemOpenApi {

    private final ViagemService viagemService;
    private final RelatorioSimplesViagemService relatorioSimplesViagemService;

    @Autowired
    public ViagemController(ViagemService viagemService, RelatorioSimplesViagemService relatorioSimplesViagemService) {
        this.viagemService = viagemService;
        this.relatorioSimplesViagemService = relatorioSimplesViagemService;
    }

    @Override
    @GetMapping
    public List<ViagemResponseDTO> getAllViagens() {
        return viagemService.getAllViagens();
    }

    @Override
    @PostMapping
    public ResponseEntity<ViagemResponseDTO> createViagem(@RequestBody ViagemResponseDTO viagemResponseDTO) {
        ViagemResponseDTO newViagem = viagemService.createViagem(viagemResponseDTO);
        return new ResponseEntity<>(newViagem, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/relatorio")
    public List<RelatorioSimplesViagemDTO> getAllRelatorioViagem() {
        return relatorioSimplesViagemService.findAll();
    }
}
