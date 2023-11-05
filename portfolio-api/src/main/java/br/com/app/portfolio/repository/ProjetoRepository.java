package br.com.app.portfolio.repository;

import br.com.app.portfolio.data.model.Pessoa;
import br.com.app.portfolio.data.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {


    @Query(value = "select id+1 from projeto  order by id desc limit 1", nativeQuery = true)
    public Long getNextValMySequence();




}