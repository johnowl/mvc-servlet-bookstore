package br.ita.joaopaulo.esseeujali.aceitacao;

import static br.ita.joaopaulo.esseeujali.aceitacao.AceitacaoBase.driver;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class US4_MarcarLeituraLivro extends AceitacaoBase {

    @Before
    public void before() throws Exception {
        this.loadFlatXmlData("/leituras.xml");        
    }
    
    @Test
    public void CA1_deveMarcarLeituraDeUmLivro() {     
        
        /* Dado que o usuário está na página de detalhes de um livro, 
        quando o usuário clica no botão “Esse eu já li” o sistema registra
        a leitura do livro e redireciona para a página que exibe sua pontuação.*/
        
        fazerLogin("joao@email.com", "abc");                     
        clicarNoLivro(0);      
        clicarNoBotaoEsseEuJaLi();
        
        assertEquals("João da Silva", driver.findElement(By.tagName("h1")).getText());
    }
    
    @Test
    public void CA2_devePontuarPorLivroLido() {     
        
        /* CA2 – Dado que o usuário registrou a leitura de um livro, então o 
        sistema deve computar um ponto para o usuário para cada 100 páginas 
        que o livro possui. */
        
        fazerLogin("joao@email.com", "abc");                     
        clicarNoLivro(1);      
        clicarNoBotaoEsseEuJaLi(); 
        
        assertEquals("2", driver.findElement(By.id("pontuacao")).getText());
    }        
    
    @Test
    public void CA3_deveGanharTrofeu() {     
        
        /* CA3 – Dado que o usuário registrou a leitura de 5 livros do mesmo 
        estilo, então o sistema deve computar um troféu de leitor do 
        respectivo estilo. */
        
        fazerLogin("joao@email.com", "abc");                     
                         
     
        for(int i = 0; i < 5; i++) {
            driver.get(baseUrl + "/livros");            
            clicarNoLivro(i);      
            clicarNoBotaoEsseEuJaLi();           
        }
        
        assertEquals("23", driver.findElement(By.id("pontuacao")).getText());
        assertEquals("Leitor de Programação", driver.findElement(By.id("trofeus")).findElement(By.tagName("li")).getText());
    }        
}
