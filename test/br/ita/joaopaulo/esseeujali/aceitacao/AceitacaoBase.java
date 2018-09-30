package br.ita.joaopaulo.esseeujali.aceitacao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AceitacaoBase {
    
    protected static final String DB_DRIVER_CLASS_NAME = "org.postgresql.Driver";
    protected static final String DATABASE = "jdbc:postgresql://localhost/livros";
    protected static final String USER = "postgres";
    protected static final String PASSWORD = "changeme";
    
    protected static WebDriver driver;  
    protected static String baseUrl;    
    
    @BeforeClass
    public static void setUp() {        
        driver = new ChromeDriver();
        baseUrl = "http://localhost:8080/EsseEuJaLi";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);        
    }
    
    
    public void loadFlatXmlData(String filename) throws Exception {
        JdbcDatabaseTester jdt = new JdbcDatabaseTester(DB_DRIVER_CLASS_NAME, DATABASE, USER, PASSWORD);
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        jdt.setDataSet(loader.load(filename));
        jdt.onSetup();
    }
    
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
    
    protected List<String> pegarListaTitulosLivros() {
        
        List<String> titulos = new ArrayList<>();
        List<WebElement> livros = driver.findElements(By.className("livro"));                
        for(WebElement livro : livros) {
            WebElement titulo = livro.findElement(By.className("titulo"));
            titulos.add(titulo.getText());
        }
        
        return titulos;
    }
    
    protected void clicarNoLivro(int posicao) {
        List<WebElement> livros = driver.findElements(By.className("livro"));
        livros.get(posicao).findElement(By.className("titulo")).click();  
    }
    
    protected void fazerLogin(String email, String senha) {
        driver.get(baseUrl + "/login");    
        driver.findElement(By.id("email")).sendKeys(email);     
        driver.findElement(By.id("senha")).sendKeys(senha);
        driver.findElement(By.id("entrar")).click();  
    }
    
    protected void clicarNoBotaoEsseEuJaLi() {
        driver.findElement(By.id("registrar")).click(); 
    }
    
    protected void navegarParaPerfil() {
        driver.get(baseUrl + "/perfil");  
    }
    
    protected void navegarParaRanking() {
        driver.get(baseUrl + "/ranking");  
    }
}
