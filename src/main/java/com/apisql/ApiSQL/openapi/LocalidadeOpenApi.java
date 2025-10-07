package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.model.Localidade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Localidades", description = "Gerenciamento de Localidades")
@RequestMapping("/localidades")
public interface LocalidadeOpenApi {
    // ...
    @GetMapping
    List<Localidade> getAll();
    // ...
    @GetMapping("/{id}")
    ResponseEntity<Localidade> getById(
            @Parameter(description = "ID da localidade") @PathVariable Integer id);
    // ...
    @PostMapping
    Localidade create(@RequestBody Localidade localidade);

    @Operation(summary = "Atualiza uma localidade existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Localidade atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Localidade não encontrada")
    })
    @PutMapping("/{id}") // ADICIONADO
    ResponseEntity<Localidade> update(@PathVariable Integer id,
                                      @RequestBody Localidade localidade);

    // ...
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id);
}

