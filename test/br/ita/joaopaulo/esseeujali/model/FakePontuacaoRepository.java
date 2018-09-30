package br.ita.joaopaulo.esseeujali.model;

import br.ita.joaopaulo.esseeujali.repository.PontuacaoRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakePontuacaoRepository implements PontuacaoRepository {

    protected Map<String, Integer> estilosLidos;
    protected List<Integer> paginasLidas;
    protected List<Pontuacao> ranking;
    protected boolean apagarTrofeusFoiChamado;
    protected boolean atualizarPontuacaoFoiChamado;
    protected boolean registrarTrofeusFoiChamado;
    protected int pontuacaoRecebidaEmAtualizarPontuacao;
    protected List<String> trofeusRecebidosEmRegistrarTrofeus;
    protected Pontuacao pontuacao;
    
    public FakePontuacaoRepository() {
        estilosLidos = new HashMap<>();
        paginasLidas = new ArrayList<>();
    }
    
    @Override
    public void apagarTrofeus(int codigoUsuario) {
        apagarTrofeusFoiChamado = true;
    }

    @Override
    public void atualizarPontuacao(int codigoUsuario, int pontuacao) {
        atualizarPontuacaoFoiChamado = true;
        pontuacaoRecebidaEmAtualizarPontuacao = pontuacao;
    }

    @Override
    public Map<String, Integer> consultarEstilosLidos(int codigoUsuario) {
        return estilosLidos;
    }

    @Override
    public List<Integer> consultarPaginasLidas(int codigoUsuario) {
        return paginasLidas;
    }

    @Override
    public List<Pontuacao> consultarRanking() {
        return ranking;
    }

    @Override
    public void registrarTrofeus(int codigoUsuario, List<String> trofeus) {
        registrarTrofeusFoiChamado = true;
        trofeusRecebidosEmRegistrarTrofeus = trofeus;
    }

    @Override
    public Pontuacao consultar(int idUsuario) {
        return pontuacao;
    }

}
