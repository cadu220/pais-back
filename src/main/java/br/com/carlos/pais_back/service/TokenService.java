package br.com.carlos.pais_back.service;

import br.com.carlos.pais_back.model.Token;
import br.com.carlos.pais_back.model.Usuario;
import br.com.carlos.pais_back.repository.TokenRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Autowired
    private TokenRepository tokenRepository;

    public String generateToken(Usuario user){
        try{


            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())
//                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);

            Token tokenEntity = tokenRepository.findByToken(token);
            if(tokenEntity != null){
                tokenEntity.setExpiracao(getExpirationDate());
                tokenRepository.save(tokenEntity);
            }else{
            Token tokenBean = new Token();
            tokenBean.setToken(token);
            tokenBean.setLogin(user.getLogin());
            tokenBean.setAdministrador(user.getAdministrador());
            tokenBean.setExpiracao(getExpirationDate());
            tokenBean.setNome(user.getNome());
            tokenRepository.save(tokenBean);
            }

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token){
        try {
            Token tokenEntity = tokenRepository.findByToken(token);
            LocalDateTime dataExpiracao = tokenEntity.getExpiracao()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            Algorithm algorithm = Algorithm.HMAC256(secret);
            if (LocalDateTime.now().isAfter(dataExpiracao)) {
                throw new JWTVerificationException("Token expirado.");
            }
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    private Date getExpirationDate(){
        return Date.from(LocalDateTime.now().plusMinutes(5)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public Token findByToken(String token){
        return this.tokenRepository.findByToken(token);
    }

    public Boolean renovarToken(String token){
        Token tokenEntity = tokenRepository.findByToken(token);
        if(tokenEntity != null){
            tokenEntity.setExpiracao(getExpirationDate());
            tokenRepository.save(tokenEntity);
            return true;
        }else{
            return false;
        }
    }
}
