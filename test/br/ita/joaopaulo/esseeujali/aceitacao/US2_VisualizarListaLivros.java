package br.ita.joaopaulo.esseeujali.aceitacao;

import static br.ita.joaopaulo.esseeujali.aceitacao.AceitacaoBase.driver;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class US2_VisualizarListaLivros extends AceitacaoBase {

    @Before
    public void before() throws Exception {
        this.loadFlatXmlData("/usuarios.xml");        
    }
    
    @Test
    public void CA1_deveExibirListaDeLivros() {     
        
        /* CA1 – Dado que o usuário está identificado no sistema, 
        quando o usuário acessa a página que lista os livros então o 
        usuário vê todos os livros cadastrados ordenados pelo título. */
        
        fazerLogin("joao@email.com", "abc");                           
     
        List<String> titulos = pegarListaTitulosLivros();
        
        assertEquals(5, titulos.size());
        assertEquals("Clean Code", titulos.get(0));
        assertEquals("Domain Driven Design", titulos.get(1));
        assertEquals("Programação Extrema", titulos.get(2));
        assertEquals("Refactoring", titulos.get(3));
        assertEquals("Working Effectively with Legacy Code", titulos.get(4));
        
    }
    
}
