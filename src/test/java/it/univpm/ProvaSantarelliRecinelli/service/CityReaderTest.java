package it.univpm.ProvaSantarelliRecinelli.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.exception.WrongFileException;
import it.univpm.ProvaSantarelliRecinelli.model.City;
import it.univpm.ProvaSantarelliRecinelli.model.Weather;

/**
 * Classe che testa i metodi della classe CityReader
 * 
 * @author DiegoSantarelli
 * @author SimoneRecinelli
 *
 */

class CityReaderTest {
	
	private City city;
	private CityReader cityR;
	private Weather weat;
	private Vector<Weather> weatVec;
	
	/**
	 * Metodo che inizializza le variabili per testare i metodi.
	 * 
	 * @throws WrongCityException eccezione dovuta all'inserimento di una città o Paese errati.
	 */
	
	@BeforeEach
	void setUp() throws WrongCityException {
		this.city = new City("Ancona", "IT");
		this.cityR = new CityReader(city.getName(), city.getCountry());
		this.weat = new Weather(8.46, 7.55, 7.55, 6.76, 3.36, "2022-01-11", "12.00", "broken clouds", "Clouds");
		this.weatVec = new Vector<Weather>();
		
		
	}
	
	/**
	 * Metodo che rimuove gli elementi creati nel setUp
	 */
	
	@AfterEach
	void tearDown() {
		
	}
	
	/**
	 * Metodo che testa se il JSONParsing avviene correttamente.
	 * 
	 * @throws WrongFileException eccezione che restituisce un messaggio di errore quando il path del file non viene trovato.
	 */

	@Test
	@DisplayName("Parsing avvenuto correttamente")
	void JSONParsingTest() throws WrongFileException {
		
		this.weatVec.add(weat);
		assertEquals(cityR.JSONParsing().getVector(), weatVec);
		
	}

}
