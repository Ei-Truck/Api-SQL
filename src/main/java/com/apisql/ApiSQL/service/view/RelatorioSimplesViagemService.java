package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.RelatorioSimplesViagemDTO;
import com.apisql.ApiSQL.repository.view.RelatorioSimplesViagemRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RelatorioSimplesViagemService {
    private RelatorioSimplesViagemRepository relatorioSimplesViagemRepository;
    public RelatorioSimplesViagemService(RelatorioSimplesViagemRepository repository){
        this.relatorioSimplesViagemRepository = repository;
    }

    public List<RelatorioSimplesViagemDTO> findAll(){
        List<Object[]> resultado = relatorioSimplesViagemRepository.buscarRelatorioSimplesViagem();
        System.out.println(resultado);
        for(Object[] o : resultado){
            if (resultado.isEmpty()){
                System.out.println("vazio");
            }else{
                for (int i = 0; i < o.length ; i++) {
                    System.out.println(o[i]);
                }
            }
        }
        return resultado.stream()
                .map(obj -> {
                    RelatorioSimplesViagemDTO dto = new RelatorioSimplesViagemDTO();
                    dto.setIdViagem((Integer) obj[0]);
                    dto.setPlacaCaminhao((String) obj[1]);
                    dto.setDataInicioViagem((Date) obj[2]);
                    dto.setNomeMotorista((String) obj[3]);
                    dto.setKmViagem((String) obj[4]);
                    dto.setPontuacaoTotal((Long) obj[5]);
                    dto.setWasAnalysed((Boolean) obj[6]);
                    return dto;
                }).toList();
    }
}
