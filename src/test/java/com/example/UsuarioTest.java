package com.example;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioTest {

	private static WebDriver driver;

	@Value("${api.hostname}")
	private String hostname;
	@Value("${api.path}")
	private String path;

	// Método que inicia tudo que for necessário para o teste
	@BeforeClass
	public static void setUpTest(){
		System.setProperty("webdriver.chrome.driver", "D:\\Git\\Alura\\alura-selenium\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Before
	public void configuraTestes(){
		driver.get(hostname.concat(path));
	}

	@Test
	public void deveAdicionarUsuario() throws InterruptedException {
		WebElement nome = driver.findElement(By.name("usuario.nome"));
		WebElement email = driver.findElement(By.name("usuario.email"));
		Thread.sleep(1000);  // Let the user actually see something!
		nome.sendKeys("michelmedeiros");
		email.sendKeys("michelmedeiros@gmail.com");
		Thread.sleep(1000);  // Let the user actually see something!
		nome.submit();
		boolean achouNome = driver.getPageSource().contains("michelmedeiros");
		boolean achouEmail = driver.getPageSource().contains("michelmedeiros@gmail.com");
		assertThat(achouNome, equalTo(true));
		assertThat(achouEmail, equalTo(true));
	}

	@Test
	public void deveAdicionarNovoUsuario() throws InterruptedException {
		WebElement nome = driver.findElement(By.name("usuario.nome"));
		WebElement email = driver.findElement(By.name("usuario.email"));
		Thread.sleep(1000);  // Let the user actually see something!
		nome.sendKeys("anamedeiros");
		email.sendKeys("ana@gmail.com");
		Thread.sleep(1000);  // Let the user actually see something!
		nome.submit();
		boolean achouNome = driver.getPageSource().contains("anamedeiros");
		boolean achouEmail = driver.getPageSource().contains("ana@gmail.com");
		assertThat(achouNome, equalTo(true));
		assertThat(achouEmail, equalTo(true));
	}

	// Método que finaliza o teste, fechando a instância do WebDriver.
	@AfterClass
	public static void tearDownTest(){
		driver.quit();
	}

}
