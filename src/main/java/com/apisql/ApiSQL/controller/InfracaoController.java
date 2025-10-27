package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.view.RelatorioSemanalInfracoesDTO;
import com.apisql.ApiSQL.dto.view.TotalOcorrenciasDTO;
import com.apisql.ApiSQL.dto.view.VariacaoMesPassadoPorMesAnoDTO;
import com.apisql.ApiSQL.service.view.RelatorioSemanalInfracoesService;

import com.apisql.ApiSQL.dto.InfracaoRequestDTO;
import com.apisql.ApiSQL.dto.InfracaoResponseDTO;
import com.apisql.ApiSQL.openapi.InfracaoOpenApi;
import com.apisql.ApiSQL.service.InfracaoService;
import com.apisql.ApiSQL.service.view.TotalOcorrenciasService;
import com.apisql.ApiSQL.service.view.VariacaoMesPassadoPorMesAnoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/infracoes")
public class InfracaoController implements InfracaoOpenApi {

    private final InfracaoService infracaoService;
    private final RelatorioSemanalInfracoesService relatorioService;
    private final TotalOcorrenciasService totalOcorrenciasService;
    private final VariacaoMesPassadoPorMesAnoService variacaoMesPassadoPorMesAno;

    public InfracaoController(InfracaoService infracaoService, RelatorioSemanalInfracoesService relatorioService, TotalOcorrenciasService totalOcorrenciasService, VariacaoMesPassadoPorMesAnoService variacaoMesPassadoPorMesAno) {
        this.infracaoService = infracaoService;
        this.relatorioService = relatorioService;
        this.totalOcorrenciasService = totalOcorrenciasService;
        this.variacaoMesPassadoPorMesAno = variacaoMesPassadoPorMesAno;
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
    public List<RelatorioSemanalInfracoesDTO> getAllRelatorioInfracoes(HttpServletRequest request) {
        return relatorioService.findAll(request);
    }

    @Override
    @GetMapping("/variacao")
    public List<VariacaoMesPassadoPorMesAnoDTO> getAllVariacao(HttpServletRequest request){
        return variacaoMesPassadoPorMesAno.findAll(request);
    }

    @Override
    @GetMapping("/total-ocorrencias")
    public List<TotalOcorrenciasDTO> getAllOcorrencias(HttpServletRequest request){
        return totalOcorrenciasService.findAll(request);
    }
}
