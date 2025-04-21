package br.com.carlos.pais_back.repository;

import br.com.carlos.pais_back.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

    List<Pais> findByNome(String nome);

    List<Pais> findByNomeContainingIgnoreCase(String nome);
}
