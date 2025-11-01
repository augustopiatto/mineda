package com.augusto.SpringBoot.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cor_correcao")
public class Correcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cor_id")
    private Long id;

    @Column(name = "cor_feedback")
    private String feedback;

    @Column(name = "cor_nota")
    private Double nota;

    @Column(name = "cor_data")
    private LocalDate data;

    @Column(name = "cor_sequencia")
    private Integer sequencia;

    @ManyToOne
    @JoinColumn(name = "cor_anotacao", referencedColumnName = "ant_id")
    private Anotacao anotacao;

    public Correcao() { }

    public Correcao(String feedback, Double nota, LocalDate data, Integer sequencia, Anotacao anotacao) {
        this();
        this.feedback = feedback;
        this.nota = nota;
        this.data = data;
        this.sequencia = sequencia;
        this.anotacao = anotacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }

    public Anotacao getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(Anotacao anotacao) {
        this.anotacao = anotacao;
    }
}
