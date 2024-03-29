package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class NotaneitorTests {

	// EnWindows (Debeserlaversión65.0.1y desactivarlasactualizacioensautomáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "D:\\User\\DocumentosHDD\\Universidad\\CuartoCarrera\\SistemasDistribuidosInternet\\Laboratorio\\Sesion5\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";// EnMACOSX
																																																// (Debeserlaversión65.0.1y
																																																// desactivarlasactualizacioensautomáticas):
	// staticString PathFirefox65=
	// "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	// staticString Geckdriver024= "/Users/delacal/selenium/geckodriver024mac";
	// Comúna Windows y a MACOSX
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

	// PR01. Accedera lapáginaprincipal /
	@Test
	public void PR01() {
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
	}

	// PR02. OPción denavegación. Pincharenel enlaceRegistroenlapáginahome
	@Test
	public void PR02() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
	}

	// PR03. OPción denavegación. Pincharenel enlaceIdentificateenlapáginahome
	@Test
	public void PR03() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	}

	// PR04. OPción denavegación. CambiodeidiomadeEspañola Inglesy vueltaa Español
	@Test
	public void PR04() {
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());
		// SeleniumUtils.esperarSegundos(driver, 2);
	}

	// PR05. Pruebadelformularioderegistro. registrocondatoscorrectos
	@Test
	public void PR05() {
		// Vamosalformularioderegistro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");// Rellenamosel formulario.
		PO_RegisterView.fillForm(driver, "77777778A", "Josefo", "Perez", "77777", "77777");// Comprobamosqueentramosenlasecciónprivada
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}

	// PR06. Pruebadelformularioderegistro. DNI repetidoenlaBD, Nombrecorto, ....
	// pagination pagination-centered, Error.signup.dni.length
	@Test
	public void PR06() {

		// Vamosalformularioderegistro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamosel formulario.
		PO_RegisterView.fillForm(driver, "99999990A", "Josefo", "Perez", "77777", "77777");
		PO_View.getP();

		// COmprobamos el error deDNI repetido.
		PO_RegisterView.checkKey(driver, "Error.signup.dni.duplicate", PO_Properties.getSPANISH());

		// Comprobamos dni corto
		PO_RegisterView.fillForm(driver, "1234", "Josefo", "Perez", "77777", "77777");
		PO_RegisterView.checkKey(driver, "Error.signup.dni.length", PO_Properties.getSPANISH());

		// Comprobamos dni largo
		PO_RegisterView.fillForm(driver, "0123456789012345678912345", "Josefo", "Perez", "77777", "77777");
		PO_RegisterView.checkKey(driver, "Error.signup.dni.length", PO_Properties.getSPANISH());

		// COmprobamos el error deNombrecorto.
		PO_RegisterView.fillForm(driver, "99999990B", "Jose", "Perez", "77777", "77777");// Rellenamosel formulario.
		PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());

		// Comprobamos la longitud del apellido
		PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "Per", "77777", "77777");// Rellenamosel formulario.
		PO_RegisterView.checkKey(driver, "Error.signup.lastName.length", PO_Properties.getSPANISH());

		// Comprobamos la longitud de la contraseña
		PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "Perez", "777", "777");
		PO_RegisterView.checkKey(driver, "Error.signup.password.length", PO_Properties.getSPANISH());

		// Comprobamos la coincidencia de la contraseña
		PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "Perez", "77777", "77778");
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());

	}

	// PRN. Loguearseconexitodesdeel ROl deUsuario,99999990D, 123456
	@Test
	public void PR07() {
		// Vamosalformulariodelogueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamosel formulario
		PO_LoginView.fillForm(driver, "99999990A", "123456");
		// COmprobamos queentramosenlapaginaprivada
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}

	// PR08: Identificación válida con usuario de ROL profesor ( 99999993D/123456).
	@Test
	public void PR08() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999993D", "123456");
		// COmprobamos queentramosenlapaginaprivada
		PO_View.checkElement(driver, "text", "Agregar Notas");
	}

	// PR09: Identificación válida con usuario de ROL Administrador (
	// 99999988F/123456).
	@Test
	public void PR09() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999988F", "123456");
		// COmprobamos queentramosenlapaginaprivada
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}

	// PR10: Identificación inválida con usuario de ROL alumno ( 99999990A/123456).
	@Test
	public void PR10() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999990A", "123457");
		// Comprobamos el error de password incorrecta

	}

	// PR11. Loguearse con exito desde el ROl de Usuario, 99999990D, 123456 y
	// dexonexion
	@Test
	public void PR11() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999990A", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Notas del usuario");
		// Nos deslogeamos:
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// PR12. Loguearse, comprobar que se visualizan 4 filas de notas y desconectarse
	// usando el rol de estudiante.
	@Test
	public void PR12() {		
		PO_PrivateView.logIn(driver, "Notas del usuario", "99999990A", "123456");
		
		// Contamos el número de filas de notas
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 4);

		PO_PrivateView.logOut(driver, "Identifícate");
	}

	// PR13. Loguearse como estudiante y ver los detalles de la nota con Descripcion
	// = Nota A2.
	// P13. Ver la lista de Notas.
	@Test
	public void PR13() {		
		PO_PrivateView.logIn(driver, "Notas del usuario", "99999990A", "123456");		
		
		SeleniumUtils.esperarSegundos(driver, 1);
		// Contamoslasnotas
		By enlace = By.xpath("//td[contains(text(), 'Nota A2')]/following-sibling::*[2]");
		driver.findElement(enlace).click();
		SeleniumUtils.esperarSegundos(driver, 1);
		// Esperamosporlaventanadedetalle
		PO_View.checkElement(driver, "text", "Detalles de la nota");
		SeleniumUtils.esperarSegundos(driver, 1);		
		
		PO_PrivateView.logOut(driver, "Identifícate");
	}

	// P14. Loguearse como profesor y Agregar Nota A2.
	// P14. Esta prueba podría encapsularse mejor ...
	@Test
	public void PR14() {		
		PO_PrivateView.logIn(driver, "99999993D", "99999993D", "123456");	
		
		// Pinchamosenlaopcióndemenu deNotas: //li[contains(@id, 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'marks-menu')]/a");
		elementos.get(0).click();// Esperamosa aparezcalaopcióndeañadirnota: //a[contains(@href, 'mark/add')]
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'mark/add')]");// PinchamosenagregarNota.
		elementos.get(0).click();// Ahoravamosa rellenarlanota. //option[contains(@value, '4')]
		PO_PrivateView.fillFormAddMark(driver, 3, "Nota Nueva 1", "8");
		// Esperamosa quesemuestrenlosenlacesdepaginaciónlalistadenotas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");// Nosvamosa
																								// laúltimapágina
		elementos.get(3).click();// Comprobamosqueaparecelanotaenlapagina
		elementos = PO_View.checkElement(driver, "text", "Nota Nueva 1");// Ahoranosdesconectamos
		
		PO_PrivateView.logOut(driver, "Identifícate");
	}

	// PRN. Loguearse como profesor, vamos a la ultima página y Eliminamos la Nota
	// Nueva 1.
	// PRN. Ver la lista de Notas.
	@Test
	public void PR15() {
		PO_PrivateView.logIn(driver, "99999993D", "99999993D", "123456");		
		
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'marks-menu')]/a");
		elementos.get(0).click();// Pinchamosenlaopcióndelistadenotas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'mark/list')]");
		elementos.get(0).click();
		// Esperamosa quesemuestrenlosenlacesdepaginacionlalistadenotas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		// Nosvamosa laúltimapágina
		elementos.get(3).click();
		// Esperamosa queaparezcalaNuevanotaenlaultimapagina
		// Y Pinchamosenel enlacedeborradodelaNota"NotaNueva1"//td[contains(text(),
		// 'NotaNueva1')]/following-sibling::*/a[contains(text(), 'mark/delete')]"
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), 'Nota Nueva 1')]/following-sibling::*/a[contains(@href, 'mark/delete')]");
		elementos.get(0).click();
		// Volvemosa laúltimapagina
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		elementos.get(3).click();
		// Y esperamosa queNO aparezcalaultima"NuevaNota1"
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Nota Nueva 1", PO_View.getTimeout());
		
		PO_PrivateView.logOut(driver, "Identifícate");//Tambien podemos poner el mensaje internacionalizado!
		
	}

}