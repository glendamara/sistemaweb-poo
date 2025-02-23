package br.appLogin.appLogin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Doutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    @Column(unique = true) // Garante que o email seja único
    private String email;

    @NotEmpty
    private String senha;

    @NotEmpty
    private String especialidade;

    @NotEmpty
    private String planoAtendido; // Plano de saúde que o médico aceita

    private Double mediaEstrelas; // Média de avaliações feitas pelos pacientes

    @OneToMany(mappedBy = "doutor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacoes;

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getPlanoAtendido() {
        return planoAtendido;
    }
    public void setPlanoAtendido(String planoAtendido) {
        this.planoAtendido = planoAtendido;
    }

    public Double getMediaEstrelas() {
        return mediaEstrelas;
    }
    public void setMediaEstrelas(Double mediaEstrelas) {
        this.mediaEstrelas = mediaEstrelas;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }
    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
