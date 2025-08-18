package com.porject.ForoHub.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Esta clase está anotada con @Configuration, lo que indica que contiene configuraciones para la aplicación.
@Configuration
// La anotación @EnableWebSecurity activa la configuración personalizada de seguridad de Spring Security.
@EnableWebSecurity
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /**
         * Desactiva la protección contra CSRF (Cross-Site Request Forgery)
         */
        return http.csrf(csrf -> csrf.disable())
                /**
                 * Configura la política de manejo de sesiones para que sea "STATELESS",
                 * es decir, que no se almacene ninguna sesión del usuario en el servidor.
                 * Esto es ideal para APIs que usan tokens como JWT.
                 */
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                /**
                 * Configura las reglas de autorización para las peticiones HTTP.
                 * Específicamente, define qué endpoints requieren estar autenticado y cuáles no.
                 */
//                .authorizeHttpRequests(req-> {
//                    /**
//                     * Permite el acceso sin autenticación al endpoint POST "/login".
//                     * Esto es necesario para que los usuarios puedan enviar sus credenciales y obtener un token JWT.
//                     */
//                    req.requestMatchers(HttpMethod.POST,"/login").permitAll()
//                            /**
//                             * Permite el acceso público (sin autenticación) a las rutas relacionadas con la
//                             * documentación de la API generada por Swagger/OpenAPI:
//                             * // - "/v3/api-docs/**" → Documentación en formato JSON de OpenAPI.
//                             * // - "/swagger-ui.html" → Página principal de Swagger UI.
//                             * // - "/swagger-ui/**" → Archivos y recursos internos que usa Swagger UI.
//                             * // Esto asegura que la documentación de la API sea accesible incluso si el resto de la
//                             * aplicación requiere autenticación.
//                             */
//                            .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
//                    /**
//                     * Requiere autenticación para cualquier otra solicitud (GET, POST, PUT, DELETE, etc. en otros endpoints).
//                     * Es decir, todas las rutas excepto "/login" estarán protegidas y necesitan un token válido.
//                     */
//                    req.anyRequest().authenticated();
//                })
//                /**
//                 * Agrega el filtro personalizado (securityFilter) antes del filtro estándar de autenticación con usuario y contraseña.
//                 * Esto permite validar un JWT o realizar otras verificaciones antes de que Spring Security procese la autenticación normal.
//                 */
//                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                // build() finaliza la configuración y construye el SecurityFilterChain.
                .build();
    }

}
