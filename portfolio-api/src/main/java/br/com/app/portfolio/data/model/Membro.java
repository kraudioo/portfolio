package br.com.app.portfolio.data.model;



import br.com.app.portfolio.data.model.pk.MembroPK;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "membros")
public class Membro implements Serializable {


    @EmbeddedId
    MembroPK id;

    public MembroPK getId() {
        return id;
    }

    public void setId(MembroPK id) {
        this.id = id;
    }
}