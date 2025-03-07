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

import com.componentes.actualizacion.dto.GastosPorDiaDto;
import com.componentes.actualizacion.modelos.GastosPorDiaModel;
import com.componentes.actualizacion.modelos.ProveedoresModel;
import com.componentes.actualizacion.services.GastosPorDiaService;
import com.componentes.actualizacion.services.ProveedoresService;

//el corss controla los clientes en un dominio diferente hagan solicitudes a tu servidor
//origin puede ser un arreglo y se especifican las rutas que pueden hacer solicitudes
//maxAge son el maximo de solicitudes aceptadas
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class GastosPorDiaController {

    @Autowired
    private GastosPorDiaService gastosPorDiasService;

    @Autowired
    private ProveedoresService proveedoresService;

    @GetMapping("/gastos-dia-mes-actual")
    public ResponseEntity<?> gastos_fijos_por_dia_actual() {

        LocalDate fechaActual = LocalDate.now();

        return ResponseEntity.status(HttpStatus.OK).body(this.gastosPorDiasService.listarMes(fechaActual.getMonthValue(), fechaActual.getYear()));
    }

    @GetMapping("/gastos-dia-mes/{mes}")
    public ResponseEntity<?> gastos_por_dia_por_mes_especifico(@PathVariable("mes") Integer mes) {

        LocalDate fechaActual = LocalDate.now();
        return ResponseEntity.status(HttpStatus.OK).body(this.gastosPorDiasService.
                listarMes(mes, fechaActual.getYear()));
    }

    @PostMapping("/gastos-por-dia")
    public ResponseEntity<?> crearGasto(@RequestBody GastosPorDiaDto dto) {
        ProveedoresModel proveedor = this.proveedoresService.buscarId(dto.getProveedoresId());
        float iva = dto.getIva();
        float total;

        if (iva > 100) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
                {
                    put("mensaje", "El IVA debe ser menor o igual a 100");
                }
            });
        } else {
            iva = iva / 100;
            float sub_iva = dto.getNeto() * iva;
            total = dto.getNeto() + sub_iva;
        }
        if (proveedor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
                {
                    put("mensaje", "No se encontro el proveedor");
                }
            });
        }
        try {
            this.gastosPorDiasService.guardar(new GastosPorDiaModel(dto.getNeto(), dto.getIva(), total,
                    new Date(), dto.getDescripcion(), proveedor));

            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
                {
                    put("mensaje", "Se creó el registro existosamente");
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

    @PutMapping("/gastos-por-dia/{id}")
    public ResponseEntity<?> modificarGastoFijo(@PathVariable("id") Long id, @RequestBody GastosPorDiaDto dto) {

        GastosPorDiaModel datos = this.gastosPorDiasService.buscarId(id);
        ProveedoresModel proveedor = this.proveedoresService.buscarId(dto.getProveedoresId());

        float iva = dto.getIva();
        float total;

        if (iva > 100) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
                {
                    put("mensaje", "El IVA debe ser menor o igual a 100");
                }
            });
        } else {
            iva = iva / 100;
            float sub_iva = dto.getNeto() * iva;
            total = dto.getNeto() + sub_iva;
        }

        if (id == null || proveedor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
                {
                    put("mensaje", "Ocurrio un error inesperado");
                }
            });
        } else {
            datos.setNeto(dto.getNeto());
            datos.setIva(dto.getIva());
            datos.setTotal(total);
            datos.setDescripcion(dto.getDescripcion());
            datos.setProveedoresId(proveedor);
            this.gastosPorDiasService.guardar(datos);
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
                {
                    put("mensaje", "Se actualizó el registro exitosamente");
                }
            });
        }
    }

    @DeleteMapping("/gastos-por-dia/{id}")
    public ResponseEntity<?> eliminarGastoFijo(@PathVariable("id") Long id) {

        GastosPorDiaModel datos = this.gastosPorDiasService.buscarId(id);

        if (datos == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
                {
                    put("mensaje", "No se encontro el gasto");
                }
            });
        } else {
            this.gastosPorDiasService.Eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
                {
                    put("mensaje", "Se elimino el registro exitosamente");
                }
            });
        }
    }
}
