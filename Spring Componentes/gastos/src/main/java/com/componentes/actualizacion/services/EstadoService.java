package com.componentes.actualizacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.componentes.actualizacion.modelos.EstadosModel;
import com.componentes.actualizacion.repositorys.EstadosRepository;

@Service
@Primary
public class EstadoService {

    @Autowired
    private EstadosRepository repository;

    public List<EstadosModel> listar() {
        return this.repository.findAll();
    }

    public List<EstadosModel> listarParaGastos(List<Long> id) {
        return this.repository.findByIdIn(id);
    }

    public void guardar(EstadosModel model) {
        this.repository.save(model);
    }

    public EstadosModel buscarId(long id) {
        return this.repository.findById(id).orElse(null);
    }

    public void Eliminar(long id) {
        this.repository.deleteById(id);
    }
}
