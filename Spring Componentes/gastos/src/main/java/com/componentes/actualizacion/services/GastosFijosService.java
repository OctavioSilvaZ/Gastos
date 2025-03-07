package com.componentes.actualizacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.componentes.actualizacion.modelos.GastosFijosModel;
import com.componentes.actualizacion.repositorys.GastosFijosRepository;

@Service

public class GastosFijosService {

    @Autowired
    private GastosFijosRepository repository;

    public List<GastosFijosModel> listar() {
        return this.repository.findAll();
    }

    public void guardar(GastosFijosModel model) {
        this.repository.save(model);
    }

    public GastosFijosModel buscarId(long id) {
        return this.repository.findById(id).orElse(null);
    }

    public void Eliminar(long id) {
        this.repository.deleteById(id);
    }

    public List<GastosFijosModel> listarMes(Integer mes, Integer anio) {
        return this.repository.findAllByMonth(mes, anio);
    }

}
