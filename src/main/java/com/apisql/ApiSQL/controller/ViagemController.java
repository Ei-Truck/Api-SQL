package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.ViagemRequestDTO;
import com.apisql.ApiSQL.dto.ViagemResponseDTO;
import com.apisql.ApiSQL.openapi.ViagemOpenApi;
import com.apisql.ApiSQL.service.ViagemService;
import jakarta.validation.Valid;
import com.apisql.ApiSQL.dto.ViagemResponseDTO;

import com.apisql.ApiSQL.dto.view.OcorrenciaPorViagemDTO;
import com.apisql.ApiSQL.dto.view.RelatorioSimplesViagemDTO;
import com.apisql.ApiSQL.dto.view.VisaoBasicaViagemDTO;
import com.apisql.ApiSQL.openapi.ViagemOpenApi;
import com.apisql.ApiSQL.service.ViagemService;
import com.apisql.ApiSQL.service.view.OcorrenciaPorViagemService;
import com.apisql.ApiSQL.service.view.RelatorioSimplesViagemService;
import com.apisql.ApiSQL.service.view.VisaoBasicaViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viagens")
public class ViagemController implements ViagemOpenApi {

    private final ViagemService viagemService;
    private final RelatorioSimplesViagemService relatorioSimplesViagemService;
    private final OcorrenciaPorViagemService ocorrenciaPorViagemService;
    private final VisaoBasicaViagemService visaoBasicaViagemService;
      
    @Autowired
    public ViagemController(ViagemService viagemService, RelatorioSimplesViagemService relatorioSimplesViagemService, OcorrenciaPorViagemService ocorrenciaPorViagemService, VisaoBasicaViagemService visaoBasicaViagemService) {
        this.viagemService = viagemService;
        this.relatorioSimplesViagemService = relatorioSimplesViagemService;
        this.ocorrenciaPorViagemService = ocorrenciaPorViagemService;
        this.visaoBasicaViagemService = visaoBasicaViagemService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ViagemResponseDTO>> findAll() {
        List<ViagemResponseDTO> viagens = viagemService.findAll();
        return ResponseEntity.ok(viagens);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ViagemResponseDTO> findById(@PathVariable Integer id) {
        ViagemResponseDTO viagem = viagemService.findById(id);
        return ResponseEntity.ok(viagem);
    }

    @Override
    @PostMapping
    public ResponseEntity<ViagemResponseDTO> save(@Valid @RequestBody ViagemRequestDTO dto) {
        ViagemResponseDTO savedViagem = viagemService.save(dto);
        return new ResponseEntity<>(savedViagem, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ViagemResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody ViagemRequestDTO dto) {
        ViagemResponseDTO updatedViagem = viagemService.update(id, dto);
        return ResponseEntity.ok(updatedViagem);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        viagemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @Override
    @GetMapping("/relatorio-simples")
    public List<RelatorioSimplesViagemDTO> getAllRelatorioViagem() {
        return relatorioSimplesViagemService.findAll();
    }
    
    @GetMapping("/ocorrencias")
    public List<OcorrenciaPorViagemDTO> getAllOcorrencias() {
        return ocorrenciaPorViagemService.findAll();
    }

    @GetMapping("/visao-basica")
    public List<VisaoBasicaViagemDTO> getAllVisaoBasica() {
        return visaoBasicaViagemService.findALl();
    }
}
