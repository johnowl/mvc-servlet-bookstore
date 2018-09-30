package br.ita.joaopaulo.esseeujali.model;

import br.ita.joaopaulo.esseeujali.repository.PontuacaoRepository;
import br.ita.joaopaulo.esseeujali.repository.SqlPontuacaoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PontuacaoService {

    private final PontuacaoRepository repository;

    public PontuacaoService() {
        repository = new SqlPontuacaoRepository();
    }

    public PontuacaoService(PontuacaoRepository repository) {
        this.repository = repository;
    }
    
    public List<Pontuacao> consultarRanking() {
        return repository.consultarRanking();
    }
    
    public Pontuacao consultar(int idUsuario) {
        return repository.consultar(idUsuario);
    }

    public void atualizarRanking(int codigoUsuario) {
        atualizarPontuacao(codigoUsuario);
        atualizarTrofeus(codigoUsuario);
    }

    private void atualizarPontuacao(int codigoUsuario) {
        int pontos = calcularPontuacao(codigoUsuario);
        repository.atualizarPontuacao(codigoUsuario, pontos);
    }

    private void atualizarTrofeus(int codigoUsuario) {
        List<String> trofeus = calcularTrofeus(codigoUsuario);
        repository.apagarTrofeus(codigoUsuario);
        repository.registrarTrofeus(codigoUsuario, trofeus);
    }

    private int calcularPontuacao(int codigoUsuario) {

        List<Integer> paginasLidas = repository.consultarPaginasLidas(codigoUsuario);
        if (paginasLidas.isEmpty()) {
            return 0;
        }

        int pontuacao = 0;
        for (int p : paginasLidas) {

            pontuacao += (p / 100);

            if (p % 100 != 0) {
                pontuacao += 1;
            }
        }
        return pontuacao;
    }

    private List<String> calcularTrofeus(int codigoUsuario) {
        Map<String, Integer> estiloLidos = repository.consultarEstilosLidos(codigoUsuario);

        List<String> trofeus = new ArrayList<>();

        if (estiloLidos.isEmpty()) {
            return trofeus;
        }

        for (Map.Entry<String, Integer> estilo : estiloLidos.entrySet()) {
            if(estilo.getValue() >= 5) {
                trofeus.add("Leitor de " + estilo.getKey());
            }
        }

        return trofeus;
    }

}
