package br.com.app.portfolio.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class PessoaDTO {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private Boolean funcionario;

    public PessoaDTO() {
    }
}