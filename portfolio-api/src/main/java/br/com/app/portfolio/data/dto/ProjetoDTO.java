package br.com.app.portfolio.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class ProjetoDTO {

    private Long id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataPrevisaoFim;
    private LocalDate dataFim;
    private String descricao;
    private String status;
    private Double orcamento;
    private String risco;
    private Long idgerente;

    public ProjetoDTO() {
    }
}