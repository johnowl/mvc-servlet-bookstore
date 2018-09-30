package br.ita.joaopaulo.esseeujali.model;

import org.junit.Test;
import static org.junit.Assert.*;


public class PontuacaoServiceTest {
    
    @Test
    public void calculaPontuacaoZeradaQuandoUsuariosNaoTemLeituras() {
        FakePontuacaoRepository repository = new FakePontuacaoRepository();
        
        PontuacaoService service = new PontuacaoService(repository);
        service.atualizarRanking(100);        
        
        assertEquals(0, repository.pontuacaoRecebidaEmAtualizarPontuacao);        
    }
    
    @Test
    public void calculaPontuacaoQuandoLeuUmLivroDeCemPaginas() {
        FakePontuacaoRepository repository = new FakePontuacaoRepository();
        repository.paginasLidas.add(100);
        
        PontuacaoService service = new PontuacaoService(repository);
        service.atualizarRanking(100);        
        
        assertEquals(1, repository.pontuacaoRecebidaEmAtualizarPontuacao);        
    }
    
    @Test
    public void calculaPontuacaoCorretaQuandoUsuariosTemLeituras() {
        FakePontuacaoRepository repository = new FakePontuacaoRepository();
        repository.paginasLidas.add(120);
        repository.paginasLidas.add(90);
        repository.paginasLidas.add(300);
        repository.paginasLidas.add(480);
        
        PontuacaoService service = new PontuacaoService(repository);
        service.atualizarRanking(100);        
        
        assertEquals(11, repository.pontuacaoRecebidaEmAtualizarPontuacao);
        
    }
    

    @Test
    public void calculaListaTrofeusVaziaQuandoUsuarioNaoTemLeituras() {
        FakePontuacaoRepository repository = new FakePontuacaoRepository();
        
        PontuacaoService service = new PontuacaoService(repository);
        service.atualizarRanking(100);        
        
        assertTrue(repository.trofeusRecebidosEmRegistrarTrofeus.isEmpty());        
    }
    
    @Test
    public void calculaTrofeuQuandoUsuarioTemUmTrofeu() {
        FakePontuacaoRepository repository = new FakePontuacaoRepository();
        repository.estilosLidos.put("Programação", 5);
        repository.estilosLidos.put("Arquitetura", 4);
        
        PontuacaoService service = new PontuacaoService(repository);
        service.atualizarRanking(100);        
                
        assertEquals("Leitor de Programação", repository.trofeusRecebidosEmRegistrarTrofeus.get(0));
    }
    
    @Test
    public void calculaTrofeuQuandoUsuarioTemTresTrofeus() {
        FakePontuacaoRepository repository = new FakePontuacaoRepository();
        repository.estilosLidos.put("Programação", 5);
        repository.estilosLidos.put("Arquitetura", 4);
        repository.estilosLidos.put("Medicina", 6);
        repository.estilosLidos.put("Jornalismo", 3);
        repository.estilosLidos.put("Suspense", 5);
        
        PontuacaoService service = new PontuacaoService(repository);
        service.atualizarRanking(100);        
                
        assertTrue(repository.trofeusRecebidosEmRegistrarTrofeus.contains("Leitor de Programação"));
        assertTrue(repository.trofeusRecebidosEmRegistrarTrofeus.contains("Leitor de Medicina"));
        assertTrue(repository.trofeusRecebidosEmRegistrarTrofeus.contains("Leitor de Suspense"));        
    }

}
