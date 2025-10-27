package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.ViagemRequestDTO;
import com.apisql.ApiSQL.dto.ViagemResponseDTO;
import com.apisql.ApiSQL.dto.view.RelatorioSimplesViagemDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Viagens", description = "Operações de gerenciamento de viagens de caminhões.")
public interface ViagemOpenApi {

    @Operation(summary = "Lista todas as viagens", description = "Retorna uma lista de todas as viagens.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso.")
    ResponseEntity<List<ViagemResponseDTO>> findAll();

    @Operation(summary = "Busca Viagem por ID", description = "Retorna os detalhes de uma viagem específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viagem encontrada com sucesso.",
                    content = @Content(schema = @Schema(implementation = ViagemResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Viagem não encontrada.")
    })
    ResponseEntity<ViagemResponseDTO> findById(@Parameter(description = "ID da Viagem") @PathVariable Integer id);

    @Operation(summary = "Cria uma nova Viagem", description = "Registra uma nova viagem, associando Caminhão, Usuário e Localidades.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Viagem criada com sucesso.",
                    content = @Content(schema = @Schema(implementation = ViagemResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos (ex: Caminhão/Usuário/Localidade inexistente).")
    })
    ResponseEntity<ViagemResponseDTO> save(@RequestBody ViagemRequestDTO dto);

    @Operation(summary = "Atualiza uma Viagem existente", description = "Atualiza o registro de uma Viagem pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viagem atualizada com sucesso.",
                    content = @Content(schema = @Schema(implementation = ViagemResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Viagem não encontrada."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização.")
    })
    ResponseEntity<ViagemResponseDTO> update(@Parameter(description = "ID da Viagem a ser atualizada") @PathVariable Integer id,
                                             @RequestBody ViagemRequestDTO dto);

    @Operation(summary = "Deleta uma Viagem", description = "Remove uma Viagem do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Viagem deletada com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "Viagem não encontrada para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID da Viagem a ser deletada") @PathVariable Integer id);
  
    @Operation(summary = "Listar relatório",
        description = "Lista um relatório simples da viagem",
        responses = {
                @ApiResponse(responseCode = "200", description = "Relatório obtido com sucesso"),
                @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        })
    List<RelatorioSimplesViagemDTO> getAllRelatorioViagem(HttpServletRequest request);
 
}