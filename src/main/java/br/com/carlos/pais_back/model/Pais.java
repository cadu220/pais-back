package br.com.carlos.pais_back.model;

import jakarta.persistence.*;


@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String sigla;

    @Column(unique = true, nullable = false)
    private String gentilico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getGentilico() {
        return gentilico;
    }

    public void setGentilico(String gentilico) {
        this.gentilico = gentilico;
    }
}
