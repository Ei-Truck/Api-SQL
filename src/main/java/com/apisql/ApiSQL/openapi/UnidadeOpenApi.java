package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.UnidadeRequestDTO;
import com.apisql.ApiSQL.dto.UnidadeResponseDTO;
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

@Tag(name = "Unidades", description = "Operações de gerenciamento de unidades.")
public interface UnidadeOpenApi {

    @Operation(summary = "Lista todas as unidades", description = "Retorna uma lista de todas as unidades.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso.")
    ResponseEntity<List<UnidadeResponseDTO>> findAll();

    @Operation(summary = "Busca Unidade por ID", description = "Retorna os detalhes de uma unidade específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Unidade encontrada com sucesso.",
                    content = @Content(schema = @Schema(implementation = UnidadeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada.")
    })
    ResponseEntity<UnidadeResponseDTO> findById(@Parameter(description = "ID da Unidade") @PathVariable Integer id);

    @Operation(summary = "Cria uma nova Unidade", description = "Registra uma nova unidade, associando a um Segmento e Localidade.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Unidade criada com sucesso.",
                    content = @Content(schema = @Schema(implementation = UnidadeResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos (ex: Segmento/Localidade inexistente).")
    })
    ResponseEntity<UnidadeResponseDTO> save(@RequestBody UnidadeRequestDTO dto);

    @Operation(summary = "Atualiza uma Unidade existente", description = "Atualiza o registro de uma Unidade pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Unidade atualizada com sucesso.",
                    content = @Content(schema = @Schema(implementation = UnidadeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização.")
    })
    ResponseEntity<UnidadeResponseDTO> update(@Parameter(description = "ID da Unidade a ser atualizada") @PathVariable Integer id,
                                              @RequestBody UnidadeRequestDTO dto);

    @Operation(summary = "Deleta uma Unidade", description = "Remove uma Unidade do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Unidade deletada com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID da Unidade a ser deletada") @PathVariable Integer id);
}