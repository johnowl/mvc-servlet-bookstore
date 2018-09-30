package br.ita.joaopaulo.esseeujali.aceitacao;

import static br.ita.joaopaulo.esseeujali.aceitacao.AceitacaoBase.driver;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class US3_VisualizarLivro extends AceitacaoBase {

    @Before
    public void before() throws Exception {
        this.loadFlatXmlData("/usuarios.xml");        
    }
    
    @Test
    public void CA1_deveVisualizarDetalhesDosLivros() {     
        
        /* CA1 – Dado que o usuário está na página que lista os livros, 
        quando o usuário clica sobre o título de um livro então o usuário 
        é enviado para uma página onde são exibidas mais informações 
        sobre o livro.*/
        
        fazerLogin("joao@email.com", "abc");                     
        clicarNoLivro(0);
        
        assertEquals("Clean Code", driver.findElement(By.tagName("h1")).getText());
                        
    }
    
}
