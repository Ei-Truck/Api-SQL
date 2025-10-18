package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.view.RelatorioSemanalInfracoesDTO;
import com.apisql.ApiSQL.service.view.RelatorioSemanalInfracoesService;

import com.apisql.ApiSQL.dto.InfracaoRequestDTO;
import com.apisql.ApiSQL.dto.InfracaoResponseDTO;
import com.apisql.ApiSQL.openapi.InfracaoOpenApi;
import com.apisql.ApiSQL.service.InfracaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/infracoes")
public class InfracaoController implements InfracaoOpenApi {

    private final InfracaoService infracaoService;
    private final RelatorioSemanalInfracoesService relatorioService;

    public InfracaoController(InfracaoService infracaoService, RelatorioSemanalInfracoesService relatorioService) {
        this.infracaoService = infracaoService;
        this.relatorioService = relatorioService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<InfracaoResponseDTO>> findAll() {
        List<InfracaoResponseDTO> infracoes = infracaoService.findAll();
        return ResponseEntity.ok(infracoes);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<InfracaoResponseDTO> findById(@PathVariable Integer id) {
        InfracaoResponseDTO infracao = infracaoService.findById(id);
        return ResponseEntity.ok(infracao);
    }

    @Override
    @PostMapping
    public ResponseEntity<InfracaoResponseDTO> save(@Valid @RequestBody InfracaoRequestDTO dto) {
        InfracaoResponseDTO savedInfracao = infracaoService.save(dto);
        return new ResponseEntity<>(savedInfracao, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<InfracaoResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody InfracaoRequestDTO dto) {
        InfracaoResponseDTO updatedInfracao = infracaoService.update(id, dto);
        return ResponseEntity.ok(updatedInfracao);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        infracaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/relatorio")
    public List<RelatorioSemanalInfracoesDTO> getAllRelatorioInfracoes() {
        return relatorioService.findAll();
    }
}
