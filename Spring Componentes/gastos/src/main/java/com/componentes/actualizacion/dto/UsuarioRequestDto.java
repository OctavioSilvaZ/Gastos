package com.componentes.actualizacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UsuarioRequestDto {

    private String nombre;
    private String correo;
    private String password;
}
