package com.juan.classes;

import java.util.List;

public class Aluno {
    private Integer id;
    private String nome;
    private List<Double> notas;

    public Aluno(Integer id, String nome, List<Double> notas) {
        this.id = id;
        this.nome = nome;
        this.notas = notas;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Double> getNotas() {
        return notas;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nota=" + notas +
                '}';
    }
}
