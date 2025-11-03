package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.NotNull;

public class RegistroTratativaPatchDTO {

    @NotNull
    private String tratativa;

    public String getTratativa() { return tratativa; }
    public void setTratativa(String tratativa) { this.tratativa = tratativa; }
}