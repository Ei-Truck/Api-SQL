package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.MotoristaPontuacaoMensalDTO;
import com.apisql.ApiSQL.repository.view.MotoristaPontuacaoMensalRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoristaPontuacaoMensalService {

    private final MotoristaPontuacaoMensalRepository repository;

    public MotoristaPontuacaoMensalService( MotoristaPontuacaoMensalRepository repository){
        this.repository = repository;
    }

    public List<MotoristaPontuacaoMensalDTO> findAll(HttpServletRequest request){
        List<Object[]> resultado = repository.buscarPontuacaoMensalMotoristas(request);
        return resultado.stream()
                .map(obj ->{
                    MotoristaPontuacaoMensalDTO dto = new MotoristaPontuacaoMensalDTO();
                    dto.setRanking_pontuacao(((Number)obj[0]).intValue());
                    dto.setMotorista(obj[1].toString());
                    dto.setId_unidade(((Number)obj[2]).intValue());
                    dto.setUnidade(obj[3].toString());
                    dto.setId_segmento(((Number)obj[4]).intValue());
                    dto.setSegmento(obj[5].toString());
                    dto.setPontuacao_ultimo_mes(((Number)obj[6]).doubleValue());
                    return dto;
                }).toList();
    }
}