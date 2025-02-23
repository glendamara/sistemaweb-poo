package br.appLogin.appLogin.repository;

import br.appLogin.appLogin.model.ListaEspera;
import br.appLogin.appLogin.model.Doutor;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaEsperaRepository extends JpaRepository<ListaEspera, Long> {
    List<ListaEspera> findByDoutorAndData(Doutor doutor, Date data); // Busca por médico e data
}
