package br.com.carlos.pais_back.repository;

import br.com.carlos.pais_back.model.Pais;
import br.com.carlos.pais_back.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository  extends JpaRepository<Token, Long> {

    Token findByToken(String token);
}
