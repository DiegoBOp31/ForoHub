package com.porject.ForoHub.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter//Crea todos los getters en tiempo de compilación
@NoArgsConstructor//Crea un constructor sin argumentos
@AllArgsConstructor//Crea un constructor con todos los argumentos
@EqualsAndHashCode(of = "id")//El sistema identifica que dos objetos son iguales si tienen el mismo id

public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String contrasenia;

    // Retorna la lista de roles/permisos del usuario.
    // En este caso, asigna por defecto el rol "ROLE_USER" para control de acceso en Spring Security.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // Métodos obligatorios al implementar UserDetails de Spring Security.
    // Se usan para que el sistema conozca usuario, contraseña y estado de la cuenta.
    @Override
    public String getPassword() {
        return contrasenia;
    }// Contraseña del usuario

    @Override
    public String getUsername() {
        return login;
    }// Identificador o nombre de usuario

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }// La cuenta no está expirada

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }// La cuenta no está bloqueada

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }// La contraseña sigue vigente

    @Override
    public boolean isEnabled() {
        return true;
    }// El usuario está habilitado
}