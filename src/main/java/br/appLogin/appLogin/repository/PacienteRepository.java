package br.appLogin.appLogin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.appLogin.appLogin.model.Paciente;

public interface PacienteRepository extends CrudRepository<Paciente, String> {

	Paciente findById(long id);
	
	@Query(value="select * from applogin.paciente where email = :email and senha = :senha", nativeQuery = true)
	public Paciente login(String email, String senha);
	
}
