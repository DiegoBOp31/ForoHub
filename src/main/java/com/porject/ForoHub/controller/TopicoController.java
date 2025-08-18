package com.porject.ForoHub.controller;

import com.porject.ForoHub.domain.topico.DatosRegistroTopico;
import com.porject.ForoHub.domain.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody DatosRegistroTopico datos){
        /**
         * @RequestBody sirve para indicar que el parametro que estamos recibiendo en el metodo es parte especificamente
         * del body de la request. Y no la demas informacion que no necesitamos
         */
        System.out.println(datos);
//        var topico = new Topico(datos);
//        repository.save(topico);
    }
}
