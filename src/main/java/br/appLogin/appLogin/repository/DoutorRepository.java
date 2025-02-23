package br.appLogin.appLogin.repository;

import br.appLogin.appLogin.model.Doutor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DoutorRepository extends JpaRepository<Doutor, Long> {

    // Login do médico
    Doutor findByEmailAndSenha(String email, String senha);

    // Listar médicos que aceitam determinado plano de saúde
    List<Doutor> findByPlanoAtendido(String planoSaude);
    
    
    
    // Busca por nome ou especialidade (usado na filtragem)
    @Query("SELECT d FROM Doutor d WHERE " +
            "(:nome IS NULL OR LOWER(d.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:especialidade IS NULL OR LOWER(d.especialidade) LIKE LOWER(CONCAT('%', :especialidade, '%')))")
    List<Doutor> findAllByNomeOrEspecialidade(String nome, String especialidade);

    // Busca por plano atendido, nome ou especialidade combinados
    @Query("SELECT d FROM Doutor d WHERE " +
            "(:planoAtendido IS NULL OR LOWER(d.planoAtendido) = LOWER(:planoAtendido)) AND " +
            "(:nome IS NULL OR LOWER(d.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:especialidade IS NULL OR LOWER(d.especialidade) LIKE LOWER(CONCAT('%', :especialidade, '%')))")
    List<Doutor> findByPlanoAtendidoAndNomeOrEspecialidade(String planoAtendido, String nome, String especialidade);
}
