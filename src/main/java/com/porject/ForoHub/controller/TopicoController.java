package com.porject.ForoHub.controller;

import com.porject.ForoHub.domain.topico.DatosListaTopico;
import com.porject.ForoHub.domain.topico.DatosRegistroTopico;
import com.porject.ForoHub.domain.topico.Topico;
import com.porject.ForoHub.domain.topico.TopicoRepository;
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
    public void registrar(@RequestBody @Valid DatosRegistroTopico datos){
        /**
         * @RequestBody sirve para indicar que el parametro que estamos recibiendo en el metodo es parte especificamente
         * del body de la request. Y no la demas informacion que no necesitamos
         */
        System.out.println(datos);
        //repository.save(new Topico(datos));
//        var topico = new Topico(datos);
//        repository.save(topico);
    }

    @GetMapping
    public Page<DatosListaTopico> listarTopicos(@PageableDefault(size=10,sort={"titulo"}) Pageable paginacion){
        return repository.findAll(paginacion).map(DatosListaTopico::new);
    }
}
