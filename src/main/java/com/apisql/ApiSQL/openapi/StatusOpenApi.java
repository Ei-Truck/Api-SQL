package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.model.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/status")
@Tag(name = "Status", description = "Gerenciamento de Status dos usuários")
public interface StatusOpenApi {
    @Operation(summary = "Lista todos os status")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    List<Status> getAll();

    @Operation(summary = "Busca status por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Status.class))),
            @ApiResponse(responseCode = "404", description = "Status não encontrado")
    })
    ResponseEntity<Status> getById(
            @Parameter(description = "ID do status") Integer id);

    @Operation(summary = "Cria um novo status")
    @ApiResponse(responseCode = "201", description = "Status criado com sucesso")
    Status create(Status status);

    @Operation(summary = "Remove um status pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Status removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Status não encontrado")
    })
    ResponseEntity<Void> delete(Integer id);
}
