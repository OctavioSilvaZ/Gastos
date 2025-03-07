package com.componentes.actualizacion.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.componentes.actualizacion.dto.ProveedoresRequestDto;
import com.componentes.actualizacion.modelos.ProveedoresModel;
import com.componentes.actualizacion.services.ProveedoresService;

//el corss controla los clientes en un dominio diferente hagan solicitudes a tu servidor
//origin puede ser un arreglo y se especifican las rutas que pueden hacer solicitudes
//maxAge son el maximo de solicitudes aceptadas
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ProveedoresController {

    @Autowired
    private ProveedoresService proveedoresService;

    @GetMapping("/proveedores")
    public ResponseEntity<?> proveedores() {
        return ResponseEntity.status(HttpStatus.OK).body(this.proveedoresService.listar());
    }

    @GetMapping("/proveedores/{id}")
    public ResponseEntity<?> proveedoresPorId(@PathVariable("id") Long id) {
        ProveedoresModel datos = this.proveedoresService.buscarId(id);

        if (datos == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
                {
                    put("mensaje", "No se encotró el id ");
                }
            });
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(datos);
        }

    }

    @PostMapping("/proveedores")
    public ResponseEntity<?> metodo_post(@RequestBody ProveedoresRequestDto dto) {

        try {
            this.proveedoresService.guardar(new ProveedoresModel(dto.getNombre()));
            return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
                {
                    put("mensaje", "Se creo el registro exitosamente");
                }
            });
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
                {
                    put("mensaje", "Ocurrió un error inesperado");
                }
            });
        }
    }

    @PutMapping("/proveedores/{id}")
    public ResponseEntity<?> metodo_put(@PathVariable("id") Long id, @RequestBody ProveedoresRequestDto dto) {
        ProveedoresModel proveedor = this.proveedoresService.buscarId(id);
        if (proveedor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
                {
                    put("mensaje", "No se encontro el proveedor");
                }
            });
        } else {
            proveedor.setNombre(dto.getNombre());
            this.proveedoresService.guardar(proveedor);
            return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
                {
                    put("mensaje", "Se modificó exitosamente el proveedor");
                }
            });
        }
    }
}
