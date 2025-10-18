package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.TipoGravidadeRequestDTO;
import com.apisql.ApiSQL.dto.TipoGravidadeResponseDTO;
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

@Tag(name = "Tipos de Gravidade", description = "Operações de gerenciamento de tipos de gravidade.")
public interface TipoGravidadeOpenApi {

    @Operation(summary = "Lista todos os tipos de gravidade", description = "Retorna uma lista de todos os tipos de gravidade.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso.")
    ResponseEntity<List<TipoGravidadeResponseDTO>> findAll();

    @Operation(summary = "Busca TipoGravidade por ID", description = "Retorna os detalhes de um tipo de gravidade específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoGravidade encontrado com sucesso.",
                    content = @Content(schema = @Schema(implementation = TipoGravidadeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "TipoGravidade não encontrado.")
    })
    ResponseEntity<TipoGravidadeResponseDTO> findById(@Parameter(description = "ID do TipoGravidade") @PathVariable Integer id);

    @Operation(summary = "Cria um novo TipoGravidade", description = "Registra um novo TipoGravidade. O ID deve ser fornecido no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TipoGravidade criado com sucesso.",
                    content = @Content(schema = @Schema(implementation = TipoGravidadeResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos (ex: ID ou nome duplicado).")
    })
    ResponseEntity<TipoGravidadeResponseDTO> save(@RequestBody TipoGravidadeRequestDTO dto);

    @Operation(summary = "Atualiza um TipoGravidade existente", description = "Atualiza o registro de um TipoGravidade pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoGravidade atualizado com sucesso.",
                    content = @Content(schema = @Schema(implementation = TipoGravidadeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "TipoGravidade não encontrado."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização.")
    })
    ResponseEntity<TipoGravidadeResponseDTO> update(@Parameter(description = "ID do TipoGravidade a ser atualizado") @PathVariable Integer id,
                                                    @RequestBody TipoGravidadeRequestDTO dto);

    @Operation(summary = "Deleta um TipoGravidade", description = "Remove um TipoGravidade do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoGravidade deletado com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "TipoGravidade não encontrado para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID do TipoGravidade a ser deletado") @PathVariable Integer id);
}