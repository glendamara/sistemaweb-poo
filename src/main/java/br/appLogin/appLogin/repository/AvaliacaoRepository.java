package br.appLogin.appLogin.repository;

import br.appLogin.appLogin.model.Avaliacao;
import br.appLogin.appLogin.model.Doutor;
import br.appLogin.appLogin.model.Consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    // Listar avaliações de um médico
    List<Avaliacao> findByDoutor(Doutor doutor);

    // Avaliações relacionadas a uma consulta

}
