package com.componentes.actualizacion.controllers;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//el corss controla los clientes en un dominio diferente hagan solicitudes a tu servidor
//origin puede ser un arreglo y se especifican las rutas que pueden hacer solicitudes
//maxAge son el maximo de solicitudes aceptadas
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<?> metodo_get() {
        return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
            {
                put("mensaje", "Método Get - Hola Mundo");
                put("estado", "Ok");
            }
        });
    }
}
