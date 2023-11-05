package br.com.app.portfolio.data.model.pk;

import br.com.app.portfolio.data.model.Pessoa;
import br.com.app.portfolio.data.model.Projeto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class MembroPK implements Serializable {
    private Long idProjeto;
    private Long idPessoa;

    // Getters e setters

    public Long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Long idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MembroPK membroPK = (MembroPK) o;
        return Objects.equals(idProjeto, membroPK.idProjeto) && Objects.equals(idPessoa, membroPK.idPessoa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProjeto, idPessoa);
    }
// Implemente equals() e hashCode() adequados
}