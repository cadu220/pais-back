package br.com.carlos.pais_back.service;

import br.com.carlos.pais_back.model.Pais;
import br.com.carlos.pais_back.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public Pais savePais(Pais bean){
        return this.paisRepository.save(bean);
    }

    public void deletePais(Long id){
        this.paisRepository.deleteById(id);
    }

    public List<Pais> listPais(){
        return this.paisRepository.findAll();
    }
    public List<Pais> filterPais(String nome){
        return this.paisRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Pais findById(Long id){
        return paisRepository.findById(id).orElse(null);
    }


}
