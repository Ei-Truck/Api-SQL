package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.VariacaoMesPassadoPorMesAnoDTO;
import com.apisql.ApiSQL.repository.view.VariacaoMesPassadoPorMesAnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariacaoMesPassadoPorMesAnoService {

    private final VariacaoMesPassadoPorMesAnoRepository repository;

    public VariacaoMesPassadoPorMesAnoService( VariacaoMesPassadoPorMesAnoRepository repository){
        this.repository = repository;
    }

    public List<VariacaoMesPassadoPorMesAnoDTO> findAll(){
        List<Object[]> resultado = repository.buscarVariacaoMesPassado();
        return resultado.stream()
                .map(obj ->{
                    VariacaoMesPassadoPorMesAnoDTO dto = new VariacaoMesPassadoPorMesAnoDTO();
                    dto.setMes(((Number)obj[0]).intValue());
                    dto.setAno(((Number)obj[1]).intValue());
                    dto.setInfracoes_mes_atual(((Number)obj[2]).longValue());
                    dto.setInfracoes_mes_passado(obj[3] != null ? ((Number)obj[3]).longValue() : null);
                    dto.setVariacao(obj[4] != null ? ((Number)obj[4]).doubleValue() : null);
                    return dto;
                }).toList();
    }
}