package br.ita.joaopaulo.esseeujali.model;

import br.ita.joaopaulo.esseeujali.repository.LivrosRepository;
import java.util.ArrayList;
import java.util.List;

public class FakeLivrosRepository implements LivrosRepository {

    protected boolean livroFoiLido;
    protected List<Livro> livros;
    protected Livro livro;
    protected boolean registrarLeituraFoiChamado;
    
    public FakeLivrosRepository() {
        livros = new ArrayList<>();
    }
    
    @Override
    public Livro consultarPorCodigo(int codigo) {
        return livro;
    }

    @Override
    public List<Livro> listar() {
        return livros;
    }

    @Override
    public boolean livroFoiLido(int codigoLivro, int codigoUsuario) {
        return livroFoiLido;
    }

    @Override
    public void registrarLeitura(Livro livro, int codigoUsuario) {
        registrarLeituraFoiChamado = true;
    }

}
