package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.RegistroRequestDTO;
import com.apisql.ApiSQL.dto.RegistroResponseDTO;
import com.apisql.ApiSQL.dto.RegistroTratativaPatchDTO;
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

@Tag(name = "Registros", description = "Operações de gerenciamento de registros de tratativas de viagens.")
public interface RegistroOpenApi {

    @Operation(summary = "Lista todos os registros", description = "Retorna uma lista de todos os registros de tratativas.")
    @ApiResponse(responseCode = "200", description = "Lista de registros retornada com sucesso.")
    ResponseEntity<List<RegistroResponseDTO>> findAll();

    @Operation(summary = "Busca registro por ID", description = "Retorna os detalhes de um registro específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso.",
                    content = @Content(schema = @Schema(implementation = RegistroResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado com o ID fornecido.")
    })
    ResponseEntity<RegistroResponseDTO> findById(@Parameter(description = "ID do registro") @PathVariable Integer id);

    @Operation(summary = "Cria um novo registro", description = "Registra uma nova tratativa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro criado com sucesso.",
                    content = @Content(schema = @Schema(implementation = RegistroResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.")
    })
    ResponseEntity<RegistroResponseDTO> save(@RequestBody RegistroRequestDTO dto);

    @Operation(summary = "Atualiza um registro existente", description = "Atualiza completamente (PUT) o registro de uma tratativa pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso.",
                    content = @Content(schema = @Schema(implementation = RegistroResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado para atualização.")
    })
    ResponseEntity<RegistroResponseDTO> update(@Parameter(description = "ID do registro a ser atualizado") @PathVariable Integer id,
                                               @RequestBody RegistroRequestDTO registroAtualizado);

    @Operation(summary = "Atualiza parcialmente um registro", description = "Atualiza apenas o campo 'tratativa' do registro pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso.",
                    content = @Content(schema = @Schema(implementation = RegistroResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado.")
    })
    ResponseEntity<RegistroResponseDTO> patchTratativa(@Parameter(description = "ID da Viagem") @PathVariable Integer idViagem,
                                                       @Parameter(description = "ID do Motorista") @PathVariable Integer idMotorista,
                                                       @RequestBody RegistroTratativaPatchDTO dto);

    @Operation(summary = "Deleta um registro", description = "Remove um registro do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro deletado com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID do registro a ser deletado") @PathVariable Integer id);
}