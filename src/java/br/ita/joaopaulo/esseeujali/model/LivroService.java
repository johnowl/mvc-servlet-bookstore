package br.ita.joaopaulo.esseeujali.model;

import br.ita.joaopaulo.esseeujali.repository.LivrosRepository;
import br.ita.joaopaulo.esseeujali.repository.SqlLivrosRepository;
import java.util.List;

public class LivroService {

    private final LivrosRepository repository;
    
    public LivroService() {
        repository = new SqlLivrosRepository();
    }
    
    public LivroService(LivrosRepository repository) {
        this.repository = repository;
    }
    
    public List<Livro> listar() {
        return repository.listar();
    }
    
    public Livro consultarPorCodigo(int codigo) {
        return repository.consultarPorCodigo(codigo);
    }
    
    public void registrarLeitura(int codigoLivro, int codigoUsuario) {
        
        Livro livro = repository.consultarPorCodigo(codigoLivro);
        if(livro == null) {
            throw new RuntimeException("Livro não encontrado.");
        }
        
        if(repository.livroFoiLido(codigoLivro, codigoUsuario)) {
           return;
        }
        
        repository.registrarLeitura(livro, codigoUsuario);
    }
    
}
