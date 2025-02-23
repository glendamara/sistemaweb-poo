package br.appLogin.appLogin.repository;

import br.appLogin.appLogin.model.Paciente;
import org.springframework.data.repository.CrudRepository;

public interface PacienteRepository extends CrudRepository<Paciente, Long> {

    // Método para autenticação (login)
    Paciente findByEmailAndSenha(String email, String senha);
}
