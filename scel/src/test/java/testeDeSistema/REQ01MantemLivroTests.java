package testeDeSistema;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class REQ01MantemLivroTests {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.manage().window().maximize();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void ct01_cadastrar_livro_com_sucesso() {
		// *********************************************************************************
		// dado que o livro não esta cadastrado
		// *********************************************************************************
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		espera();
		// quando o usuario cadastrar um livro
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1111");
		driver.findElement(By.id("autor")).sendKeys("George Orwell");
		driver.findElement(By.id("titulo")).sendKeys("1984");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		// entao apresenta as informacoes do aluno
		assertEquals(("Lista de livros"), driver.findElement(By.id("titulopagina")).getText());
		assertEquals("https://ts-scel-web.herokuapp.com/sig/livros", driver.getCurrentUrl());
		assertTrue(driver.getPageSource().contains("1111"));
		// *********************************************************************************
		// teardown - exclusao do registro
		// *********************************************************************************
		driver.findElement(By.linkText("Excluir")).click();
	}

	@Test
	public void ct02_atualiza_livro_com_sucesso() {
		// ***********************************************************************************
		// dado que o livro esta cadastrado
		// ***********************************************************************************
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		espera();
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1111");
		driver.findElement(By.id("autor")).sendKeys("George Orwell");
		driver.findElement(By.id("titulo")).sendKeys("1984");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		espera();
		// **********************************************************************************
		// quando o usuario altera o titulo do livro
		// **********************************************************************************
		driver.findElement(By.linkText("Editar")).click();
		driver.findElement(By.cssSelector(".form-group:nth-child(2)")).click();
		driver.findElement(By.id("titulo")).clear();
		driver.findElement(By.id("titulo")).sendKeys("1985");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		// **********************************************************************************
		// entao o sistema apresenta as informações do aluno com o CEP alterado
		// **********************************************************************************
		assertEquals(("Lista de livros"), driver.findElement(By.id("titulopagina")).getText());
		assertTrue(driver.getPageSource().contains("1111"));
		assertTrue(driver.getPageSource().contains("George Orwell"));
		assertTrue(driver.getPageSource().contains("1985"));
		// **********************************************************************************
		// teardown - exclusao do registro
		// **********************************************************************************
		driver.findElement(By.linkText("Excluir")).click();
	}

	public void espera() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    @Test
	public void ct03_consultar_livro_com_sucesso() {
		// *********************************************************************************
		// dado que o livro está cadastrado
		// *********************************************************************************
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		esperar();
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1111");
		driver.findElement(By.id("autor")).sendKeys("George Orwell");
		driver.findElement(By.id("titulo")).sendKeys("1985");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		esperar();
		// ******************************************************************************
		// quando o usuário consulta os livros cadastrados
		// ********************************************************************************
		driver.findElement(By.linkText("Voltar")).click();
		esperar();
		driver.findElement(By.linkText("Livros")).click();
		esperar();
		driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
		esperar();
		// *********************************************************************************
		// então o sistema apresenta as informações do livro
		// *********************************************************************************
		assertEquals("https://ts-scel-web.herokuapp.com/sig/livros", driver.getCurrentUrl());
		assertEquals(("Lista de livros"), driver.findElement(By.id("titulopagina")).getText());
		assertTrue(driver.getPageSource().contains("1111"));
		assertTrue(driver.getPageSource().contains("George Orwell"));
		assertTrue(driver.getPageSource().contains("1985"));
		// *********************************************************************************
		// teardown - exclusao do registro
		// *********************************************************************************
		driver.findElement(By.linkText("Excluir")).click();
	}


    @Test
	public void ct19_excluir_livro_com_sucesso() {
		// *********************************************************************************
		// dado que o livro está cadastrado
		// *********************************************************************************
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		esperar();
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1111");
		driver.findElement(By.id("autor")).sendKeys("George Orwell");
		driver.findElement(By.id("titulo")).sendKeys("1985");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		esperar();
		// ******************************************************************************
		// quando o usuário excluir o livro
		// ********************************************************************************
		driver.findElement(By.linkText("Excluir")).click();
		// *********************************************************************************
		// então sistema remove o livro
		// *********************************************************************************
		assertFalse(driver.getPageSource().contains("1111"));
		assertFalse(driver.getPageSource().contains("George Orwell"));
		assertFalse(driver.getPageSource().contains("1985"));
	}


	public String waitForWindow(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> whNow = driver.getWindowHandles();
		Set<String> whThen = (Set<String>) vars.get("window_handles");
		if (whNow.size() > whThen.size()) {
			whNow.removeAll(whThen);
		}
		return whNow.iterator().next();
	}
}
