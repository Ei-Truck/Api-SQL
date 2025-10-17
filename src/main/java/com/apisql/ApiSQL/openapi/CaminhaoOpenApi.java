package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.CaminhaoRequestDTO;
import com.apisql.ApiSQL.dto.CaminhaoResponseDTO;
import com.apisql.ApiSQL.model.Caminhao;
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

@Tag(name = "Caminhões", description = "Operações de gerenciamento de frotas de caminhões.")
public interface CaminhaoOpenApi {

    @Operation(summary = "Lista todos os caminhões", description = "Retorna uma lista de todos os caminhões registrados na frota.")
    @ApiResponse(responseCode = "200", description = "Lista de caminhões retornada com sucesso.")
    ResponseEntity<List<CaminhaoResponseDTO>> findAll();

    @Operation(summary = "Busca caminhão por ID", description = "Retorna os detalhes de um caminhão específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Caminhão encontrado com sucesso.",
                    content = @Content(schema = @Schema(implementation = CaminhaoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Caminhão não encontrado com o ID fornecido.")
    })
    ResponseEntity<CaminhaoResponseDTO> findById(@Parameter(description = "ID do caminhão") @PathVariable Integer id);

    @Operation(summary = "Cria um novo caminhão", description = "Registra um novo caminhão na frota.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Caminhão criado com sucesso.",
                    content = @Content(schema = @Schema(implementation = CaminhaoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos (ex: placa ou chassi duplicados, ID de Segmento/Unidade inexistente).")
    })
    ResponseEntity<CaminhaoResponseDTO> save(@RequestBody CaminhaoRequestDTO dto);

    @Operation(summary = "Atualiza um caminhão existente", description = "Atualiza completamente (PUT) o registro de um caminhão pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Caminhão atualizado com sucesso.",
                    content = @Content(schema = @Schema(implementation = CaminhaoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Caminhão não encontrado ou dados inválidos para atualização.")
    })
    ResponseEntity<CaminhaoResponseDTO> update(@Parameter(description = "ID do caminhão a ser atualizado") @PathVariable Integer id,
                                               @RequestBody CaminhaoRequestDTO caminhaoAtualizado);

    @Operation(summary = "Deleta um caminhão", description = "Remove um caminhão do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Caminhão deletado com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "Caminhão não encontrado para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID do caminhão a ser deletado") @PathVariable Integer id);
}