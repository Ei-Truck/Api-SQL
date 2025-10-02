package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.RelatorioSemanalInfracoesDTO;
import com.apisql.ApiSQL.repository.view.RelatorioSemanalInfracoesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioSemanalInfracoesService {

    private RelatorioSemanalInfracoesRepository relatorioSemanalInfracoesRepository;

    public RelatorioSemanalInfracoesService( RelatorioSemanalInfracoesRepository relatorioSemanalInfracoesRepository){
        this.relatorioSemanalInfracoesRepository = relatorioSemanalInfracoesRepository;
    }

    public List<RelatorioSemanalInfracoesDTO> findAll(){
        List<Object[]> resultado = relatorioSemanalInfracoesRepository.buscarRelatorioSamanalInfracoes();
        return resultado.stream()
                .map(obj ->{
                    RelatorioSemanalInfracoesDTO dto = new RelatorioSemanalInfracoesDTO();
                    dto.setDiasemana(obj[0] != null? obj[0].toString(): null );
                    dto.setTotal_infracoes(obj[1] != null? ((Number)obj[1]).intValue(): 0);
                    return dto;
                }).toList();
    }
}
