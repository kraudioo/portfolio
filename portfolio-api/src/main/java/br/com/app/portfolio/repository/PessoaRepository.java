package br.com.app.portfolio.repository;

import br.com.app.portfolio.data.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@EnableJpaRepositories
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {


    @Query(value = "select id+1 from pessoa  order by id desc limit 1", nativeQuery = true)
    public Long getNextValMySequence();


}