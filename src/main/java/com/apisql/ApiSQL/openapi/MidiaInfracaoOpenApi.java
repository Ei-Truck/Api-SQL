package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.MidiaConcatenadaResponseDTO;
import com.apisql.ApiSQL.dto.MidiaInfracaoRequestDTO;
import com.apisql.ApiSQL.dto.MidiaInfracaoResponseDTO;
import com.apisql.ApiSQL.dto.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "Mídia de Infração", description = "Gerenciamento de URLs de mídia (vídeos, etc.) associadas a infrações.")
public interface MidiaInfracaoOpenApi {

    @Operation(summary = "Lista todas as mídias de infração", description = "Retorna uma lista de todos os registros de mídia de infração.")
    @ApiResponse(responseCode = "200", description = "Lista de mídias retornada com sucesso.")
    ResponseEntity<List<MidiaInfracaoResponseDTO>> findAll();

    @Operation(summary = "Busca mídia por ID", description = "Retorna os detalhes de uma mídia de infração específica pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mídia encontrada com sucesso.",
                    content = @Content(schema = @Schema(implementation = MidiaInfracaoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Mídia não encontrada com o ID fornecido.")
    })
    ResponseEntity<MidiaInfracaoResponseDTO> findById(@Parameter(description = "ID da mídia") @PathVariable Integer id);

    @Operation(summary = "Cria um novo registro de mídia", description = "Registra uma nova URL de mídia de infração.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mídia criada com sucesso.",
                    content = @Content(schema = @Schema(implementation = MidiaInfracaoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.")
    })
    ResponseEntity<MidiaInfracaoResponseDTO> save(@RequestBody MidiaInfracaoRequestDTO dto);

    @Operation(summary = "Atualiza uma mídia existente", description = "Atualiza o registro de mídia de infração pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mídia atualizada com sucesso.",
                    content = @Content(schema = @Schema(implementation = MidiaInfracaoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Mídia não encontrada para atualização.")
    })
    ResponseEntity<MidiaInfracaoResponseDTO> update(@Parameter(description = "ID da mídia a ser atualizada") @PathVariable Integer id,
                                                    @RequestBody MidiaInfracaoRequestDTO dto);

    @Operation(summary = "Deleta uma mídia", description = "Remove um registro de mídia de infração do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Mídia deletada com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "Mídia não encontrada para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID da mídia a ser deletada") @PathVariable Integer id);
    @Operation(
            summary = "Insere uma nova mídia em uma infração",
            description = "Realiza o upload de um arquivo de vídeo vinculado a uma viagem e motorista."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Upload da mídia realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou arquivo ausente")
    })
    @PostMapping(value = "/inserir-midia", consumes = {"multipart/form-data"})
    ResponseEntity<MidiaInfracaoResponseDTO> incluirMidia(
            @Parameter(description = "ID do motorista", required = true)
            @RequestParam Integer motoristaId,

            @Parameter(
                    description = "Arquivo de vídeo a ser enviado",
                    required = true,
                    content = @Content(mediaType = "application/octet-stream",
                            schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart("file") MultipartFile file
    ) throws IOException;

}