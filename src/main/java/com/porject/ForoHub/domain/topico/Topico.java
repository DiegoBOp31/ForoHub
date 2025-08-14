package com.porject.ForoHub.domain.topico;

import com.porject.ForoHub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public class Topico {
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    private String curso;
    private Usuario usuario;
}
