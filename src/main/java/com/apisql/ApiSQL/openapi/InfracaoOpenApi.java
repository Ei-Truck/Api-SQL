package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.InfracaoRequestDTO;
import com.apisql.ApiSQL.dto.InfracaoResponseDTO;
import com.apisql.ApiSQL.dto.view.RelatorioSemanalInfracoesDTO;
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

@Tag(name = "Infrações", description = "Operações de gerenciamento de ocorrências de infrações.")
public interface InfracaoOpenApi {

    @Operation(summary = "Lista todas as infrações", description = "Retorna uma lista de todas as infrações registradas.")
    @ApiResponse(responseCode = "200", description = "Lista de infrações retornada com sucesso.")
    ResponseEntity<List<InfracaoResponseDTO>> findAll();

    @Operation(summary = "Busca infração por ID", description = "Retorna os detalhes de uma infração específica pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Infração encontrada com sucesso.",
                    content = @Content(schema = @Schema(implementation = InfracaoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Infração não encontrada com o ID fornecido.")
    })
    ResponseEntity<InfracaoResponseDTO> findById(@Parameter(description = "ID da infração") @PathVariable Integer id);

    @Operation(summary = "Cria uma nova infração", description = "Registra uma nova infração.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Infração criada com sucesso.",
                    content = @Content(schema = @Schema(implementation = InfracaoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos (ex: ID de Viagem/Motorista/TipoInfracao inexistente).")
    })
    ResponseEntity<InfracaoResponseDTO> save(@RequestBody InfracaoRequestDTO dto);

    @Operation(summary = "Atualiza uma infração existente", description = "Atualiza completamente (PUT) o registro de uma infração pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Infração atualizada com sucesso.",
                    content = @Content(schema = @Schema(implementation = InfracaoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Infração não encontrada."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização.")
    })
    ResponseEntity<InfracaoResponseDTO> update(@Parameter(description = "ID da infração a ser atualizada") @PathVariable Integer id,
                                               @RequestBody InfracaoRequestDTO dto);

    @Operation(summary = "Deleta uma infração", description = "Remove uma infração do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Infração deletada com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "Infração não encontrada para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID da infração a ser deletada") @PathVariable Integer id);

    @Operation(summary = "Lista a quantidade de infrações por dias da semana", description = "Retorna uma lista de dias da semana e a quantidade de infrações nele.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de infrações retornada com sucesso.")
    })
    List<RelatorioSemanalInfracoesDTO> getAllRelatorioInfracoes();

}