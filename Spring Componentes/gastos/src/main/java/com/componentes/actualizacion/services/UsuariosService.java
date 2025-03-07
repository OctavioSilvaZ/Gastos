package com.componentes.actualizacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.componentes.actualizacion.modelos.UsuariosModel;
import com.componentes.actualizacion.repositorys.UsuariosRepository;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository repository;

    @Autowired
    private EstadoService estadoService;

    public List<UsuariosModel> listar() {
        return this.repository.findAll();
    }

    public void guardar(UsuariosModel model) {
        this.repository.save(model);
    }

    public UsuariosModel buscarId(long id) {
        return this.repository.findById(id).orElse(null);
    }

    public void Eliminar(long id) {
        this.repository.deleteById(id);
    }

    public UsuariosModel buscarCorreo(String correo) {
        return this.repository.findByCorreo(correo);
    }

    public UsuariosModel bucarCorreoActivo(String correo) {

        return this.repository.findByCorreoAndEstadosId(correo, this.estadoService.buscarId(1L)).orElse(null);
    }
}
