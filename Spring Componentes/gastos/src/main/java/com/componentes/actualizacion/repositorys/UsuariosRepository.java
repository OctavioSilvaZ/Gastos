package com.componentes.actualizacion.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.componentes.actualizacion.modelos.EstadosModel;
import com.componentes.actualizacion.modelos.UsuariosModel;

public interface UsuariosRepository extends JpaRepository<UsuariosModel, Long> {

    UsuariosModel findByCorreo(String correo);

    Optional<UsuariosModel> findByCorreoAndEstadosId(String correo, EstadosModel estado);

}
