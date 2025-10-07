package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.model.Segmento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/segmentos")
@Tag(name = "Segmentos", description = "Gerenciamento de segmentos")
public interface SegmentoOpenApi {

    @Operation(summary = "Lista todos os segmentos")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    List<Segmento> getAll();

    @Operation(summary = "Busca segmento por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Segmento encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Segmento.class))),
            @ApiResponse(responseCode = "404", description = "Segmento não encontrado")
    })
    ResponseEntity<Segmento> getById(@Parameter(description = "ID do segmento") Integer id);

    @Operation(summary = "Cria um novo segmento")
    @ApiResponse(responseCode = "201", description = "Segmento criado com sucesso")
    Segmento create(@RequestBody Segmento segmento);

    @Operation(summary = "Remove um segmento pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Segmento removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Segmento não encontrado")
    })
    ResponseEntity<Void> delete(Integer id);

}
