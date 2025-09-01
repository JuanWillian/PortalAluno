package com.juan.classes;

public class Aluno {
    private Integer id;
    private String nome;
    private Double nota;

    public Aluno(Integer id, String nome, Double nota) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getNota() {
        return nota;
    }
}
