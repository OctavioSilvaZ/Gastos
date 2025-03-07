package com.componentes.actualizacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.componentes.actualizacion.modelos.PerfilModel;
import com.componentes.actualizacion.repositorys.PerfilRepository;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository repository;

    public List<PerfilModel> listar() {
        return this.repository.findAll();
    }

    public void guardar(PerfilModel model) {
        this.repository.save(model);
    }

    public PerfilModel buscarId(long id) {
        return this.repository.findById(id).orElse(null);
    }

    public void Eliminar(long id) {
        this.repository.deleteById(id);
    }

}
