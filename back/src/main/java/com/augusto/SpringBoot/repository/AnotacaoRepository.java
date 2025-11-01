package com.augusto.SpringBoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.augusto.SpringBoot.entity.Anotacao;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long>{

    public List<Anotacao> findByUsuarioNomeAndTextoContains(String nomeUsuario, String texto);
    
}