package br.ita.joaopaulo.esseeujali.aceitacao;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class US6_VisualizarPerfil extends AceitacaoBase {

    @Before
    public void before() throws Exception {
        this.loadFlatXmlData("/leituras.xml");        
    }
    
    @Test
    public void CA1_deveVisualizarPerfil() {     
        
        /* CA1 – Dado que o usuário está logado e está em qualquer página do 
        sistema, quando o usuário clica em “Pontuação”, então ele é 
        redirecionado para uma página que exibe sua pontuação e os troféus 
        que ele conquistou. */
                            
        fazerLogin("joao@email.com", "abc");    
        clicarNoLivro(0);        
        clicarNoBotaoEsseEuJaLi();                
        clicarNoLivro(1);        
        clicarNoBotaoEsseEuJaLi();                
        
        navegarParaPerfil();
                
        assertEquals("João da Silva", driver.findElement(By.tagName("h1")).getText());
        
    }
    
    
    
}
