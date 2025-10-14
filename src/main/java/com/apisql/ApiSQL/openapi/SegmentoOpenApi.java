package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.SegmentoRequestDTO;
import com.apisql.ApiSQL.dto.SegmentoResponseDTO;
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

@Tag(name = "Segmentos", description = "Operações de gerenciamento de segmentos.")
public interface SegmentoOpenApi {

    @Operation(summary = "Lista todos os segmentos", description = "Retorna uma lista de todos os segmentos.")
    @ApiResponse(responseCode = "200", description = "Lista de segmentos retornada com sucesso.")
    ResponseEntity<List<SegmentoResponseDTO>> findAll();

    @Operation(summary = "Busca segmento por ID", description = "Retorna os detalhes de um segmento específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Segmento encontrado com sucesso.",
                    content = @Content(schema = @Schema(implementation = SegmentoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Segmento não encontrado com o ID fornecido.")
    })
    ResponseEntity<SegmentoResponseDTO> findById(@Parameter(description = "ID do segmento") @PathVariable Integer id);

    @Operation(summary = "Cria um novo segmento", description = "Registra um novo segmento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Segmento criado com sucesso.",
                    content = @Content(schema = @Schema(implementation = SegmentoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos (ex: nome duplicado).")
    })
    ResponseEntity<SegmentoResponseDTO> save(@RequestBody SegmentoRequestDTO dto);

    @Operation(summary = "Atualiza um segmento existente", description = "Atualiza completamente (PUT) o registro de um segmento pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Segmento atualizado com sucesso.",
                    content = @Content(schema = @Schema(implementation = SegmentoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Segmento não encontrado."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização.")
    })
    ResponseEntity<SegmentoResponseDTO> update(@Parameter(description = "ID do segmento a ser atualizado") @PathVariable Integer id,
                                               @RequestBody SegmentoRequestDTO dto);

    @Operation(summary = "Deleta um segmento", description = "Remove um segmento do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Segmento deletado com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "Segmento não encontrado para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID do segmento a ser deletado") @PathVariable Integer id);
}