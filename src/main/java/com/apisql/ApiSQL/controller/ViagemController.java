package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.ViagemDTO;
import com.apisql.ApiSQL.dto.view.RelatorioSimplesViagemDTO;
import com.apisql.ApiSQL.service.ViagemService;
import com.apisql.ApiSQL.service.view.RelatorioSimplesViagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/viagens")
@Tag(name = "Viagens", description = "Endpoints para gerenciamento de viagens")
public class ViagemController {

    private final ViagemService viagemService;
    private final RelatorioSimplesViagemService relatorioSimplesViagemService;

    @Autowired
    public ViagemController(ViagemService viagemService, RelatorioSimplesViagemService relatorioSimplesViagemService) {
        this.viagemService = viagemService;
        this.relatorioSimplesViagemService = relatorioSimplesViagemService;
    }

    @GetMapping
    @Operation(summary = "Obter todas as viagens",
            description = "Retorna uma lista de todas as viagens cadastradas no sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de viagens obtida com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public List<ViagemDTO> getAllViagens() {
        return viagemService.getAllViagens();
    }

    @PostMapping
    @Operation(summary = "Criar uma nova viagem",
            description = "Cria e salva uma nova viagem no banco de dados. Retorna a viagem criada.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Viagem criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
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
