package com.componentes.actualizacion.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.componentes.actualizacion.JWT.JWTService;
import com.componentes.actualizacion.dto.JwtResponseDto;
import com.componentes.actualizacion.dto.LoginDto;
import com.componentes.actualizacion.modelos.UsuariosModel;
import com.componentes.actualizacion.services.UsuariosService;
//el corss controla los clientes en un dominio diferente hagan solicitudes a tu servidor
//origin puede ser un arreglo y se especifican las rutas que pueden hacer solicitudes
//maxAge son el maximo de solicitudes aceptadas

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private JWTService jWTService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {

        UsuariosModel usuario = this.usuariosService.bucarCorreoActivo(dto.getCorreo());

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
                {
                    put("mensaje", "No se encontro el usuario");
                }
            });
        } else {
            if (this.passwordEncoder.matches(dto.getPassword(), usuario.getPassword())) {
                String token = this.jWTService.generateToken(usuario.getCorreo());
                return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
                    {
                        put("token", token);
                        put("nombre", usuario.getNombre());
                        put("estado", String.valueOf(usuario.getEstadosId().getId()));
                        put("id_usuario", String.valueOf(usuario.getId()));
                        put("Rol", usuario.getPerfilId().getNombre());
                    }
                });
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
                    {
                        put("mensaje", "Las credenciales ingresadas no son válidas");
                    }
                });
            }
        }
    }

    @GetMapping("/refresh/{id}")
    public ResponseEntity<?> refresh(@PathVariable("id") Long id) {
        UsuariosModel usuario = this.usuariosService.buscarId(id);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
                {
                    put("mensaje", "No se encontro el usuario");
                }
            });
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new JwtResponseDto(usuario.getId(),
                    usuario.getNombre(), usuario.getPerfilId().getNombre(), usuario.getPerfilId().getId(),
                    this.jWTService.generateToken(usuario.getCorreo())));
        }
    }
}
