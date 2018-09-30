package br.ita.joaopaulo.esseeujali.model;

import java.util.List;

public class Pontuacao {
    
    private String nome;
    private int pontos;
    private List<String> trofeus;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public List<String> getTrofeus() {
        return trofeus;
    }

    public void setTrofeus(List<String> trofeus) {
        this.trofeus = trofeus;
    }

    
}
