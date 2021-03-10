package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {
	
	static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {
		// Esperamos 5 segundo aque carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		// Seleccionamosel alumnosuserOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
		// Rellenemosel campodedescripción
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descriptionp);
		WebElement score = driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	
	
	
	static public void logIn(WebDriver driver, String message, String user, String password) {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, user, password);
		// COmprobamos que entramos en la pagina privada del Profesor
		PO_View.checkElement(driver, "text", message);

	}
	static public void logOut(WebDriver driver, String message) {		
		PO_PrivateView.clickOption(driver, "logout", "text", message);
	}
	
	static public List<WebElement> logInAsProfessor(WebDriver driver, String url){		
		
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamosel formulario
		PO_LoginView.fillForm(driver, "99999993D", "123456");
		// COmprobamos queentramosenlapaginaprivadadelProfesor
		PO_View.checkElement(driver, "text", "99999993D");
		// Pinchamosenlaopcióndemenu deNotas: //li[contains(@id, 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'marks-menu')]/a");
		elementos.get(0).click();// Esperamosa aparezcalaopcióndeañadirnota: //a[contains(@href, 'mark/add')]
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'mark/add')]");// PinchamosenagregarNota.
		elementos.get(0).click();// Ahoravamosa rellenarlanota. //option[contains(@value, '4')]
		
		
		return elementos;
	}

}