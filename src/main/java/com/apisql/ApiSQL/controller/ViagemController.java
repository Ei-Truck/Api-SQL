package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.ViagemDTO;

import com.apisql.ApiSQL.dto.view.RelatorioSimplesViagemDTO;
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
    public List<ViagemDTO> getAllViagens() {
        return viagemService.getAllViagens();
    }

    @Override
    @PostMapping
    public ResponseEntity<ViagemDTO> createViagem(@RequestBody ViagemDTO viagemDTO) {
        ViagemDTO newViagem = viagemService.createViagem(viagemDTO);
        return new ResponseEntity<>(newViagem, HttpStatus.CREATED);
    }
    @Operation(summary = "Listar relatório",
            description = "Lista um relatório simples da viagem",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Relatório obtido com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    @GetMapping("/relatorio")
    public List<RelatorioSimplesViagemDTO> getAllRelatorioViagem() {
        return relatorioSimplesViagemService.findAll();
    }
}
