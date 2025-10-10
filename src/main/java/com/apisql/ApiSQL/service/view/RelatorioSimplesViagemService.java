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
        return resultado.stream()
                .map(obj -> {
                    RelatorioSimplesViagemDTO dto = new RelatorioSimplesViagemDTO();
                    dto.setId_viagem(obj[0] != null ? ((Number) obj[0]).intValue() : null);
                    dto.setPlaca_caminhao(obj[1] != null ? obj[1].toString() : null);
                    dto.setData_inicio_viagem(obj[2] != null ? (Date) obj[2] : null);
                    dto.setMotorista(obj[3] != null ? obj[3].toString() : null);
                    dto.setKm_viagem(obj[4] != null ? obj[4].toString() : null);
                    dto.setPontuacao_total(obj[5] != null ? ((Number) obj[5]).intValue() : null);
                    return dto;
                }).toList();
    }
}
