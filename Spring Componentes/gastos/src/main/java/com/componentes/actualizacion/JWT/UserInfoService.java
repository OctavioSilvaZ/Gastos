package com.componentes.actualizacion.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.componentes.actualizacion.modelos.UsuariosModel;
import com.componentes.actualizacion.services.EstadoService;
import com.componentes.actualizacion.services.UsuariosService;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private EstadoService estadoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuariosModel userDetails = this.usuariosService.bucarCorreoActivo(username);

        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new UserInfoDetails(userDetails);
    }
}
