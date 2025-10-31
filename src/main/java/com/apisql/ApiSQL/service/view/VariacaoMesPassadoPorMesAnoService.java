package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.VariacaoMesPassadoPorMesAnoDTO;
import com.apisql.ApiSQL.repository.view.VariacaoMesPassadoPorMesAnoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariacaoMesPassadoPorMesAnoService {

    private final VariacaoMesPassadoPorMesAnoRepository repository;

    public VariacaoMesPassadoPorMesAnoService( VariacaoMesPassadoPorMesAnoRepository repository){
        this.repository = repository;
    }

    public List<VariacaoMesPassadoPorMesAnoDTO> findAll(HttpServletRequest request){
        List<Object[]> resultado = repository.buscarVariacaoMesPassado(request);
        return resultado.stream()
                .map(obj ->{
                    VariacaoMesPassadoPorMesAnoDTO dto = new VariacaoMesPassadoPorMesAnoDTO();
                    dto.setIdUnidade(((Number)obj[0]).intValue());
                    dto.setIdSegmento(((Number)obj[1]).intValue());
                    dto.setIdLocalidade(((Number)obj[2]).intValue());
                    dto.setMes(((Number)obj[3]).intValue());
                    dto.setAno(((Number)obj[4]).intValue());
                    dto.setInfracoes_mes_atual(((Number)obj[5]).longValue());
                    dto.setInfracoes_mes_passado(obj[6] != null ? ((Number)obj[6]).longValue() : null);
                    dto.setVariacao(obj[7] != null ? ((Number)obj[7]).doubleValue() : null);
                    dto.setUfEstado((obj[8]).toString());
                    return dto;
                }).toList();
    }
}