package br.ita.joaopaulo.esseeujali.model;

import org.junit.Test;
import static org.junit.Assert.*;


public class LivroServiceTest {
    
    @Test
    public void deveFalharAoGravarLeituraQuandoLivroNaoExiste() {
        FakeLivrosRepository repository = new FakeLivrosRepository();
        repository.livroFoiLido = false;
        repository.livro = null;
        
        LivroService service = new LivroService(repository);
        try {
            service.registrarLeitura(1, 1);
            fail();
        }catch(Exception e) {
            assertEquals("Livro não encontrado.", e.getMessage());
        }            
    }
    
    @Test
    public void naoDeveGravarLeituraRepetida() {
        FakeLivrosRepository repository = new FakeLivrosRepository();
        repository.livroFoiLido = true;
        repository.livro = new Livro();
        
        LivroService service = new LivroService(repository);
        service.registrarLeitura(1, 1);
        
        assertFalse(repository.registrarLeituraFoiChamado);        
    }
    
    @Test
    public void deveGravarLeituraNova() {
        FakeLivrosRepository repository = new FakeLivrosRepository();
        repository.livroFoiLido = false;
        repository.livro = new Livro();
        
        LivroService service = new LivroService(repository);
        service.registrarLeitura(1, 1);
        
        assertTrue(repository.registrarLeituraFoiChamado);        
    }
    
}
