package br.ita.joaopaulo.esseeujali.aceitacao;

import static br.ita.joaopaulo.esseeujali.aceitacao.AceitacaoBase.driver;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class US5_VisualizarRankingUsuarios extends AceitacaoBase {

    @Before
    public void before() throws Exception {
        this.loadFlatXmlData("/leituras.xml");        
    }
    
    @Test
    public void CA1_deveVisualizarRanking() {     
        
        /* Dado que o usuário está logado e está em qualquer página do sistema, 
        quando o usuário clica em “Ranking” então o usuário é enviado para 
        uma página onde é exibida uma lista com os 10 usuários com mais 
        pontos e suas respectivas pontuações..*/
                            
        fazerLogin("joao@email.com", "abc");    
        clicarNoLivro(0);        
        clicarNoBotaoEsseEuJaLi();
        
        fazerLogin("maria@email.com", "abc"); 
        clicarNoLivro(1);   
        clicarNoBotaoEsseEuJaLi();
        
        navegarParaRanking();
        
        WebElement tabela = driver.findElement(By.id("ranking"));
        List<WebElement> linhas = tabela.findElements(By.tagName("tr"));        
        String primeiro = linhas.get(1).findElement(By.className("nome")).getText();
        String segundo = linhas.get(2).findElement(By.className("nome")).getText();
        
        assertEquals("Maria Tereza", primeiro);
        assertEquals("João da Silva", segundo);
        
    }
    
   
    
}
