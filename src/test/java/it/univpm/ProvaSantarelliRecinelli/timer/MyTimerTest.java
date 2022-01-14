package it.univpm.ProvaSantarelliRecinelli.timer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.exception.WrongFileException;
import it.univpm.ProvaSantarelliRecinelli.model.City;

/**
 * Classe che testa i metodi della classe MyTimer
 * 
 * @author DiegoSantarelli
 * @author SimoneRecinelli
 *
 */

class MyTimerTest {
	
	private MyTimer timer;
	private City city;
	
	/**
	 * Metodo che inizializza le variabili per testare i metodi.
	 */
	
	@BeforeEach
	void setUp() {
		this.timer = new MyTimer();
		this.city = new City("Ancona", "IT");
	}
	
	/**
	 * Metodo che rimuove gli elementi creati nel setUp
	 */
	
	@AfterEach
	void tearDown() {
		
	}
	
	/**
	 * Metodo che testa se il file locale viene sovrascritto correttamente.
	 * 
	 * @throws WrongFileException eccezione che restituisce un messaggio di errore quando il path del file non viene trovato.
	 * @throws WrongCityException eccezione riguardante l'inserimento di una città errata
	 */

	@Test
	@DisplayName("Salvataggio del file avvenuto correttamente")
	void WriteOnLocalFileEveryHourTest() throws WrongFileException, WrongCityException {
		String path = "C:\\Users\\diego\\OneDrive\\Desktop\\ProvaSantarelliRecinelli\\ProvaSantarelliRecinelli\\src\\main\\resources\\APIForecastEveryHour";
		String outputMyTimer = "Il file APIForecastEveryHour è stato salvato in " + path;
		assertEquals(timer.WriteOnLocalFileEveryHour(city.getName(), city.getCountry()), outputMyTimer);
	}

}
