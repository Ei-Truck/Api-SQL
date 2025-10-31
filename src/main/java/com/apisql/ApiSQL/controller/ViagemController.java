package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.dto.ViagemRequestDTO;
import com.apisql.ApiSQL.dto.ViagemResponseDTO;
import com.apisql.ApiSQL.dto.view.*;
import com.apisql.ApiSQL.openapi.ViagemOpenApi;
import com.apisql.ApiSQL.repository.view.VisaoBasicaViagemMotoristaInfoRepository;
import com.apisql.ApiSQL.service.ViagemService;
import com.apisql.ApiSQL.service.view.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import com.apisql.ApiSQL.dto.ViagemResponseDTO;

import com.apisql.ApiSQL.openapi.ViagemOpenApi;
import com.apisql.ApiSQL.service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/viagens")
public class ViagemController implements ViagemOpenApi {

    private final ViagemService viagemService;
    private final RelatorioSimplesViagemService relatorioSimplesViagemService;
    private final OcorrenciaPorViagemService ocorrenciaPorViagemService;
    private final VisaoBasicaViagemService visaoBasicaViagemService;
    private final VisaoBasicaViagemMotoristaInfoService visaoBasicaViagemMotoristaInfoService;
    private final QuantidadeInfracoesMotoristaViagemService quantidadeInfracoesMotoristaViagemService;
    private final QuantidadeInfracaoTipoGravidadeService quantidadeInfracoesTipoGravidadeService;
      
    @Autowired
    public ViagemController(ViagemService viagemService, RelatorioSimplesViagemService relatorioSimplesViagemService,
                            OcorrenciaPorViagemService ocorrenciaPorViagemService,
                            VisaoBasicaViagemService visaoBasicaViagemService,
                            VisaoBasicaViagemMotoristaInfoService visaoBasicaViagemMotoristaInfoService,
                            QuantidadeInfracoesMotoristaViagemService quantidadeInfracoesMotoristaViagemService,
                            QuantidadeInfracaoTipoGravidadeService quantidadeInfracoesTipoGravidadeService) {
        this.viagemService = viagemService;
        this.relatorioSimplesViagemService = relatorioSimplesViagemService;
        this.ocorrenciaPorViagemService = ocorrenciaPorViagemService;
        this.visaoBasicaViagemService = visaoBasicaViagemService;
        this.visaoBasicaViagemMotoristaInfoService = visaoBasicaViagemMotoristaInfoService;
        this.quantidadeInfracoesMotoristaViagemService = quantidadeInfracoesMotoristaViagemService;
        this.quantidadeInfracoesTipoGravidadeService = quantidadeInfracoesTipoGravidadeService;
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
    public List<RelatorioSimplesViagemDTO> getAllRelatorioViagem(HttpServletRequest request) {
        return relatorioSimplesViagemService.findAll(request);
    }

    @Override
    @GetMapping("/ocorrencias")
    public List<OcorrenciaPorViagemDTO> getAllOcorrencias(HttpServletRequest request) {
        return ocorrenciaPorViagemService.findAll(request);
    }

    @Override
    @GetMapping("/visao-basica/{id}")
    public Optional<VisaoBasicaViagemDTO> getAllVisaoBasica(@PathVariable Integer id) {
        return visaoBasicaViagemService.findById(id);
    }

    @Override
    @GetMapping("/motorista-visao-basica/{id}")
    public Optional<VisaoBasicaViagemMotoristaInfoDTO> getAllInfoMotorista(@PathVariable Integer id) {
        return visaoBasicaViagemMotoristaInfoService.findById(id);
    }

    @Override
    @GetMapping("/motorista-infracoes/{id}")
    public Optional<QuantidadeInfracoesViagemMotoristaDTO> getAllInfracoesMotorista(@PathVariable Integer id) {
        return quantidadeInfracoesMotoristaViagemService.findById(id);
    }

    @Override
    @GetMapping("/quantidade-infracao-tipo-gravidade/{id}")
    public Optional<QuantidadeInfracaoTipoGravidadeDTO> getAllInfracaoTipoGravidade(@PathVariable Integer id) {
        return quantidadeInfracoesTipoGravidadeService.findById(id);
    }
}
