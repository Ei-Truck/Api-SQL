package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.TipoInfracaoRequestDTO;
import com.apisql.ApiSQL.dto.TipoInfracaoResponseDTO;
import com.apisql.ApiSQL.dto.view.OcorrenciaPorTipoDTO;
import com.apisql.ApiSQL.dto.view.OcorrenciaPorViagemDTO;
import com.apisql.ApiSQL.dto.view.OcorrenciasPorGravidadeDTO;
import com.apisql.ApiSQL.dto.view.RelatorioSimplesViagemDTO;
import com.apisql.ApiSQL.openapi.TipoInfracaoOpenApi;
import com.apisql.ApiSQL.service.TipoInfracaoService;
import com.apisql.ApiSQL.service.view.OcorrenciaPorTipoService;
import com.apisql.ApiSQL.service.view.OcorrenciaPorViagemService;
import com.apisql.ApiSQL.service.view.OcorrenciasPorGravidadeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-infracao")
public class TipoInfracaoController implements TipoInfracaoOpenApi {

    private final TipoInfracaoService tipoInfracaoService;
    private final OcorrenciaPorTipoService ocorrenciaPorTipoService;
    private final OcorrenciasPorGravidadeService ocorrenciasPorGravidadeService;

    public TipoInfracaoController(TipoInfracaoService tipoInfracaoService, OcorrenciaPorTipoService ocorrenciaPorTipoService, OcorrenciasPorGravidadeService ocorrenciasPorGravidadeService) {
        this.tipoInfracaoService = tipoInfracaoService;
        this.ocorrenciaPorTipoService = ocorrenciaPorTipoService;
        this.ocorrenciasPorGravidadeService = ocorrenciasPorGravidadeService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<TipoInfracaoResponseDTO>> findAll() {
        List<TipoInfracaoResponseDTO> infracoes = tipoInfracaoService.findAll();
        return ResponseEntity.ok(infracoes);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TipoInfracaoResponseDTO> findById(@PathVariable Integer id) {
        TipoInfracaoResponseDTO infracao = tipoInfracaoService.findById(id);
        return ResponseEntity.ok(infracao);
    }

    @Override
    @PostMapping
    public ResponseEntity<TipoInfracaoResponseDTO> save(@Valid @RequestBody TipoInfracaoRequestDTO dto) {
        TipoInfracaoResponseDTO savedInfracao = tipoInfracaoService.save(dto);
        return new ResponseEntity<>(savedInfracao, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TipoInfracaoResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody TipoInfracaoRequestDTO dto) {
        TipoInfracaoResponseDTO updatedInfracao = tipoInfracaoService.update(id, dto);
        return ResponseEntity.ok(updatedInfracao);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        tipoInfracaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/ocorrencia-tipo")
    public List<OcorrenciaPorTipoDTO> getAllOcorrenciaTipo(HttpServletRequest request) {
        return ocorrenciaPorTipoService.findAll(request);
    }

    @Override
    @GetMapping("/ocorrencia-gravidade")
    public List<OcorrenciasPorGravidadeDTO> getAllOcorrenciaGravidade(HttpServletRequest request) {
        return ocorrenciasPorGravidadeService.findAll(request);
    }
}