package com.componentes.actualizacion.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.componentes.actualizacion.dto.GastosFijosRequestDto;
import com.componentes.actualizacion.modelos.EstadosModel;
import com.componentes.actualizacion.modelos.GastosFijosModel;
import com.componentes.actualizacion.modelos.ProveedoresModel;
import com.componentes.actualizacion.services.EstadoService;
import com.componentes.actualizacion.services.GastosFijosService;
import com.componentes.actualizacion.services.ProveedoresService;

//el corss controla los clientes en un dominio diferente hagan solicitudes a tu servidor
//origin puede ser un arreglo y se especifican las rutas que pueden hacer solicitudes
//maxAge son el maximo de solicitudes aceptadas
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1")
public class GastosFijosController {

    @Autowired
    private GastosFijosService gastosFijosService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private ProveedoresService proveedoresService;

    @GetMapping("/gastos-fijos-mes-actual")
    public ResponseEntity<?> gastos_fijos_por_mes_actual() {

        LocalDate fechaActual = LocalDate.now();

        return ResponseEntity.status(HttpStatus.OK).body(this.gastosFijosService.
                listarMes(fechaActual.getMonthValue(), fechaActual.getYear()));
    }

    @GetMapping("Gastos-fijos-mes/{mes}")
    public ResponseEntity<?> gastos_fijos_por_mes_especifico(@PathVariable("mes") Integer mes) {

        LocalDate fechaActual = LocalDate.now();
        return ResponseEntity.status(HttpStatus.OK).body(this.gastosFijosService.
                listarMes(mes, fechaActual.getYear()));
    }

    @PostMapping("/gastos-fijos")
    public ResponseEntity<?> crearGasto(@RequestBody GastosFijosRequestDto dto) {
        ProveedoresModel proveedor = this.proveedoresService.buscarId(dto.getProveedoresId());
        if (proveedor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
                {
                    put("mensaje", "No se encontro el proveedor");
                }
            });
        }
        try {
            this.gastosFijosService.guardar(new GastosFijosModel(dto.getNombre(), dto.getMonto(), new Date(),
                    this.estadoService.buscarId(4), proveedor));

            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
                {
                    put("mensaje", "Se creo el registro existosamente");
                }
            });
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
                {
                    put("mensaje", "ocurrio un error inesperado");
                }
            });
        }
    }

    @PutMapping("/gastos-fijos/{id}")
    public ResponseEntity<?> modificarGastoFijo(@PathVariable("id") Long id, @RequestBody GastosFijosRequestDto dto) {

        GastosFijosModel datos = this.gastosFijosService.buscarId(id);
        ProveedoresModel proveedor = this.proveedoresService.buscarId(dto.getProveedoresId());
        EstadosModel estado = this.estadoService.buscarId(dto.getEstadosId());

        if (id == null || proveedor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
                {
                    put("mensaje", "Ocurrio un error inesperado");
                }
            });
        } else {
            datos.setEstadosId(estado);
            datos.setMonto(dto.getMonto());
            datos.setNombre(dto.getNombre());
            datos.setProveedoresId(proveedor);
            this.gastosFijosService.guardar(datos);
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
                {
                    put("mensaje", "Se actualizó el registro exitosamente");
                }
            });
        }
    }

    @DeleteMapping("/gastos-fijos/{id}")
    public ResponseEntity<?> eliminarGastoFijo(@PathVariable("id") Long id) {

        GastosFijosModel datos = this.gastosFijosService.buscarId(id);

        if (datos == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
                {
                    put("mensaje", "No se encontro el gasto");
                }
            });
        } else {
            this.gastosFijosService.Eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
                {
                    put("mensaje", "Se elimino el registro exitosamente");
                }
            });
        }
    }
}
