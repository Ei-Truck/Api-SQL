package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.StatusRequestDTO;
import com.apisql.ApiSQL.dto.StatusResponseDTO;
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

@Tag(name = "Status", description = "Operações de gerenciamento de status (ativo/inativo).")
public interface StatusOpenApi {

    @Operation(summary = "Lista todos os status", description = "Retorna uma lista de todos os status.")
    @ApiResponse(responseCode = "200", description = "Lista de status retornada com sucesso.")
    ResponseEntity<List<StatusResponseDTO>> findAll();

    @Operation(summary = "Busca status por ID", description = "Retorna os detalhes de um status específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status encontrado com sucesso.",
                    content = @Content(schema = @Schema(implementation = StatusResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Status não encontrado com o ID fornecido.")
    })
    ResponseEntity<StatusResponseDTO> findById(@Parameter(description = "ID do status") @PathVariable Integer id);

    @Operation(summary = "Cria um novo status", description = "Registra um novo status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Status criado com sucesso.",
                    content = @Content(schema = @Schema(implementation = StatusResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos (ex: nome duplicado).")
    })
    ResponseEntity<StatusResponseDTO> save(@RequestBody StatusRequestDTO dto);

    @Operation(summary = "Atualiza um status existente", description = "Atualiza completamente (PUT) o registro de um status pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso.",
                    content = @Content(schema = @Schema(implementation = StatusResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Status não encontrado."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização.")
    })
    ResponseEntity<StatusResponseDTO> update(@Parameter(description = "ID do status a ser atualizado") @PathVariable Integer id,
                                             @RequestBody StatusRequestDTO dto);

    @Operation(summary = "Deleta um status", description = "Remove um status do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Status deletado com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "Status não encontrado para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID do status a ser deletado") @PathVariable Integer id);
}