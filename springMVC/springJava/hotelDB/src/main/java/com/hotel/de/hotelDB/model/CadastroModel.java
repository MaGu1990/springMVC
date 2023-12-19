package com.hotel.de.hotelDB.model;

import jakarta.persistence.*;
import org.hibernate.boot.jaxb.hbm.internal.RepresentationModeConverter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "CADASTRO_CLIENTE")
public class CadastroModel extends RepresentationModel<CadastroModel> implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID IdCadastro;

    private string name;

    private string email;

    public UUID getIdCadastro() {
        return IdCadastro;
    }

    public void setIdCadastro(UUID idCadastro) {
        IdCadastro = idCadastro;
    }

    public string getName() {
        return name;
    }

    public void setName(string name) {
        this.name = name;
    }

    public string getEmail() {
        return email;
    }

    public void setEmail(string email) {
        this.email = email;
    }
}
