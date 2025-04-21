package br.com.carlos.pais_back.controller;

import br.com.carlos.pais_back.model.Pais;
import br.com.carlos.pais_back.model.Usuario;
import br.com.carlos.pais_back.service.PaisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pais")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @PostMapping("/salvar")
    public ResponseEntity savePais(@RequestBody @Valid Pais body ){
        Pais retorno = this.paisService.savePais(body);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("/listar")
    public List<Pais> listPaises(){
         return paisService.listPais();
    }

    @GetMapping("/pesquisar/{filtro}")
    public List<Pais> filterPaises(@PathVariable String filtro){
        return paisService.filterPais(filtro);
    }

    @GetMapping("/{id}")
    public Pais getPais(@PathVariable Long id){
        return paisService.findById(id);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity deletePais(@PathVariable Long id){
        this.paisService.deletePais(id);
        return ResponseEntity.ok().build();
    }
}


