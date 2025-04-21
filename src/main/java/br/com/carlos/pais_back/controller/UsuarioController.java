package br.com.carlos.pais_back.controller;

import br.com.carlos.pais_back.DTO.UsuarioAutenticado;
import br.com.carlos.pais_back.DTO.UsuarioDTO;
import br.com.carlos.pais_back.model.Token;
import br.com.carlos.pais_back.model.Usuario;
import br.com.carlos.pais_back.service.TokenService;
import br.com.carlos.pais_back.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/autenticar")
    public ResponseEntity login(@RequestBody @Valid UsuarioDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var usuario = (Usuario) auth.getPrincipal();
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        var usuarioAutenticado = new UsuarioAutenticado(
                usuario.getLogin(),
                usuario.getNome(),
                token,
                usuario.getAdministrador(),
                true
        );
        return ResponseEntity.ok(usuarioAutenticado);
    }

    @GetMapping("/renovar-ticket")
    public ResponseEntity renovarTicket(@RequestParam("token") String token){
        Boolean tokenRenovado = tokenService.renovarToken(token);
        Map<String, Boolean> response = new HashMap<>();
        response.put("tokenAutenticado", tokenRenovado);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid Usuario data){
        if(this.usuarioService.findByLogin(data.getLogin()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getSenha());
        Usuario newUser = new Usuario(data.getLogin(), encryptedPassword, data.getNome(), data.getAdministrador());
        this.usuarioService.saveUser(newUser);
        return ResponseEntity.ok().build();
    }

//    @GetMapping
//      public List<Usuario> listUsers(){
//         List<Usuario> users = usuarioService.listUser();
//         return users;
//      }
}
