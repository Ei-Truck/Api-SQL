package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.UsuarioResponseDTO;
import com.apisql.ApiSQL.dto.UsuarioSenhaPatchDTO;
import com.apisql.ApiSQL.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "Usuários", description = "Operações de gerenciamento de usuários.")
public interface UsuarioOpenApi {

    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista de todos os usuários.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso.")
    ResponseEntity<List<UsuarioResponseDTO>> findAll();

    @Operation(summary = "Busca Usuário por ID", description = "Retorna os detalhes de um usuário específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso.",
                    content = @Content(schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    ResponseEntity<UsuarioResponseDTO> findById(@Parameter(description = "ID do Usuário") @PathVariable Integer id);

    @Operation(summary = "Busca Usuário por telefone", description = "Retorna os detalhes de um usuário pelo telefone.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso.",
                    content = @Content(schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    ResponseEntity<UsuarioResponseDTO> findByTelefone(@PathVariable String telefone);

    @Operation(summary = "Deleta um Usuário", description = "Remove um Usuário do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID do Usuário a ser deletado") @PathVariable Integer id);

    @Operation(summary = "Atualiza a senha de um Usuário", description = "Atualiza a senha do usuário passado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para atualização..")
    })
    ResponseEntity<UsuarioResponseDTO> atualizarSenha(@PathVariable Integer id, @RequestBody @Valid UsuarioSenhaPatchDTO usuarioSenhaDTO);

    @Operation(summary = "Atualizar a foto do usuário")
    @ApiResponse(responseCode = "200", description = "Atualizada com sucesso")
    ResponseEntity<UsuarioResponseDTO> atualizarFoto(@PathVariable Integer id,
                                          @RequestParam("file") MultipartFile file) throws IOException;
}
