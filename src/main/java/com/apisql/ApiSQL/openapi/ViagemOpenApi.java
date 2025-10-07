package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.ViagemResponseDTO;
import com.apisql.ApiSQL.dto.view.RelatorioSimplesViagemDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.*; // Adicionado para incluir todos

@RequestMapping("/viagens")
@Tag(name = "Viagens", description = "Endpoints para gerenciamento de viagens")
public interface ViagemOpenApi {
    @Operation(summary = "Obter todas as viagens",
            description = "Retorna uma lista de todas as viagens cadastradas no sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de viagens obtida com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    @GetMapping // ADICIONADO
    List<ViagemResponseDTO> getAllViagens();

    @Operation(summary = "Criar uma nova viagem",
            description = "Cria e salva uma nova viagem no banco de dados. Retorna a viagem criada.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Viagem criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    @PostMapping // ADICIONADO
    ResponseEntity<ViagemResponseDTO> createViagem(@RequestBody ViagemResponseDTO viagemResponseDTO); // ADICIONADO @RequestBody

    @Operation(summary = "Listar relatório",
            description = "Lista um relatório simples da viagem",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Relatório obtido com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    @GetMapping("/relatorio") // ADICIONADO (Assumindo um subcaminho para o relatório)
    List<RelatorioSimplesViagemDTO> getAllRelatorioViagem();
}