package util;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteLogin {

	private WebDriver driver = null;
	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver","/Users/Gilberto Lima/Desktop/chromedriver_win32/chromedriver.exe");
		this.driver = new ChromeDriver();
		
	}
	@Test
	public void teste() {
		driver.get("localhost:8080/Controle_de_Acesso");
		WebElement login = driver.findElement(By.name("login"));
		WebElement senha = driver.findElement(By.name("senha"));
		WebElement botaoLogar = driver.findElement(By.id("btnsalvar"));
		login.sendKeys("FernandoA");
		senha.sendKeys("123456");
		botaoLogar.submit();
		
		boolean encontrou = driver.getPageSource().contains("Módulos");
		assertTrue(encontrou);
		//driver.findElement(By.linkText("Darwin2018.1")).click();
		WebElement botaoAcessar = driver.findElement(By.name("Darwin2018.1"));
		botaoAcessar.submit();
	}
	/*
	public void acessaDarwin() {
		
		boolean encontrou = driver.getPageSource().contains("Módulos do Sistema");
		assertTrue(encontrou);
		driver.findElement(By.linkText("Darwin2018.1"));
		
	}
	*/
	
}
