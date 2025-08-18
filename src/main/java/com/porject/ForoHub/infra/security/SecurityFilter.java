package com.porject.ForoHub.infra.security;

import com.porject.ForoHub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Se llama al método recuperarToken para obtener el token JWT desde el encabezado "Authorization" de la solicitud.
        var tokenJWT = recuperarToken(request);
        if(tokenJWT != null){
            //Obtenemo el usuario que se logeo a partir del token
            var subject = tokenService.getSubject(tokenJWT);
            //Obtengo el usuario de la base de datos
            var usuario = repository.findByLogin(subject);
            //Creo una autenticacion que se la enviamos a setAutentication
            var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
            /**
             * Guarda la información del usuario autenticado en el contexto de seguridad de Spring.
             * Esto es necesario para que las siguientes peticiones dentro del mismo request
             * reconozcan al usuario como autenticado y puedan aplicar reglas de autorización.
             */
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //Sino viene ningún token, entonces encárgate tú de revisar si el usuario está logeado
        /**
         * Llama al siguiente filtro en la cadena (puede ser otro filtro o el controlador si este es el último).
         * Es obligatorio para que la petición siga su curso. Si no llamas a esto, la petición se detiene aquí.
         */
        filterChain.doFilter(request,response);
    }

    // Este método intenta recuperar el token JWT desde el encabezado Authorization de la solicitud.
    private String recuperarToken(HttpServletRequest request) {
        // Obtiene el valor del header "Authorization" (donde típicamente se envía el token JWT).
        var authorizationHeader = request.getHeader("Authorization");
        // Si el encabezado no está presente, lanza una excepción (esto lo puedes personalizar).
        if (authorizationHeader != null){
            // Devuelve el token, quitando la palabra "Bearer" al inicio.
            return authorizationHeader.replace("Bearer","").trim();
            /**
             * Tuve que agregar el trim() al final porque por alguna razón al generar o verificar el token,
             * se le agregaban espacios. Lo que provocaba que se muriera el programa principalmente al hacer la
             * verificación
             */
        }
        return null;
    }

}
