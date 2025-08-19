package com.porject.ForoHub.controller;

import com.porject.ForoHub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){
        /**
         * Este método maneja la creación (registro) de un nuevo médico desde una solicitud HTTP POST.
         * Devuelve un ResponseEntity con el estado 201 Created, la URI del nuevo recurso y los datos del médico creado.
         */
        var topico = new Topico(datos); // Crea un nuevo objeto Medico usando los datos recibidos en el request.
        repository.save(topico); // Guarda el nuevo médico en la base de datos.
        /**
         * Se construye la URI del recurso recién creado para enviarla en la respuesta.
         * Esto es parte del estándar REST, que recomienda que cuando creas un recurso nuevo, devuelvas la ubicación
         * donde se puede acceder.
         * uriComponentsBuilder permite construir rutas dinámicamente a partir de una plantilla, en este caso "/medicos/{id}".
         * buildAndExpand(medico.getId()) reemplaza {id} por el ID real del médico que acabamos de guardar.
         * toUri() convierte esa ruta en un objeto URI válido que se puede usar en la respuesta.
         */
        var uri = uriComponentsBuilder.path("/medicos/{id}")// Plantilla de la ruta para el nuevo recurso
                .buildAndExpand(topico.getId())// Reemplaza {id} por el ID real del médico
                .toUri();// Convierte la ruta en un objeto URI
        /**
         * Finalmente, construimos y devolvemos un ResponseEntity con:
         * - Código HTTP 201 (Created), que indica que un nuevo recurso fue creado exitosamente.
         * - La URI del nuevo recurso (para que el cliente sepa dónde encontrarlo).
         * - El cuerpo de la respuesta contiene un DTO con los detalles del médico creado
         * (para mostrar al cliente la información del recurso).
         */
        return ResponseEntity
                .created(uri)// Establece el código HTTP 201 Created y la cabecera "Location" con la URI del nuevo recurso
                .body(new DatosDetalleTopico(topico));// En el cuerpo se devuelven los datos del médico en un formato adecuado para el cliente
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listarTopicos(@PageableDefault(size=10,sort={"titulo"}) Pageable paginacion){
        var page = repository.findAllByStatusTrue(paginacion).map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizacionTopico datos){
        var topico = repository.getReferenceById(datos.id());
        topico.actualizarInformacion(datos);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity desactivarTopico(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        topico.eliminar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity obtenerUnTopico(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }
}
