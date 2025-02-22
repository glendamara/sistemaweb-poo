package br.appLogin.appLogin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Paciente {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String nome;
	@NotEmpty
	private String senha;
	
	
	private String plano;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String idade;
	
	
	
	
	
	
	
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getPlano() {
		return plano;
	}
	public void setPlano(String plano) {
		this.plano = plano;
	}
	public long getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
