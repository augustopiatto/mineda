package com.augusto.SpringBoot.service;

import java.time.LocalDateTime;
import java.util.List;

import com.augusto.SpringBoot.entity.Correcao;

public interface ICorrecaoService {
    
    public List<Correcao> findByNotaGreaterThanAndDataAfter(Double nota, LocalDateTime data);

    public Correcao novaCorrecao(Correcao correcao);

    public List<Correcao> buscarTodos();

}