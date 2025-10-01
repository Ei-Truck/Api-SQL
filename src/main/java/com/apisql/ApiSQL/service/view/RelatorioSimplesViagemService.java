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

        return resultado.stream()
                .map(obj -> {
                    RelatorioSimplesViagemDTO dto = new RelatorioSimplesViagemDTO();
                    dto.setPlaca_caminhao(obj[0] != null ? obj[0].toString() : null);
                    dto.setData_inicio_viagem(obj[1] != null ? (java.util.Date) obj[1] : null);
                    dto.setId_infracao(obj[2] != null ? ((Number) obj[2]).intValue() : null);
                    dto.setId_viagem(obj[3] != null ? ((Number) obj[3]).intValue() : null);
                    dto.setId_motorista(obj[4] != null ? ((Number) obj[4]).intValue() : null);
                    dto.setMotorista(obj[5] != null ? obj[5].toString() : null);       // campo novo
                    dto.setId_caminhao(obj[6] != null ? ((Number) obj[6]).intValue() : null);
                    dto.setKm_viagem(obj[7] != null ? obj[7].toString() : null);
                    dto.setPontuacao_total(obj[8] != null ? ((Number) obj[8]).intValue() : null); // campo novo
                    return dto;
                }).toList();


    }
}
