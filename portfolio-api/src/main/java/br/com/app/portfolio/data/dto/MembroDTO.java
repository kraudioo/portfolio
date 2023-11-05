package br.com.app.portfolio.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MembroDTO {

    private Long idProjeto;
    private Long idPessoa;

    public MembroDTO() {
    }
}