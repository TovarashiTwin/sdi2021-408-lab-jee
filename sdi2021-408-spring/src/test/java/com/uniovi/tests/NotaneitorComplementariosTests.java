package com.uniovi.tests;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorComplementariosTests {

	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "D:\\User\\DocumentosHDD\\Universidad\\CuartoCarrera\\SistemasDistribuidosInternet\\Laboratorio\\Sesion5\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";// EnMACOSX

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cadaprueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {

	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramosel navegadoralfinalizarlaspruebas
		driver.quit();
	}

	// 99999988F
	@Test
	public void PR01() {

		PO_PrivateView.logIn(driver, "99999988F", "99999988F", "123456");

		// Pinchamos en la opción de menu de Profesores:		
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'teacher-menu')]/a");
		elementos.get(0).click();
		
		// Esperamos a aparezca la opción de añadir profesor: 
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/teacher/add')]");
		// Pinchamos en agregar Profesor.
		elementos.get(0).click();

		//Rellenar formulario
		PO_PrivateView.fillFormAddProfessor(driver, 0, "99999900J", "Juan", "Cuesta", "Prueba");
		
		//Esperamos
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");		
		elementos.get(2).click();
		
		//Esperamos
		elementos = PO_View.checkElement(driver, "text", "12111111P");
		
		PO_PrivateView.logOut(driver, "Identifícate");

	}

	@Test
	public void PR02() {
		PO_PrivateView.logIn(driver, "99999988F", "99999988F", "123456");

		// Pinchamos en la opción de menu de Profesores:
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'teacher-menu')]/a");
		elementos.get(0).click();
		// Esperamos 
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'teacher/add')]");
		// Pinchamos en agregar Profesor.
		elementos.get(0).click();
		//Rellenamos
		PO_PrivateView.fillFormAddProfessor(driver, 0, "99999900J", "Juanjo", "Cuesta", "Prueba");
		PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());

		PO_PrivateView.fillFormAddProfessor(driver, 0, "99999900J", "Juanito", "Cuesta", "Prueba");
		PO_RegisterView.checkKey(driver, "Error.signup.category.length", PO_Properties.getSPANISH());
		
		PO_PrivateView.logOut(driver, "Identifícate");
	}

	@Test
	public void PR03() {
		// Alumno
		PO_PrivateView.logIn(driver, "99999990A", "99999990A", "123456");
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'professors-menu')]/a");
		elementos.get(0).click();
		PO_View.checkElement(driver, "@href", "professor/add");
		PO_PrivateView.logOut(driver, "Identifícate");

		// Profesor
		PO_PrivateView.logIn(driver, "99999977E", "99999977E", "123456");
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'teacher-menu')]/a");
		elementos.get(0).click();
		PO_View.checkElement(driver, "@href", "teacher/add");
		PO_PrivateView.logOut(driver, "Identifícate");

		// Admin
		PO_PrivateView.logIn(driver, "99999988F", "99999988F", "123456");
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'teacher-menu')]/a");
		elementos.get(0).click();
		PO_View.checkElement(driver, "@href", "teacher/add");
		
		PO_PrivateView.logOut(driver, "Identifícate");
	}

}
