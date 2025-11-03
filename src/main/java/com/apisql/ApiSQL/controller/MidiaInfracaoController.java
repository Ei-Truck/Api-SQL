package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.MidiaConcatenadaResponseDTO;
import com.apisql.ApiSQL.dto.MidiaInfracaoRequestDTO;
import com.apisql.ApiSQL.dto.MidiaInfracaoResponseDTO;
import com.apisql.ApiSQL.dto.MotoristaResponseDTO;
import com.apisql.ApiSQL.openapi.MidiaInfracaoOpenApi;
import com.apisql.ApiSQL.service.MidiaInfracaoService;
import com.apisql.ApiSQL.service.MotoristaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/midias-infracoes")
public class MidiaInfracaoController implements MidiaInfracaoOpenApi {

    private final MidiaInfracaoService service;


    public MidiaInfracaoController(MidiaInfracaoService service, MotoristaService motoristaService) {
        this.service = service;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<MidiaInfracaoResponseDTO>> findAll() {
        List<MidiaInfracaoResponseDTO> midias = service.findAll();
        return ResponseEntity.ok(midias);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MidiaInfracaoResponseDTO> findById(@PathVariable Integer id) {
        MidiaInfracaoResponseDTO midia = service.findById(id);
        return ResponseEntity.ok(midia);
    }

    @Override
    @PostMapping
    public ResponseEntity<MidiaInfracaoResponseDTO> save(@Valid @RequestBody MidiaInfracaoRequestDTO dto) {
        MidiaInfracaoResponseDTO savedMidia = service.save(dto);
        return new ResponseEntity<>(savedMidia, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<MidiaInfracaoResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody MidiaInfracaoRequestDTO dto) {
        MidiaInfracaoResponseDTO updatedMidia = service.update(id, dto);
        return ResponseEntity.ok(updatedMidia);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/inserir-midia", consumes = "multipart/form-data")
    @Operation(
            summary = "Insere uma nova mídia em uma infração",
            description = "Realiza o upload de um arquivo de vídeo vinculado a uma viagem, motorista e infração."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Upload da mídia realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou arquivo ausente")
    })
    @Override
    public ResponseEntity<MidiaInfracaoResponseDTO> incluirMidia(
            @Parameter(description = "ID do motorista", required = true)
            @RequestParam Integer motoristaId,

            @Parameter(
                    description = "Arquivo de vídeo a ser enviado",
                    required = true,
                    content = @Content(
                            mediaType = "application/octet-stream",
                            schema = @Schema(type = "string", format = "binary")
                    )
            )
            @RequestPart("file") MultipartFile file
    ) throws IOException {

        MidiaInfracaoResponseDTO response = service.inserirMidia(motoristaId, file);
        return ResponseEntity.ok(response);
    }

}