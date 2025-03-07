package com.componentes.actualizacion.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.componentes.actualizacion.modelos.PerfilModel;

public interface PerfilRepository extends JpaRepository<PerfilModel, Long> {

}
