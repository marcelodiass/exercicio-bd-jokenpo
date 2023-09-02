package com.example.model;

public class Jogador {
    private String nome;
    private Integer vitorias;
    private Integer derrotas;

    public Jogador(String nome, Integer vitorias, Integer derrotas) {
        this.nome = nome;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVitorias() {
        return vitorias;
    }

    public void setVitorias(Integer vitorias) {
        this.vitorias = vitorias;
    }

    public Integer getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(Integer derrotas) {
        this.derrotas = derrotas;
    }

    @Override
    public String toString() {
        return "Jogador [nome=" + nome + ", vitorias=" + vitorias + ", derrotas=" + derrotas + "]";
    }
}
