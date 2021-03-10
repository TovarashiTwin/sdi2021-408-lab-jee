package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.*;
import com.uniovi.tests.util.SeleniumUtils;

public class PO_NavView extends PO_View {
	
	/*** CLicka una de las opciones principales(a href) y comprueba que se vaya a la vista con el elemento de tipo type con el textoDestino* 
	 * @param driver: apuntando al navegador abierto actualmente.* 
	 * @param textOption: Texto de la opción principal.* 
	 * @param criterio: "id" or "class" or "text" or "@attribute" or "free". Si el valor de criterio es free es una expresion xpathcompleta. * 
	 * @param textoDestino: texto correspondiente a la búsqueda de la página destino.*/
	public static void clickOption(WebDriver driver, String textOption, String criterio, String textoDestino) {
		//CLickamos enlaopciónderegistroy esperamosa quesecargueel enlacedeRegistro.
		List<WebElement> elementos= SeleniumUtils.EsperaCargaPagina(driver, "@href", textOption, getTimeout());
		//Tienequehaberunsólo elemento.
		assertTrue(elementos.size()==1);
		//Ahoraloclickamos
		elementos.get(0).click();//Esperamos a que sea visible un elemento concreto
		elementos= SeleniumUtils.EsperaCargaPagina(driver, criterio, textoDestino, getTimeout());
		//Tiene que haber un sólo elemento.
		assertTrue(elementos.size()==1);
		
		}
	
	/*** Selecciona el enlace de idioma correspondiente al texto textLanguage * 
	 * @paramdriver: apuntando al navegador abierto actualmente.* 
	 * @paramtextLanguage: el texto que aparece en el enlace de idioma("English" o "Spanish")*/
	public static void changeIdiom(WebDriver driver, String textLanguage) {//clickamos la opción Idioma.
		List<WebElement> elementos= SeleniumUtils.EsperaCargaPagina(driver, "id", "btnLanguage", getTimeout());
		elementos.get(0).click();//Esperamos a que aparezca el menú de opciones.
		elementos= SeleniumUtils.EsperaCargaPagina(driver, "id", "languageDropdownMenuButton", getTimeout());
		SeleniumUtils.esperarSegundos(driver, 2);
		//CLickamos laopciónIngléspartiendodelaopciónEspañol
		elementos= SeleniumUtils.EsperaCargaPagina(driver, "id", textLanguage, getTimeout());
		elementos.get(0).click();
		}
	
}
