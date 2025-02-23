package br.appLogin.appLogin.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.appLogin.appLogin.model.Consulta;
import br.appLogin.appLogin.model.Doutor;
import br.appLogin.appLogin.model.Paciente;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // Listar consultas de um médico em um status específico
    List<Consulta> findByDoutorAndStatus(Doutor doutor, String status);

    // Listar consultas de um paciente
    List<Consulta> findByPaciente(Paciente paciente);

    // Consultas de um doutor em uma data específica
    List<Consulta> findByDoutorAndData(Doutor doutor, Date data);
}
