package br.edu.ifspsaocarlos.agenda.model;

import java.io.Serializable;

public class Contato implements Serializable{
    private static final long serialVersionUID = 1L;
    private long id;
    private String nome;
    private String fone;
    private String fone2;
    private String email;
    private Boolean favorito;
    private String aniversario;

    public Contato()
    {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getFone() {
        return fone;
    }
    public void setFone(String fone) {
        this.fone = fone;
    }
    public String getFone2() {
        return fone2;
    }
    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Boolean getFavorito() {return favorito; }
    public void setFavorito(Boolean favorito) {this.favorito = favorito; }
    public String getAniversario() {
        return aniversario;
    }
    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }
}

