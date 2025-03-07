package com.componentes.actualizacion.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.componentes.actualizacion.modelos.EstadosModel;

public interface EstadosRepository extends JpaRepository<EstadosModel, Long> {

    List<EstadosModel> findByIdIn(List<Long> id);

}
