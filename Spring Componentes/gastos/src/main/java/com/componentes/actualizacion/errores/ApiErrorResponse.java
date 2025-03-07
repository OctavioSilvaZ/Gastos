package com.componentes.actualizacion.errores;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor

@Data
public class ApiErrorResponse {

    private int codigo;
    private String mensaje;

}
