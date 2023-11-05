package br.com.app.portfolio.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "projeto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {

    @Id
    private Long id;

    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataPrevisaoFim;
    private LocalDate dataFim;
    private String descricao;
    private String status;
    private Double orcamento;
    private String risco;

    @ManyToOne
    @JoinColumn(name = "idgerente")
    private Pessoa gerente;
}