package com.componentes.actualizacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.componentes.actualizacion.modelos.ProveedoresModel;
import com.componentes.actualizacion.repositorys.ProveedoresRepository;

@Service

public class ProveedoresService {

    @Autowired
    private ProveedoresRepository repository;

    public List<ProveedoresModel> listar() {
        return this.repository.findAll();
    }

    public void guardar(ProveedoresModel model) {
        this.repository.save(model);
    }

    public ProveedoresModel buscarId(long id) {
        return this.repository.findById(id).orElse(null);
    }

    public void Eliminar(long id) {
        this.repository.deleteById(id);
    }

}
