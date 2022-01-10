package it.univpm.ProvaSantarelliRecinelli.model;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Classe che descrive l'oggetto City.
 * 
 * @author SimoneRecinelli
 * @author DiegoSantarelli
 */

public class City {
	
	private String Name;
	private String Country;
	private Vector<Weather> vector = new Vector<Weather>();
	private Vector<WeatherStats> vectorStats = new Vector<WeatherStats>();
	private Vector <Wind> windVec = new Vector <Wind>();
	
	/**
	 * Costruttore della classe.
	 * 
	 * @param name rappresenta il nome della città
	 * @param country rappresenta il nome del Paese
	 */	
	
	public City(String Name, String Country) {
			this.Name = Name;
			this.Country = Country;
	}
	
	/**
	 * Restituisce il vettore della classe Weather.
	 * @return <code>Vector<Weather></code>
	 */
	
	public Vector<Weather> getVector() {
		return vector;
	}
	
	/**
	 * Permette di settare il vettore della classe Weather.
	 * @param vector nome del vettore
	 */
	
	public void setVector(Vector<Weather> vector) {
		this.vector = vector;
	}
	
	/**
	 * Restituisce il vettore della classe WeatherStats
	 * @return <code>Vector<WeatherStats></code>
	 */
	
	public Vector<WeatherStats> getVectorStats() {
		return vectorStats;
	}
	
	/**
	 * Permette di settare il vettore della classe WeatherStats.
	 * @param vectorStats
	 */

	public void setVectorStats(Vector<WeatherStats> vectorStats) {
		this.vectorStats = vectorStats;
	}

	/**
	 * Restituisce il nome di una città.
	 * @return <code>String</code>
	 */
	
	public String getName() {
		return Name;
	}
	
	/**
	 * Permette di settare il nome della città.
	 * @param name rappresenta il nome della città
	 */
	
	public void setName(String name) {
		this.Name = name;
	}
	
	/**
	 * Restituisce il Paese.
	 * @return <code>String</code>
	 */
	
	public String getCountry() {
		return Country;
	}
	
	/**
	 * Permette di settare il Paese
	 * @param country rappresenta il Paese
	 */
	
	public void setCountry(String country) {
		Country = country;
	}
	
	/**
	 * Restituisce il vettore della classe Wind
	 * @return <code>Vector<Wind></code>
	 */
	public Vector<Wind> getWindVec() {
		return windVec;
	}
	
	/**
	 * Permette di settare il vettore della classe Wind
	 * @param windVec rappresenta un vettore della classe Wind
	 */
	public void setWindVec(Vector<Wind> windVec) {
		this.windVec = windVec;
	}
		
	
}

