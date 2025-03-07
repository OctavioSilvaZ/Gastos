package com.componentes.actualizacion.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.componentes.actualizacion.modelos.GastosFijosModel;

public interface GastosFijosRepository extends JpaRepository<GastosFijosModel, Long> {

    //HQL
    @Query(name = "GastosFijosModel.findAllByMonth",
            value = "Select E FROM GastosFijosModel E WHERE MONTH(E.fecha)= :mes and YEAR(E.fecha)= :anio")
    List<GastosFijosModel> findAllByMonth(@Param("mes") Integer mes, @Param("anio") Integer anio);

}
