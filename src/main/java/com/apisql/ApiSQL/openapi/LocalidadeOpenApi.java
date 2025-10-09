package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.LocalidadeRequestDTO;
import com.apisql.ApiSQL.dto.LocalidadeResponseDTO;
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

@Tag(name = "Localidades", description = "Operações de gerenciamento de localidades.")
public interface LocalidadeOpenApi {

    @Operation(summary = "Lista todas as localidades", description = "Retorna uma lista de todas as localidades.")
    @ApiResponse(responseCode = "200", description = "Lista de localidades retornada com sucesso.")
    ResponseEntity<List<LocalidadeResponseDTO>> findAll();

    @Operation(summary = "Busca localidade por ID", description = "Retorna os detalhes de uma localidade específica pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Localidade encontrada com sucesso.",
                    content = @Content(schema = @Schema(implementation = LocalidadeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Localidade não encontrada com o ID fornecido.")
    })
    ResponseEntity<LocalidadeResponseDTO> findById(@Parameter(description = "ID da localidade") @PathVariable Integer id);

    @Operation(summary = "Cria uma nova localidade", description = "Registra uma nova localidade.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Localidade criada com sucesso.",
                    content = @Content(schema = @Schema(implementation = LocalidadeResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.")
    })
    ResponseEntity<LocalidadeResponseDTO> save(@RequestBody LocalidadeRequestDTO dto);

    @Operation(summary = "Atualiza uma localidade existente", description = "Atualiza completamente (PUT) o registro de uma localidade pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Localidade atualizada com sucesso.",
                    content = @Content(schema = @Schema(implementation = LocalidadeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Localidade não encontrada."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização.")
    })
    ResponseEntity<LocalidadeResponseDTO> update(@Parameter(description = "ID da localidade a ser atualizada") @PathVariable Integer id,
                                                 @RequestBody LocalidadeRequestDTO dto);

    @Operation(summary = "Deleta uma localidade", description = "Remove uma localidade do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Localidade deletada com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "Localidade não encontrada para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID da localidade a ser deletada") @PathVariable Integer id);
}