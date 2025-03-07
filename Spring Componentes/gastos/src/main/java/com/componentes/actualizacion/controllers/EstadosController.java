package com.componentes.actualizacion.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.componentes.actualizacion.services.EstadoService;

//el corss controla los clientes en un dominio diferente hagan solicitudes a tu servidor
//origin puede ser un arreglo y se especifican las rutas que pueden hacer solicitudes
//maxAge son el maximo de solicitudes aceptadas
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1")
public class EstadosController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/estados")
    public ResponseEntity<?> metodo_get() {
        return ResponseEntity.status(HttpStatus.OK).body(this.estadoService.listar());
    }

    @GetMapping("/estados-gastos")
    public ResponseEntity<?> metodo_get_gastos() {
        List<Long> ids = new ArrayList<>();
        ids.add(3L); //añadir L o poner long es lo mismo para pasar un número a long
        ids.add((long) 4);
        return ResponseEntity.status(HttpStatus.OK).body(this.estadoService.listarParaGastos(ids));
    }

}
