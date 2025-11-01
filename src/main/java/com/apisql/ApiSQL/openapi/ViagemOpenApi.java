package com.apisql.ApiSQL.openapi;

import com.apisql.ApiSQL.dto.ViagemPatchDTO;
import com.apisql.ApiSQL.dto.ViagemRequestDTO;
import com.apisql.ApiSQL.dto.ViagemResponseDTO;
import com.apisql.ApiSQL.dto.view.QuantidadeInfracaoTipoGravidadeDTO;
import com.apisql.ApiSQL.dto.view.QuantidadeInfracoesViagemMotoristaDTO;
import com.apisql.ApiSQL.dto.view.RelatorioSimplesViagemDTO;
import com.apisql.ApiSQL.dto.view.VisaoBasicaViagemDTO;
import com.apisql.ApiSQL.dto.view.VisaoBasicaViagemMotoristaInfoDTO;
import com.apisql.ApiSQL.dto.view.OcorrenciaPorViagemDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Tag(name = "Viagens", description = "Operações de gerenciamento de viagens de caminhões.")
public interface ViagemOpenApi {

    @Operation(summary = "Lista todas as viagens", description = "Retorna uma lista de todas as viagens.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso.")
    ResponseEntity<List<ViagemResponseDTO>> findAll();

    @Operation(summary = "Busca Viagem por ID", description = "Retorna os detalhes de uma viagem específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viagem encontrada com sucesso.",
                    content = @Content(schema = @Schema(implementation = ViagemResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Viagem não encontrada.")
    })
    ResponseEntity<ViagemResponseDTO> findById(@Parameter(description = "ID da Viagem") @PathVariable Integer id);

    @Operation(summary = "Cria uma nova Viagem", description = "Registra uma nova viagem, associando Caminhão, Usuário e Localidades.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Viagem criada com sucesso.",
                    content = @Content(schema = @Schema(implementation = ViagemResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos (ex: Caminhão/Usuário/Localidade inexistente).")
    })
    ResponseEntity<ViagemResponseDTO> save(@RequestBody ViagemRequestDTO dto);

    @Operation(summary = "Atualiza uma Viagem existente", description = "Atualiza o registro de uma Viagem pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viagem atualizada com sucesso.",
                    content = @Content(schema = @Schema(implementation = ViagemResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Viagem não encontrada."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização.")
    })
    ResponseEntity<ViagemResponseDTO> update(@Parameter(description = "ID da Viagem a ser atualizada") @PathVariable Integer id,
                                             @RequestBody ViagemRequestDTO dto);

    @Operation(summary = "Deleta uma Viagem", description = "Remove uma Viagem do banco de dados pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Viagem deletada com sucesso (No Content)."),
            @ApiResponse(responseCode = "404", description = "Viagem não encontrada para exclusão.")
    })
    ResponseEntity<Void> deleteById(@Parameter(description = "ID da Viagem a ser deletada") @PathVariable Integer id);

    @Operation(summary = "Lista o relatório de ocorrências",
            description = "Retorna a lista de ocorrências por viagem.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso.",
                            content = @Content(schema = @Schema(implementation = OcorrenciaPorViagemDTO.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    List<OcorrenciaPorViagemDTO> getAllOcorrencias(HttpServletRequest request);

    @Operation(summary = "Busca Visão Básica da Viagem",
            description = "Retorna informações resumidas de uma viagem pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Informações básicas encontradas.",
                            content = @Content(schema = @Schema(implementation = VisaoBasicaViagemDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Viagem não encontrada.")
            })
    Optional<VisaoBasicaViagemDTO> getAllVisaoBasica(@Parameter(description = "ID da Viagem") @PathVariable Integer id);

    @Operation(summary = "Busca Informações Básicas do Motorista na Viagem",
            description = "Retorna a visão básica da viagem e as informações do motorista pelo ID da viagem.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Informações encontradas.",
                            content = @Content(schema = @Schema(implementation = VisaoBasicaViagemMotoristaInfoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Viagem não encontrada.")
            })
    List<VisaoBasicaViagemMotoristaInfoDTO> getAllInfoMotorista(@Parameter(description = "ID da Viagem") @PathVariable Integer id);

    @Operation(summary = "Busca Quantidade de Infrações do Motorista na Viagem",
            description = "Retorna a contagem total de infrações associadas ao motorista em uma viagem específica.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Contagem de infrações obtida.",
                            content = @Content(schema = @Schema(implementation = QuantidadeInfracoesViagemMotoristaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Viagem ou infrações não encontradas.")
            })
    List<QuantidadeInfracoesViagemMotoristaDTO> getAllInfracoesMotorista(@Parameter(description = "ID da Viagem") @PathVariable Integer id);

    @Operation(summary = "Busca Contagem de Infrações por Tipo e Gravidade",
            description = "Retorna a contagem de infrações por tipo e nível de gravidade para uma viagem específica.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Contagem obtida com sucesso.",
                            content = @Content(schema = @Schema(implementation = QuantidadeInfracaoTipoGravidadeDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Viagem ou dados de infração não encontrados.")
            })
    List<QuantidadeInfracaoTipoGravidadeDTO> getAllInfracaoTipoGravidade(@Parameter(description = "ID da Viagem") @PathVariable Integer id);

    @Operation(summary = "Listar relatório",
            description = "Lista um relatório simples da viagem",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Relatório obtido com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    List<RelatorioSimplesViagemDTO> getAllRelatorioViagem(HttpServletRequest request);

    @Operation(summary = "Marca Viagem como Analisada/Checada",
            description = "Atualiza o status de análise de uma Viagem pelo ID. Esta é uma atualização parcial (PATCH).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Status de Viagem atualizado com sucesso.",
                            content = @Content(schema = @Schema(implementation = ViagemResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Viagem não encontrada."),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos para o cheque.")
            })
    ViagemResponseDTO checkViagem(@Parameter(description = "ID da Viagem") @PathVariable Integer id,
                                  @RequestBody ViagemPatchDTO dto);
}