package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.model.Usuario;
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

@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
@RequestMapping("/usuarios")
public interface UsuarioOpenApi {
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    ResponseEntity<List<Usuario>> listarTodos();

    @Operation(summary = "Buscar usuário por ID", description = "Retorna os dados de um usuário específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<Usuario> buscarPorId(
            @Parameter(description = "ID do usuário a ser buscado", required = true) Integer id);
    @Operation(summary = "Cadastrar novo usuário", description = "Cria um novo usuário no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados")
    })
    ResponseEntity<Usuario> salvar(
            @Parameter(description = "Objeto usuário a ser criado", required = true)
            @RequestBody Usuario usuario);
    @Operation(summary = "Atualizar usuário existente", description = "Atualiza os dados de um usuário pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<Usuario> atualizar(
            @Parameter(description = "ID do usuário a ser atualizado", required = true) Integer id, Usuario usuario);

    @Operation(summary = "Deletar usuário", description = "Remove um usuário pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<Void> deletar(
            @Parameter(description = "ID do usuário a ser deletado", required = true) Integer id);

    @Operation(summary = "Atualizar a foto do usuário")
    @ApiResponse(responseCode = "200", description = "Atualizada com sucesso")
    @PostMapping("/usuarios/{id}/foto")
    ResponseEntity<Usuario> atualizarFoto(@PathVariable Integer id,
                                          @RequestParam("file") MultipartFile file) throws IOException;
}
