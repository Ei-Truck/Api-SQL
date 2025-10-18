package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.ViagemDTO;

import com.apisql.ApiSQL.dto.view.OcorrenciaPorViagemDTO;
import com.apisql.ApiSQL.dto.view.RelatorioSimplesViagemDTO;
import com.apisql.ApiSQL.dto.view.VisaoBasicaViagemDTO;
import com.apisql.ApiSQL.openapi.ViagemOpenApi;
import com.apisql.ApiSQL.service.ViagemService;
import com.apisql.ApiSQL.service.view.OcorrenciaPorViagemService;
import com.apisql.ApiSQL.service.view.RelatorioSimplesViagemService;
import com.apisql.ApiSQL.service.view.VisaoBasicaViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

public class ViagemController implements ViagemOpenApi {

    private final ViagemService viagemService;
    private final RelatorioSimplesViagemService relatorioSimplesViagemService;
    private final OcorrenciaPorViagemService ocorrenciaPorViagemService;
    private final VisaoBasicaViagemService visaoBasicaViagemService;
    @Autowired
    public ViagemController(ViagemService viagemService, RelatorioSimplesViagemService relatorioSimplesViagemService, OcorrenciaPorViagemService ocorrenciaPorViagemService, VisaoBasicaViagemService visaoBasicaViagemService) {
        this.viagemService = viagemService;
        this.relatorioSimplesViagemService = relatorioSimplesViagemService;
        this.ocorrenciaPorViagemService = ocorrenciaPorViagemService;
        this.visaoBasicaViagemService = visaoBasicaViagemService;
    }

    @Override
    @GetMapping
    public List<ViagemDTO> getAllViagens() {
        return viagemService.getAllViagens();
    }

    @Override
    @PostMapping
    public ResponseEntity<ViagemDTO> createViagem(@RequestBody ViagemDTO viagemDTO) {
        ViagemDTO newViagem = viagemService.createViagem(viagemDTO);
        return new ResponseEntity<>(newViagem, HttpStatus.CREATED);
    }
    @GetMapping("/relatorio-simples")
    @Override
    public List<RelatorioSimplesViagemDTO> getAllRelatorioViagem() {
        return relatorioSimplesViagemService.findAll();
    }

    @GetMapping("ocorrencias")
    public List<OcorrenciaPorViagemDTO> getAllOcorrencias() {
        return ocorrenciaPorViagemService.findAll();
    }
    @GetMapping("visao-basica")
    public List<VisaoBasicaViagemDTO> getAllVisaoBasica() {
        return visaoBasicaViagemService.findALl();
    }
}
