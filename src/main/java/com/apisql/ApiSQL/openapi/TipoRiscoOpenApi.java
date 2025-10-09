package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.TipoRiscoRequestDTO;
import com.apisql.ApiSQL.dto.TipoRiscoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Tipos de Risco", description = "Operações de gerenciamento de tipos de risco para motoristas.")
public interface TipoRiscoOpenApi {

    @Operation(summary = "Lista todos os tipos de risco", description = "Retorna uma lista de todos os tipos de risco.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso.")
    ResponseEntity<List<TipoRiscoResponseDTO>> findAll();

    @Operation(summary = "Busca TipoRisco por ID", description = "Retorna os detalhes de um tipo de risco específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoRisco encontrado com sucesso.",
                    content = @Content(schema = @Schema(implementation = TipoRiscoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "TipoRisco não encontrado.")
    })
    ResponseEntity<TipoRiscoResponseDTO> findById(@Parameter(description = "ID do TipoRisco") @PathVariable Integer id);

    @Operation(summary = "Cria um novo TipoRisco", description = "Registra um novo TipoRisco.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TipoRisco criado com sucesso.",
                    content = @Content(schema = @Schema(implementation = TipoRiscoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos (ex: nome duplicado).")
    })
    ResponseEntity<TipoRiscoResponseDTO> save(@RequestBody TipoRiscoRequestDTO dto);

    @Operation(summary = "Atualiza um TipoRisco existente", description = "Atualiza o registro de um TipoRisco pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoRisco atualizado com sucesso.",
                    content = @Content(schema = @Schema(implementation = TipoRiscoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "TipoRisco não encontrado."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização.")
    })
    ResponseEntity<TipoRiscoResponseDTO> update(@Parameter(description = "ID do TipoRisco a ser atualizado") @PathVariable Integer id,
                                                @RequestBody TipoRiscoRequestDTO dto);

    @Operation(summary = "Deleta um TipoRisco", description = "Remove um TipoRisco do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoRisco deletado com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "TipoRisco não encontrado para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID do TipoRisco a ser deletado") @PathVariable Integer id);
}