package com.componentes.actualizacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.componentes.actualizacion.modelos.VariablesGoblalesModel;
import com.componentes.actualizacion.repositorys.VariablesGlobalesRepository;

@Service
public class VariablesGlobalesService {

    @Autowired
    private VariablesGlobalesRepository repository;

    public List<VariablesGoblalesModel> listar() {
        return this.repository.findAll();
    }

    public void guardar(VariablesGoblalesModel model) {
        this.repository.save(model);
    }

    public VariablesGoblalesModel buscarId(long id) {
        return this.repository.findById(id).orElse(null);
    }

    public void Eliminar(long id) {
        this.repository.deleteById(id);
    }
}
