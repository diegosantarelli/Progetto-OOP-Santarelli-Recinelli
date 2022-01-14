package it.univpm.ProvaSantarelliRecinelli.stats;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.exception.WrongFileException;
import it.univpm.ProvaSantarelliRecinelli.model.City;
import it.univpm.ProvaSantarelliRecinelli.model.WeatherStats;
import junit.framework.TestSuite;

/**
 * Classe che esegue i test dei metodi della classe FilterTest
 * 
 * @author DiegoSantarelli
 * @author SimoneRecinelli
 *
 */

class FilterStatsTest {
	
	private FilterStats fs;
	private City city;
	private JSONObject jo;
	private String date, hour, startDate, endDate;
	private String timePerHours;
	private LocalDate day, startDay, endDay;
	private LocalTime time;
	private WeatherStats weat;
	private Vector<WeatherStats> weatVec;
	
	/**
	 * Metodo che inizializza gli oggetti da testare.
	 * 
	 * @throws WrongCityException eccezione dovuta all'inserimento di una città o Paese errati.
	 */
	
	@BeforeEach
	void setUp() throws WrongCityException {
		
		this.city = new City("Ancona", "IT");
		this.fs = new FilterStats(city.getName(), city.getCountry());
		this.jo = new JSONObject();
		this.date = "2022-01-11";
		this.day = LocalDate.parse(date);
		this.hour = "21:00:00";
		this.time = LocalTime.parse(hour);
		this.startDate = "2022-01-10";
		this.startDay = LocalDate.parse(startDate);
		this.endDate = "2022-01-17";
		this.endDay = LocalDate.parse(endDate);
		this.timePerHours = "15:00:00-21:00:00";
		this.weat = new WeatherStats(3.57, 3.57, 3.57, -1.39, "2022-01-11", "21:00");
		this.weatVec = new Vector<WeatherStats>();
		
	}
	
	/**
	 * Metodo che distrugge gli oggetti inizializzati nel setUp.
	 */
	
	@AfterEach
	void tearDown() {
		
	}
	
	/**
	 * Metodo che testa se il filtraggio per giorno avviene correttamente.
	 * 
	 * @throws WrongCityException eccezione riguardante l'inserimento di una città errata
	 * @throws WrongFileException eccezione che restituisce un messaggio di errore quando il path del file non viene trovato.
	 */

	@Test
	@DisplayName("Filtraggio avvenuto correttamente")
	void FilterDayTest() throws WrongCityException, WrongFileException {
		
		jo.put("Temperatura massima", 7.55);
		jo.put("Temperatura minima", 6.76);
		jo.put("Varianza delle temperature reali", 2.845071314102564);
		jo.put("Varianza delle temperature percepite", 0.38986762179487183);
		jo.put("Temperatura percepita minima", -1.39);
		jo.put("Media delle temperature reali", 0.56125);
		jo.put("Media delle temperature percepite", 0.09325000000000001);
		jo.put("Temperatura percepita massima", 3.36);
		
		assertEquals(jo, fs.FilterDay(day, city.getName(), city.getCountry()));
		
	}
	
	/**
	 * Metodo che testa se il filtraggio per un'ora avviene correttamente.
	 * 
	 * @throws WrongCityException eccezione riguardante l'inserimento di una città errata
	 * @throws WrongFileException eccezione che restituisce un messaggio di errore quando il path del file non viene trovato.
	 */
	
	@Test
	@DisplayName("Filtraggio avvenuto correttamente")
	void Filter1Hour() throws WrongCityException, WrongFileException {
		
		weat.setWeatVec(weatVec);
		
		assertEquals(weatVec, fs.Filter1Hour(day, time, city.getName(), city.getCountry()).getWeatVec());
		
	}
	
	/**
	 * Metodo che testa se il filtraggio per una fascia oraria avviene correttamente.
	 * 
	 * @throws WrongCityException eccezione riguardante l'inserimento di una città errata
	 * @throws WrongFileException eccezione che restituisce un messaggio di errore quando il path del file non viene trovato.
	 */
	
	@Test
	@DisplayName("Filtraggio avvenuto correttamente")
	void FilterPerHours() throws WrongCityException, WrongFileException {
		
		jo.put("Temperatura massima", 6.74);
		jo.put("Varianza delle temperature reali", 2.446771033653846);
		jo.put("Varianza delle temperature percepite", 1.3691614182692307);
		jo.put("Temperatura percepita minima", -0.11);
		jo.put("Media delle temperature reali", 0.60625);
		jo.put("Media delle temperature percepite", 0.28825);
		jo.put("Temperatura minima", 6.74);
		jo.put("Temperatura percepita massima", 5.76);
		
		assertEquals(jo, fs.FilterPerHours(timePerHours, city.getName(), city.getCountry()));
		
	}
	
	/**
	 * Metodo che testa se il filtraggio per una settimana avviene correttamente.
	 * 
	 * @throws WrongCityException eccezione riguardante l'inserimento di una città errata
	 * @throws WrongFileException eccezione che restituisce un messaggio di errore quando il path del file non viene trovato.
	 */
	
	@Test
	@DisplayName("Filtraggio avvenuto correttamente")
	void FilterPerWeek() throws WrongCityException, WrongFileException {
		
		jo.put("Temperatura massima", 9.88);
		jo.put("Varianza delle temperature reali", 3.982336858974359);
		jo.put("Varianza delle temperature percepite", 10.706428141025643);
		jo.put("Temperatura percepita minima", -3.08);
		jo.put("Media delle temperature reali", 5.0962499999999995);
		jo.put("Media delle temperature percepite", 2.72775);
		jo.put("Temperatura minima", 9.88);
		jo.put("Temperatura percepita massima", 9.54);
		
		assertEquals(jo, fs.FilterWeek(startDay, endDay, city.getName(), city.getCountry()));
		
	}
}
