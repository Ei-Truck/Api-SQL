package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.CargoRequestDTO;
import com.apisql.ApiSQL.dto.CargoResponseDTO;
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

@Tag(name = "Cargos", description = "Operações de gerenciamento de cargos.")
public interface CargoOpenApi {

    @Operation(summary = "Lista todos os cargos", description = "Retorna uma lista de todos os cargos.")
    @ApiResponse(responseCode = "200", description = "Lista de cargos retornada com sucesso.")
    ResponseEntity<List<CargoResponseDTO>> findAll();

    @Operation(summary = "Busca cargo por ID", description = "Retorna os detalhes de um cargo específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cargo encontrado com sucesso.",
                    content = @Content(schema = @Schema(implementation = CargoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cargo não encontrado com o ID fornecido.")
    })
    ResponseEntity<CargoResponseDTO> findById(@Parameter(description = "ID do cargo") @PathVariable Integer id);

    @Operation(summary = "Cria um novo cargo", description = "Registra um novo cargo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cargo criado com sucesso.",
                    content = @Content(schema = @Schema(implementation = CargoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos (ex: nome duplicado).")
    })
    ResponseEntity<CargoResponseDTO> save(@RequestBody CargoRequestDTO dto);

    @Operation(summary = "Atualiza um cargo existente", description = "Atualiza completamente (PUT) o registro de um cargo pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cargo atualizado com sucesso.",
                    content = @Content(schema = @Schema(implementation = CargoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cargo não encontrado."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização.")
    })
    ResponseEntity<CargoResponseDTO> update(@Parameter(description = "ID do cargo a ser atualizado") @PathVariable Integer id,
                                            @RequestBody CargoRequestDTO dto);

    @Operation(summary = "Deleta um cargo", description = "Remove um cargo do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cargo deletado com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "Cargo não encontrado para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID do cargo a ser deletado") @PathVariable Integer id);
}