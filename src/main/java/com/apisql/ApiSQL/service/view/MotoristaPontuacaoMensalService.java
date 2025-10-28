package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.MotoristaPontuacaoMensalDTO;
import com.apisql.ApiSQL.repository.view.MotoristaPontuacaoMensalRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MotoristaPontuacaoMensalService {

    private final MotoristaPontuacaoMensalRepository repository;

    public MotoristaPontuacaoMensalService(MotoristaPontuacaoMensalRepository repository) {
        this.repository = repository;
    }

    public List<MotoristaPontuacaoMensalDTO> findAll(HttpServletRequest request) {
        List<Object[]> resultado = repository.buscarPontuacaoMensalMotoristas(request);

        List<MotoristaPontuacaoMensalDTO> lista = resultado.stream()
                .map(obj -> {
                    MotoristaPontuacaoMensalDTO dto = new MotoristaPontuacaoMensalDTO();
                    dto.setMotorista(obj[1] != null ? obj[1].toString() : null);
                    dto.setId_unidade(obj[2] != null ? ((Number) obj[2]).intValue() : null);
                    dto.setUnidade(obj[3] != null ? obj[3].toString() : null);
                    dto.setId_segmento(obj[4] != null ? ((Number) obj[4]).intValue() : null);
                    dto.setSegmento(obj[5] != null ? obj[5].toString() : null);
                    dto.setPontuacao_ultimo_mes(obj[6] != null ? ((Number) obj[6]).doubleValue() : 0.0);
                    return dto;
                })
                .collect(Collectors.toList());

        lista.sort(Comparator.comparing(MotoristaPontuacaoMensalDTO::getPontuacao_ultimo_mes).reversed()
                .thenComparing(dto -> dto.getMotorista() == null ? "" : dto.getMotorista()));

        List<MotoristaPontuacaoMensalDTO> resultadoComRank = new ArrayList<>();
        double ultimaPontuacao = Double.NEGATIVE_INFINITY;
        int rankAtual = 0;
        int posicao = 0;

        for (MotoristaPontuacaoMensalDTO dto : lista) {
            posicao++;
            double pont = dto.getPontuacao_ultimo_mes();

            if (Double.compare(pont, ultimaPontuacao) != 0) {
                rankAtual = posicao;
                ultimaPontuacao = pont;
            }
            dto.setRanking_pontuacao(rankAtual);
            resultadoComRank.add(dto);
        }

        return resultadoComRank;
    }
}
