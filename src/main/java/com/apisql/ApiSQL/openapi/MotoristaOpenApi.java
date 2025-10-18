package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.MotoristaRequestDTO;
import com.apisql.ApiSQL.dto.MotoristaResponseDTO;
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

@Tag(name = "Motoristas", description = "Operações de gerenciamento de motoristas.")
public interface MotoristaOpenApi {

    @Operation(summary = "Lista todos os motoristas", description = "Retorna uma lista de todos os motoristas.")
    @ApiResponse(responseCode = "200", description = "Lista de motoristas retornada com sucesso.")
    ResponseEntity<List<MotoristaResponseDTO>> findAll();

    @Operation(summary = "Busca motorista por ID", description = "Retorna os detalhes de um motorista específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Motorista encontrado com sucesso.",
                    content = @Content(schema = @Schema(implementation = MotoristaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado com o ID fornecido.")
    })
    ResponseEntity<MotoristaResponseDTO> findById(@Parameter(description = "ID do motorista") @PathVariable Integer id);

    @Operation(summary = "Cria um novo motorista", description = "Registra um novo motorista.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Motorista criado com sucesso.",
                    content = @Content(schema = @Schema(implementation = MotoristaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos (ex: CPF/CNH duplicados, ID de Unidade/TipoRisco inexistente).")
    })
    ResponseEntity<MotoristaResponseDTO> save(@RequestBody MotoristaRequestDTO dto);

    @Operation(summary = "Atualiza um motorista existente", description = "Atualiza completamente (PUT) o registro de um motorista pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Motorista atualizado com sucesso.",
                    content = @Content(schema = @Schema(implementation = MotoristaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização.")
    })
    ResponseEntity<MotoristaResponseDTO> update(@Parameter(description = "ID do motorista a ser atualizado") @PathVariable Integer id,
                                                @RequestBody MotoristaRequestDTO dto);

    @Operation(summary = "Deleta um motorista", description = "Remove um motorista do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Motorista deletado com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "Motorista não encontrado para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID do motorista a ser deletado") @PathVariable Integer id);
}