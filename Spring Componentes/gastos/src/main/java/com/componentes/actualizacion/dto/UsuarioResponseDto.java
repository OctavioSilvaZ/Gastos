package com.componentes.actualizacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UsuarioResponseDto {

    private Long id;
    private String nombre;
    private String correo;
    private String perfil;
    private Long perfil_id;
    private Long estado_id;
    private String estado;

}
