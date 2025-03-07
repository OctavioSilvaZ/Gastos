package com.componentes.actualizacion.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.componentes.actualizacion.modelos.ProveedoresModel;

public interface ProveedoresRepository extends JpaRepository<ProveedoresModel, Long> {

}
