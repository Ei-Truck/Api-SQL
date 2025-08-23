package com.apisql.ApiSQL.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_unidade")
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_segmento")
    private Segmento segmento;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cidade", nullable = false, length = 50)
    private String cidade;

    @Column(name = "uf_estado", length = 2)
    private String ufEstado;

    @Column(name = "isinactive")
    private Boolean isInactive = false;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Segmento getSegmento() { return segmento; }
    public void setSegmento(Segmento segmento) { this.segmento = segmento; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getUfEstado() { return ufEstado; }
    public void setUfEstado(String ufEstado) { this.ufEstado = ufEstado; }

    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
}
