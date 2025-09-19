package com.augusto.SpringBoot.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.augusto.SpringBoot.entity.Correcao;
import com.augusto.SpringBoot.service.ICorrecaoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/correcao")
public class CorrecaoController {

    @Autowired
    private ICorrecaoService service;

    @GetMapping("/filtro")
    public List<Correcao> findByNotaGreaterThanAndDataAfter(
            @RequestParam Double nota, 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data) {
        return service.findByNotaGreaterThanAndDataAfter(nota, data);
    };

    @PostMapping
    public Correcao novaCorrecao(@RequestBody Correcao correcao) {
        return service.novaCorrecao(correcao);
    }

    @GetMapping
    public List<Correcao> buscarTodos() {
        return service.buscarTodos();
    }   
}
