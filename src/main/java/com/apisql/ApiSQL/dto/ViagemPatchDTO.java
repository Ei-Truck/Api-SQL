package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.NotNull;

public class ViagemPatchDTO {

    @NotNull(message = "Este campo é obrigatório")
    private Boolean wasAnalyzed;

    public Boolean getWasAnalyzed() {
        return wasAnalyzed;
    }

    public void setWasAnalyzed(Boolean wasAnalyzed) {
        this.wasAnalyzed = wasAnalyzed;
    }
}
