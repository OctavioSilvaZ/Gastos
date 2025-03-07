package com.componentes.actualizacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.componentes.actualizacion.modelos.GastosPorDiaModel;
import com.componentes.actualizacion.repositorys.GastosPorDiasRepository;

@Service
public class GastosPorDiaService {

    @Autowired
    private GastosPorDiasRepository repository;

    public List<GastosPorDiaModel> listar() {
        return this.repository.findAll();
    }

    public void guardar(GastosPorDiaModel model) {
        this.repository.save(model);
    }

    public GastosPorDiaModel buscarId(long id) {
        return this.repository.findById(id).orElse(null);
    }

    public void Eliminar(long id) {
        this.repository.deleteById(id);
    }

    public List<GastosPorDiaModel> listarMes(Integer mes, Integer anio) {
        return this.repository.findAllByMonth(mes, anio);
    }

}
