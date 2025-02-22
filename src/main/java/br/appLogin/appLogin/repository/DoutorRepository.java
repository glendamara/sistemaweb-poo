package br.appLogin.appLogin.repository;

import org.springframework.data.repository.CrudRepository;

import br.appLogin.appLogin.model.Doutor;



public interface DoutorRepository extends CrudRepository<Doutor, String> {

	Doutor findById(long id);
	
}
