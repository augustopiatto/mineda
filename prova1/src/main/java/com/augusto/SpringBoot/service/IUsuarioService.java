package com.augusto.SpringBoot.service;

import java.util.List;

import com.augusto.SpringBoot.entity.Usuario;

public interface IUsuarioService {
    
    public Usuario buscarPorId(Long id);

    public Usuario novoUsuario(Usuario usuario);

    public List<Usuario> buscarTodos();

}