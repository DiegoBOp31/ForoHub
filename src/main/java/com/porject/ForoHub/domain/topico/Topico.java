package com.porject.ForoHub.domain.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter//Crea todos los getters en tiempo de compilación
@NoArgsConstructor//Crea un constructor sin argumentos
@AllArgsConstructor//Crea un constructor con todos los argumentos
@EqualsAndHashCode(of = "id")//El sistema identifica que dos objetos son iguales si tienen el mismo id

@Table(name = "topicos")
@Entity(name = "Topico")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    private String autor;
    private String nombreCurso;

    public Topico(DatosRegistroTopico datos) {
        this.id = null;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now(); //aquí se asigna automáticamente la fecha
        this.status = datos.status();
        this.autor = datos.autor();
        this.nombreCurso = datos.nombreCurso();
    }

    public void actualizarInformacion(@Valid DatosActualizacionTopico datos) {
        if(datos.titulo()!=null){
            this.titulo = datos.titulo();
        }
        if(datos.mensaje()!=null){
            this.mensaje = datos.mensaje();
        }
    }
}
