package com.componentes.actualizacion.dto;

import lombok.Data;

@Data
public class GastosPorDiaDto {

    private float neto;
    private float iva;
    private String descripcion;
    private Long proveedoresId;
}
