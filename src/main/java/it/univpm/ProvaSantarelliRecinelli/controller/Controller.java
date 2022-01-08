package it.univpm.ProvaSantarelliRecinelli.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.exception.WrongFileException;
import it.univpm.ProvaSantarelliRecinelli.model.City;
import it.univpm.ProvaSantarelliRecinelli.model.WeatherStats;
import it.univpm.ProvaSantarelliRecinelli.service.APICall;
import it.univpm.ProvaSantarelliRecinelli.service.CityReader;
import it.univpm.ProvaSantarelliRecinelli.stats.FilterStats;
import it.univpm.ProvaSantarelliRecinelli.timer.MyTimer;

/**
 * 
 * Questa classe gestisce ogni chiamata che si può effettuare al server
 * 
 * @author DiegoSantarelli
 * @author SimoneRecinelli
 *
 */

@RestController
public class Controller {
	
	/**
	 * 
	 * Rotta di tipo GET che permette di effettuare la chiamata secondo una città ed un Paese inseriti dall'utente tramite i parametri.
	 * Inoltre se l'utente non inserisce alcuna città o alcun Paese il metodo viene effettuato con dei parametri di default (Ancona, IT).
	 * 
	 * @param city rappresenta la città
	 * @param country rappresenta il Paese
	 * 
	 * @return <code>APICall</code>
	 * @see it.univpm.ProvaSantarelliRecinelli.service.APICall
	 * 
	 */
	
	@RequestMapping({"/APICall", "/APICall/{city}/{country}"})
	public JSONObject Call(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country) {
		APICall ap = new APICall(city, country);
		return ap.Call();
	}
	
	/**
	 * 
	 * Rotta di tipo GET che effettua il parsing della chiamata al server secondo una città ed un Paese inseriti dall'utente tramite i parametri.
	 * Inoltre se l'utente non inserisce alcuna città o alcun Paese il metodo viene effettuato con dei parametri di default (Ancona, IT).
	 * 
	 * @param city rappresenta la città
	 * @param country rappresenta il Paese
	 * @return <code>City</code>
	 * @throws WrongCityException eccezione dovuta all'inserimento di una città o Paese errati
	 * 
	 * @see it.univpm.ProvaSantarelliRecinelli.service.CityReader
	 * @see it.univpm.ProvaSantarelliRecinelli.service.CityReader#JSONParsing()
	 */
	
	@RequestMapping({"/JSONParsing", "/JSONParsing/{city}/{country}"})
	public City JSONParsing(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country) throws WrongCityException {
		CityReader city1 = new CityReader(city, country);
		return city1.JSONParsing();
	}
	
	/**
	 * 
	 * Rotta di tipo GET che, secondo una città ed un Paese inseriti dall'utente tramite i parametri, permette di sovrascrivere
	 * un file locale "APIForecastEveryHour.txt" ogni ora.
	 * Inoltre se l'utente non inserisce alcuna città o alcun Paese il metodo viene effettuato con dei parametri di default (Ancona, IT).
	 * 
	 * @param city rappresenta la città
	 * @param country rappresenta il Paese
	 * @return <code>String</code>
	 * @throws WrongFileException eccezione che indica che il path del file è errato
	 * @throws WrongCityException eccezione dovuta all'inserimento di una città o Paese errati
	 * 
	 * @see it.univpm.ProvaSantarelliRecinelli.timer.MyTimer;
	 */
	
	@RequestMapping({"/MyTimer", "/MyTimer/{city}/{country}"})
	public String WriteOnLocalFile1Hour(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country) throws WrongFileException, WrongCityException {
		MyTimer timer = new MyTimer();
		return timer.WriteOnLocalFileEveryHour(city, country);
	}
	
	/**
	 * Rotta di tipo GET che effettua il parsing del file locale "APIForecastEveryHour.txt" tramite i parametri inseriti dall'utente
	 * che devono corrispondere alla città e al relativo Paese del file locale.
	 * Inoltre se l'utente non inserisce alcuna città o alcun Paese il metodo viene effettuato con dei parametri di default (Ancona, IT).
	 * 
	 * @param city rappresenta la città
	 * @param country rappresenta il Paese
	 * @return <code>City</code>
	 * @throws WrongCityException eccezione dovuta all'inserimento di una città o Paese errati
	 * 
	 * @see it.univpm.ProvaSantarelliRecinelli.stats.FilterStats
	 * @see it.univpm.ProvaSantarelliRecinelli.stats.FilterStats#JSONParsingStats()
	 */
	
	@RequestMapping({"/JSONParsingStats", "/JSONParsingStats/{city}/{country}"})
	public City JSONParsingStats(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country) throws WrongCityException {
		FilterStats city1 = new FilterStats(city, country);
		return city1.JSONParsingStats();
	}
	
	/**
	 * Rotta di tipo GET che filtra le statistiche salvate nel file locale "APIForecastEveryHour.txt" relative ad un giorno, in base 
	 * ad una città, un Paese ed un giorno inseriti dall'utente.
	 * Inoltre se l'utente non inserisce alcuna città o alcun Paese il metodo viene effettuato con dei città e Paese di default (Ancona, IT).
	 * 
	 * @param city rappresenta la città
	 * @param country rappresenta il Paese
	 * @param day rappresenta il giorno di cui si vuole effettuare il filtraggio delle statistiche
	 * @return <code>JSONObject</code>
	 * @throws WrongCityException eccezione dovuta all'inserimento di una città o Paese errati
	 * 
	 * @see it.univpm.ProvaSantarelliRecinelli.stats.FilterStats
	 * @see it.univpm.ProvaSantarelliRecinelli.stats.FilterStats#FilterDay(LocalDate, String, String)
	 */
	
	@RequestMapping({"/FilterDay", "/FilterDay/{city}/{country}/{day}"})
	public JSONObject FilterDay(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country , @PathVariable (value="day" , required=false) String day) throws WrongCityException {
		LocalDate date = LocalDate.parse(day);
		FilterStats filter = new FilterStats(city, country);
		return filter.FilterDay(date,city,country);
	}
	
	/**
	 * Rotta di tipo GET che filtra le statistiche salvate nel file locale "APIForecastEveryHour.txt" relative ad un'ora, in base 
	 * ad una città, un Paese, un giorno ed un'ora inseriti dall'utente.
	 * Inoltre se l'utente non inserisce alcuna città o alcun Paese il metodo viene effettuato con dei città e Paese di default (Ancona, IT).
	 * 
	 * @param city rappresenta la città
	 * @param country rappresenta il Paese
	 * @param day rappresenta il giorno di cui si vuole effettuare il filtraggio delle statistiche
	 * @param time rappresenta l'ora del giorno di cui si vuole effettuare il filtraggio delle statistiche
	 * @return <code>JSONObject</code>
	 * @throws WrongCityException eccezione dovuta all'inserimento di una città o Paese errati
	 * 
	 * @see it.univpm.ProvaSantarelliRecinelli.stats.FilterStats
	 * @see it.univpm.ProvaSantarelliRecinelli.stats.FilterStats#Filter1Hour(LocalDate, LocalTime, String, String)
	 */
	
	@RequestMapping({"/Filter1Hour", "/Filter1Hour/{city}/{country}/{day}/{time}"})
	public WeatherStats Filter1Hour(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country , @PathVariable (value="day" , required=false) String day , @PathVariable (value="time" , required=false) String time) throws WrongCityException {
		LocalDate date = LocalDate.parse(day);
		LocalTime myTime = LocalTime.parse(time);
		FilterStats filter = new FilterStats(city, country);
		return filter.Filter1Hour(date, myTime,city,country);
	}
	
	/**
	 * Rotta di tipo GET che filtra le statistiche salvate nel file locale "APIForecastEveryHour.txt" relative ad una fascia oraria dei giorni presenti 
	 * nel file, in base ad una città, un Paese ed una fascia oraria inseriti dall'utente.
	 * Inoltre se l'utente non inserisce alcuna città o alcun Paese il metodo viene effettuato con dei città e Paese di default (Ancona, IT).
	 * 
	 * @param city rappresenta la città
	 * @param country rappresenta il Paese
	 * @param hours rappresenta la fascia oraria per la quale si vuole effettuare il filtraggio delle statistiche
	 * @return <code>JSONObject</code>
	 * @throws WrongCityException eccezione dovuta all'inserimento di una città o Paese errati
	 * 
	 * @see it.univpm.ProvaSantarelliRecinelli.stats.FilterStats
	 * @see it.univpm.ProvaSantarelliRecinelli.stats.FilterStats#FilterPerHours(String, String, String)
	 */
	
	@RequestMapping({"/FilterPerHours", "/FilterPerHours/{city}/{country}/{hours}"})
	public JSONObject FilterPerHours(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country , @PathVariable (value="hours" , required=false) String hours) throws WrongCityException {
		FilterStats objStats = new FilterStats(city, country);
		return objStats.FilterPerHours(hours, city, country);
	}
	
	/**
	 * Rotta di tipo GET che filtra le statistiche salvate nel file locale "APIForecastEveryHour.txt" relative ad un periodo di 5 giorni, in base 
	 * ad una città, un Paese, una data d'inizio e una data di fine inseriti dall'utente.
	 * Inoltre se l'utente non inserisce alcuna città o alcun Paese il metodo viene effettuato con dei città e Paese di default (Ancona, IT).
	 * 
	 * @param city rappresenta la città
	 * @param country rappresenta il Paese
	 * @param date1 rappresenta la data d'inizio
	 * @param date2 rappresenta la data di fine
	 * @return <code>JSONObject</code>
	 * @throws WrongCityException eccezione dovuta all'inserimento di una città o Paese errati
	 * 
	 * @see it.univpm.ProvaSantarelliRecinelli.stats.FilterStats
	 * @see it.univpm.ProvaSantarelliRecinelli.stats.FilterStats#Filter5Days(LocalDate, LocalDate, String, String)
	 */
	
	@RequestMapping({"/Filter5Days", "/Filter5Days/{city}/{country}/{date1}/{date2}"})
	public JSONObject Filter5Days(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country , @PathVariable (value="date1" , required=false) String dateSt, @PathVariable (value="date2" , required=false) String dateEn) throws WrongCityException {
		LocalDate startDate = LocalDate.parse(dateSt);
		LocalDate endDate = LocalDate.parse(dateEn);
		FilterStats objStats = new FilterStats(city, country);
		return objStats.Filter5Days(startDate, endDate, city, country);
	}
	
	/**
	 * 
	 * Questo metodo gestisce le eccezioni dovute all'inserimento di città o Paesi errati
	 * 
	 * @param e rapppresenta l'eccezione
	 * @return <code>String</code>
	 * 
	 * @see it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException#getErr()
	 */
	
	@ExceptionHandler (WrongCityException.class)
	public static String WrongCity(WrongCityException e) {
		return e.getErr();
	}
}
