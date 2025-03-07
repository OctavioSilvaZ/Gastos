package com.componentes.actualizacion.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.componentes.actualizacion.modelos.GastosPorDiaModel;

public interface GastosPorDiasRepository extends JpaRepository<GastosPorDiaModel, Long> {

    //HQL
    @Query(name = "GastosPorDiaModel.findAllByMonth",
            value = "Select E FROM GastosPorDiaModel E WHERE MONTH(E.fecha)= :mes and YEAR(E.fecha)= :anio")
    List<GastosPorDiaModel> findAllByMonth(@Param("mes") Integer mes, @Param("anio") Integer anio);

}
