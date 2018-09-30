package br.ita.joaopaulo.esseeujali.aceitacao;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class US1_LogarUsuario extends AceitacaoBase {
          
    @Before
    public void before() throws Exception {
        this.loadFlatXmlData("/usuarios.xml");        
    }
    
    @Test
    public void CA1_deveFazerLoginComSucesso() {     
        
        /* CA1 – Dado que eu tenho um e-mail e senha cadastrada no sistema, 
        quando eu digito meu e-mail e senha em um formulário e clico em 
        <Entrar> então eu tenho acesso ao sistema.*/
        
        fazerLogin("joao@email.com", "abc");        
        
        assertEquals("Livros", driver.findElement(By.tagName("h1")).getText());                
    }
    
    @Test
    public void CA2_deveFalharLoginComSenhaInvalida() {     
        
        /* CA2 - Dado que eu tenho um e-mail e senha cadastrada no sistema, 
        quando eu digito meu e-mail corretamente e minha senha incorretamente 
        em um formulário e clico em <Entrar> então eu recebo uma mensagem de 
        erro “Usuário ou senha inválida.”.*/
        
       fazerLogin("joao@email.com", "cdf");
       
       assertEquals("Usuário ou senha inválida.", driver.findElement(By.tagName("p")).getText());             
    }
    
    @Test
    public void CA3_deveFalharLoginComEmailNaoCadastrado() {     
        
        /* Dado que eu não tenho um e-mail e senha cadastrada no sistema, 
        quando eu digito um e-mail e senha em um formulário e clico em 
        <Entrar> então eu recebo uma mensagem de 
        erro “Usuário ou senha inválida.”.*/
        
       fazerLogin("joao404@email.com", "abc");
        
        assertEquals("Usuário ou senha inválida.", driver.findElement(By.tagName("p")).getText());             
    }     
    
}
