package br.com.app.portfolio.repository;

import br.com.app.portfolio.data.model.Membro;
import br.com.app.portfolio.data.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MembroRepository extends JpaRepository<Membro, Long> {

@Query(value = "select idprojeto, idpessoa from membros", nativeQuery = true)
Optional<Membro>findMembros();

}