package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.TipoInfracaoRequestDTO;
import com.apisql.ApiSQL.dto.TipoInfracaoResponseDTO;
import com.apisql.ApiSQL.dto.view.OcorrenciaPorTipoDTO;
import com.apisql.ApiSQL.dto.view.OcorrenciasPorGravidadeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Tipos de Infração", description = "Operações de gerenciamento de tipos de infração e pontuações.")
public interface TipoInfracaoOpenApi {

    @Operation(summary = "Lista todos os tipos de infração", description = "Retorna uma lista de todos os tipos de infração.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso.")
    ResponseEntity<List<TipoInfracaoResponseDTO>> findAll();

    @Operation(summary = "Busca TipoInfração por ID", description = "Retorna os detalhes de um tipo de infração específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoInfração encontrado com sucesso.",
                    content = @Content(schema = @Schema(implementation = TipoInfracaoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "TipoInfração não encontrado.")
    })
    ResponseEntity<TipoInfracaoResponseDTO> findById(@Parameter(description = "ID do TipoInfração") @PathVariable Integer id);

    @Operation(summary = "Cria um novo TipoInfração", description = "Registra um novo tipo de infração associado a um TipoGravidade.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TipoInfração criado com sucesso.",
                    content = @Content(schema = @Schema(implementation = TipoInfracaoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos (ex: TipoGravidade inexistente ou nome duplicado).")
    })
    ResponseEntity<TipoInfracaoResponseDTO> save(@RequestBody TipoInfracaoRequestDTO dto);

    @Operation(summary = "Atualiza um TipoInfração existente", description = "Atualiza o registro de um TipoInfração pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoInfração atualizado com sucesso.",
                    content = @Content(schema = @Schema(implementation = TipoInfracaoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "TipoInfração não encontrado."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização.")
    })
    ResponseEntity<TipoInfracaoResponseDTO> update(@Parameter(description = "ID do TipoInfração a ser atualizado") @PathVariable Integer id,
                                                   @RequestBody TipoInfracaoRequestDTO dto);

    @Operation(summary = "Deleta um TipoInfração", description = "Remove um TipoInfração do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoInfração deletado com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "TipoInfração não encontrado para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID do TipoInfração a ser deletado") @PathVariable Integer id);

    @Operation(summary = "Listar Ocorrencia Por Tipo",
            description = "Lista As Ocorrencias Por Tipo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ocorrencias obtidas com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    List<OcorrenciaPorTipoDTO> getAllOcorrenciaTipo(HttpServletRequest request);

    @Operation(summary = "Listar Ocorrencia Por Gravidade",
            description = "Lista As Ocorrencias Por Gravidade",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ocorrencias obtidas com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    List<OcorrenciasPorGravidadeDTO> getAllOcorrenciaGravidade(HttpServletRequest request);
}