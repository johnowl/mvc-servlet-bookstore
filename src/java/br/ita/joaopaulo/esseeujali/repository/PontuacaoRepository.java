/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ita.joaopaulo.esseeujali.repository;

import br.ita.joaopaulo.esseeujali.model.Pontuacao;
import java.util.List;
import java.util.Map;


public interface PontuacaoRepository {

    void apagarTrofeus(int codigoUsuario);

    void atualizarPontuacao(int codigoUsuario, int pontuacao);

    Map<String, Integer> consultarEstilosLidos(int codigoUsuario);

    List<Integer> consultarPaginasLidas(int codigoUsuario);

    List<Pontuacao> consultarRanking();

    void registrarTrofeus(int codigoUsuario, List<String> trofeus);

    public Pontuacao consultar(int idUsuario);
    
}
