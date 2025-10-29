package com.apisql.ApiSQL.repository.view;

import com.apisql.ApiSQL.security.AuthorizationUser;
import com.apisql.ApiSQL.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RelatorioSemanalInfracoesRepository {

    @PersistenceContext
    private EntityManager em;

    private final AuthorizationUser authorizationUser;

    public RelatorioSemanalInfracoesRepository(AuthorizationUser authorizationUser) {
        this.authorizationUser = authorizationUser;
    }

    public List<Map<String, Object>> buscarRelatorioSemanalInfracoes(HttpServletRequest request) {
        Usuario usuario = authorizationUser.getUsuarioLogado(request);

        Integer idSegmento = null;
        Integer idLocalidade = null;
        Integer idUnidade = null;

        switch (usuario.getCargo().getNome().toUpperCase().replace(" ", "_")) {
            case "GERENTE_ANALISE":
            case "ADMINISTRADOR":
                break;
            case "ANALISTA_SEGMENTO":
                idSegmento = usuario.getUnidade().getSegmento().getId();
                break;
            case "ANALISTA_REGIONAL":
                idLocalidade = usuario.getUnidade().getLocalidade().getId();
                break;
            case "ANALISTA_LOCAL":
                idUnidade = usuario.getUnidade().getId();
                break;
            default:
                throw new RuntimeException("Cargo não reconhecido: " + usuario.getCargo().getNome());
        }

        String sql = "SELECT dia_semana, SUM(total_infracoes) AS total_infracoes " +
                "FROM vw_relatorio_semanal_infracoes WHERE 1=1";

        if (idSegmento != null) sql += " AND id_segmento = " + idSegmento;
        if (idLocalidade != null) sql += " AND id_localidade = " + idLocalidade;
        if (idUnidade != null) sql += " AND id_unidade = " + idUnidade;

        sql += " GROUP BY dia_semana ORDER BY dia_semana DESC";

        List<Object[]> resultados = em.createNativeQuery(sql).getResultList();

        List<String> diasSemana = Arrays.asList("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado");
        Map<String, Integer> mapResultados = new HashMap<>();
        for (Object[] row : resultados) {
            String dia = (String) row[0];
            Integer total = ((Number) row[1]).intValue();
            mapResultados.put(dia, total);
        }

        List<Map<String, Object>> listaFinal = new ArrayList<>();
        for (String dia : diasSemana) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("diasemana", dia);
            mapa.put("total_infracoes", mapResultados.getOrDefault(dia, 0));
            listaFinal.add(mapa);
        }

        return listaFinal;
    }
}