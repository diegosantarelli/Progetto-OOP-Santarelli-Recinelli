package it.univpm.ProvaSantarelliRecinelli.service;

import org.json.simple.JSONArray;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.model.City;

/**
 * <p>
 * <b>Interfaccia</b> che permette di leggere il file e estrarne i dati di interesse 
 * <p>
 * 
 * @author Simone_Recinelli
 * @author Diego_Santarelli
 *
 */

public interface CityReaderService {
	
	/**
	 * metodo che legge il file e lo copia su un JSONArray
	 * 
	 * @return <code>JSONArray</code>
	 */
	
	public abstract JSONArray caricaArray();
	
	/**
	 * metodo che scorre gli oggetti del file e estrae latitudine e longitudine della città di interesse
	 * 
	 * @param ja indica il JSONArray copiato dal file
	 * 
	 * @throws WrongCityException città inserita sbagliata
	 */
	public abstract void GetWindSpeedCity(JSONArray j) throws WrongCityException;
	
	/**
	 * metodo che ritorna il nome della città di riferimento
	 * 
	 * @return <code>String</code>
	 * 
	 * @throws WrongCityException città inserita sbagliata
	 */
	
	public abstract double GetWindSpeed() throws WrongCityException;
	
	public abstract City getCityWeatherRistrictfromApi(JSONArray ja) throws WrongCityException;

	public abstract double GetTempMax() throws WrongCityException;

	double GetTempMin() throws WrongCityException;

	double GetTemp() throws WrongCityException;

	double GetFeelsLike() throws WrongCityException;
	
	public abstract String SaveEveryHour(City city);
	
	
}