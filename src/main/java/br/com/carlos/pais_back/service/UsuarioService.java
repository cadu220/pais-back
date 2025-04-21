package br.com.carlos.pais_back.service;

import br.com.carlos.pais_back.model.Usuario;
import br.com.carlos.pais_back.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario saveUser(Usuario bean){
        return this.usuarioRepository.save(bean);
    }

    public Usuario findByLogin(String login){
        return this.usuarioRepository.findByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }

    public List<Usuario> listUser(){
        return usuarioRepository.findAll();
    }
}
