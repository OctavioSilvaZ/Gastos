package com.componentes.actualizacion.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "proveedores")
@Data
public class ProveedoresModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public ProveedoresModel() {
        super();
    }

    public ProveedoresModel(String nombre) {
        super();
        this.nombre = nombre;
    }

}
