package com.apisql.ApiSQL.dto;

import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.media.Schema;

public class MidiaUploadRequest {

    @Schema(description = "ID do motorista", required = true)
    private Integer motoristaId;

    @Schema(description = "ID da viagem", required = true)
    private Integer viagemId;

    @Schema(description = "Arquivo de vídeo", type = "string", format = "binary", required = true)
    private MultipartFile file;

    // getters e setters
    public Integer getMotoristaId() { return motoristaId; }
    public void setMotoristaId(Integer motoristaId) { this.motoristaId = motoristaId; }

    public Integer getViagemId() { return viagemId; }
    public void setViagemId(Integer viagemId) { this.viagemId = viagemId; }

    public MultipartFile getFile() { return file; }
    public void setFile(MultipartFile file) { this.file = file; }
}
