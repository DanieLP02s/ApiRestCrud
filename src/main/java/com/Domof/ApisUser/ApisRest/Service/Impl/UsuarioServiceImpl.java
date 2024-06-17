package com.Domof.ApisUser.ApisRest.Service.Impl;

import com.Domof.ApisUser.ApisRest.Repository.UsuarioRepository;
import com.Domof.ApisUser.ApisRest.Service.UsuarioService;
import com.Domof.ApisUser.ApisRest.Entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerUsuariosPorId(Long id) {
        return usuarioRepository.findById(id).get();
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizarUsuarioPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarUsuarios(Long id) {
        usuarioRepository.deleteById(id);
    }
}
