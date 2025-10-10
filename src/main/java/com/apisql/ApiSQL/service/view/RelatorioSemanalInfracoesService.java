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
        for (Object[] o : resultado) {
            if(resultado.isEmpty()){
                System.out.println("Vazio");
            }else{
                System.out.println(o[0]);
                System.out.println(o[1]);
            }
        }
        return resultado.stream()
                .map(obj ->{
                    RelatorioSemanalInfracoesDTO dto = new RelatorioSemanalInfracoesDTO();
                    dto.setDiasemana(obj[0].toString());
                    dto.setTotal_infracoes(((Number)obj[1]).intValue());
                    return dto;
                }).toList();
    }
}
