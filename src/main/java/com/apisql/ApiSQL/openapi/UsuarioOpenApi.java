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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.*; // Adicionado para incluir todos

@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
@RequestMapping("/usuarios")
public interface UsuarioOpenApi {
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping // ADICIONADO
    ResponseEntity<List<Usuario>> listarTodos();

    @Operation(summary = "Buscar usuário por ID", description = "Retorna os dados de um usuário específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}") // ADICIONADO
    ResponseEntity<Usuario> buscarPorId(
            @Parameter(description = "ID do usuário a ser buscado", required = true) @PathVariable Integer id); // ADICIONADO @PathVariable

    @Operation(summary = "Cadastrar novo usuário", description = "Cria um novo usuário no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados")
    })
    @PostMapping // ADICIONADO
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
    @PutMapping("/{id}") // ADICIONADO
    ResponseEntity<Usuario> atualizar(
            @Parameter(description = "ID do usuário a ser atualizado", required = true) @PathVariable Integer id, @RequestBody Usuario usuario); // ADICIONADO @PathVariable e @RequestBody

    @Operation(summary = "Deletar usuário", description = "Remove um usuário pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}") // ADICIONADO
    ResponseEntity<Void> deletar(
            @Parameter(description = "ID do usuário a ser deletado", required = true) @PathVariable Integer id); // ADICIONADO @PathVariable
}