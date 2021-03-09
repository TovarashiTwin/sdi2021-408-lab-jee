package com.uniovi.tests;
import static org.junit.Assert.fail;

import org.junit.runners.MethodSorters;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class NotaneitorTests{
	
	//EnWindows (Debeserlaversión65.0.1y desactivarlasactualizacioensautomáticas)):
	static String PathFirefox65= "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024= "D:\\User\\DocumentosHDD\\Universidad\\CuartoCarrera\\SistemasDistribuidosInternet\\Laboratorio\\Sesion5\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";//EnMACOSX (Debeserlaversión65.0.1y desactivarlasactualizacioensautomáticas):
	//staticString PathFirefox65= "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	//staticString Geckdriver024= "/Users/delacal/selenium/geckodriver024mac";
	//Comúna Windows y a MACOSX
	static WebDriver driver= getDriver(PathFirefox65, Geckdriver024); 
	static String URL= "http://localhost:8090";
	
	
	
	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
		}
	
	//Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp(){
		driver.navigate().to(URL);
		}
	//Después de cadaprueba se borran las cookies del navegador
	@After public void tearDown(){
		driver.manage().deleteAllCookies();
		}
	//Antes de la primera prueba
	@BeforeClass 
	static public void begin() {
		
	}
	//Al finalizar la última prueba 
	@AfterClass 
	static public void end() {
		//Cerramosel navegadoralfinalizarlaspruebas
		driver.quit();
	}
	
	@Test public void test() {
		fail("Not yet implemented");
		}
	
}