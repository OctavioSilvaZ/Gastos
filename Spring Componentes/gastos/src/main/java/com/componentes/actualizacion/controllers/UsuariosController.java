package com.componentes.actualizacion.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.componentes.actualizacion.dto.UsuarioRequestDto;
import com.componentes.actualizacion.dto.UsuarioResponseDto;
import com.componentes.actualizacion.modelos.UsuariosModel;
import com.componentes.actualizacion.services.EstadoService;
import com.componentes.actualizacion.services.PerfilService;
import com.componentes.actualizacion.services.UsuariosService;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UsuariosController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private PerfilService perfilService;

    @GetMapping("/v1/usuarios")
    public ResponseEntity<?> usuarios() {

        List<UsuarioResponseDto> lista = new ArrayList<>();
        List<UsuariosModel> datos = this.usuariosService.listar();

        datos.forEach((dato) -> {
            lista.add(new UsuarioResponseDto(dato.getId(), dato.getNombre(), dato.getCorreo(), dato.getPerfilId().getNombre(), dato.getPerfilId().getId(),
                    dato.getEstadosId().getId(), dato.getEstadosId().getNombre()));
        });
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @PostMapping("/auth/usuarios")
    public ResponseEntity<?> crearUser(@RequestBody UsuarioRequestDto dto) {

        UsuariosModel usuario = this.usuariosService.buscarCorreo(dto.getCorreo());

        if (usuario == null) {
            this.usuariosService.guardar(new UsuariosModel(dto.getNombre(), dto.getCorreo(),
                    this.bCryptPasswordEncoder.encode(dto.getPassword()), "", new Date(),
                    this.perfilService.buscarId(2l), this.estadoService.buscarId(1l)));

            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
                {
                    put("mensaje", "Se creo el registro correctamente");
                }
            });
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
                {
                    put("mensaje", "Ocurrió un error inesperado");
                }
            });
        }
    }

    @PutMapping("/v1/usuarios/{id}")
    public ResponseEntity<?> editarUser(@PathVariable("id") Long id, @RequestBody UsuarioRequestDto dto) {
        UsuariosModel user = this.usuariosService.buscarId(id);

        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
                {
                    put("mensaje", "Ocurrió un error inesperado");
                }
            });
        } else {
            user.setNombre(dto.getNombre());
            user.setCorreo(dto.getCorreo());
            user.setPassword(this.bCryptPasswordEncoder.encode(dto.getPassword()));
            this.usuariosService.guardar(user);
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
                {
                    put("mensaje", "Se modifico correctamente a el usuario");
                }
            });
        }
    }
}
