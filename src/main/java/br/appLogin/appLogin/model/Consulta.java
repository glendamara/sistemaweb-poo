package br.appLogin.appLogin.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Paciente paciente;

    @ManyToOne(optional = false)
    private Doutor doutor;

    private Date data; // Data da consulta

    private String status; // Exemplo: AGENDADA, CANCELADA, REALIZADA

    private String descricao; // Adicionado pelo médico após a consulta

    private double valor; // Custo da consulta. Se o paciente tiver plano, será 0.

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doutor getDoutor() {
        return doutor;
    }
    public void setDoutor(Doutor doutor) {
        this.doutor = doutor;
    }

    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
}
