package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.MidiaConcatenadaRequestDTO;
import com.apisql.ApiSQL.dto.MidiaConcatenadaResponseDTO;
import com.apisql.ApiSQL.dto.MidiaUploadRequest;
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

@Tag(name = "Mídia Concatenada", description = "Gerenciamento de URLs de mídia (vídeos, etc.) de viagens concatenadas.")
public interface MidiaConcatenadaOpenApi {

    @Operation(summary = "Lista todas as mídias concatenadas", description = "Retorna uma lista de todos os registros de mídia.")
    @ApiResponse(responseCode = "200", description = "Lista de mídias retornada com sucesso.")
    ResponseEntity<List<MidiaConcatenadaResponseDTO>> findAll();

    @Operation(summary = "Busca mídia por ID", description = "Retorna os detalhes de uma mídia específica pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mídia encontrada com sucesso.",
                    content = @Content(schema = @Schema(implementation = MidiaConcatenadaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Mídia não encontrada com o ID fornecido.")
    })
    ResponseEntity<MidiaConcatenadaResponseDTO> findById(@Parameter(description = "ID da mídia") @PathVariable Integer id);

    @Operation(summary = "Cria um novo registro de mídia", description = "Registra uma nova URL de mídia concatenada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mídia criada com sucesso.",
                    content = @Content(schema = @Schema(implementation = MidiaConcatenadaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.")
    })
    ResponseEntity<MidiaConcatenadaResponseDTO> save(@RequestBody MidiaConcatenadaRequestDTO dto);

    @Operation(summary = "Atualiza uma mídia existente", description = "Atualiza o registro de mídia pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mídia atualizada com sucesso.",
                    content = @Content(schema = @Schema(implementation = MidiaConcatenadaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Mídia não encontrada para atualização.")
    })
    ResponseEntity<MidiaConcatenadaResponseDTO> update(@Parameter(description = "ID da mídia a ser atualizada") @PathVariable Integer id,
                                                       @RequestBody MidiaConcatenadaRequestDTO dto);

    @Operation(summary = "Deleta uma mídia", description = "Remove um registro de mídia do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Mídia deletada com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "Mídia não encontrada para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID da mídia a ser deletada") @PathVariable Integer id);


}