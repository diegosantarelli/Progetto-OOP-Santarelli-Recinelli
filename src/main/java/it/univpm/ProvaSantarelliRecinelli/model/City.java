package it.univpm.ProvaSantarelliRecinelli.model;

import java.util.Vector;

/**
 * Classe che rappresenta l'oggetto città
 * 
 * @author Simone_Recinelli
 * @author Diego_Santarelli
 */

public class City {
	
	private String Name;
	private String Country;
	private Vector<Weather> vector = new Vector<Weather>();
	private Vector<WeatherStats> vectorStats = new Vector<WeatherStats>();
	private Vector <Wind> windVec = new Vector <Wind>();
	
	/**
	 * Costruttore della classe City
	 * 
	 * @param name indica il nome della città
	 * @param country indica il Paese della città
	 */	
	
	public City(String Name, String Country) {
			this.Name = Name;
			this.Country = Country;
	}
	
	/**
	 * Ritorna il vettore della classe Weather
	 * 
	 * @return <code>Vector<Weather></code>
	 * 
	 */
	
	public Vector<Weather> getVector() {
		return vector;
	}
	
	/**
	 * Permette di settare il vettore della classe Weather
	 * 
	 * @param vector nome del vettore
	 * 
	 */
	
	public void setVector(Vector<Weather> vector) {
		this.vector = vector;
	}
	
	/**
	 * Ritorna il vettore della classe WeatherStats
	 * 
	 * @return un <code>Vector<WeatherStats></code>
	 * 
	 */
	public Vector<WeatherStats> getVectorStats() {
		return vectorStats;
	}
	
	/**
	 * Permette di settare il vettore della classe WeatherStats
	 * 
	 * @param vectorStats
	 */

	public void setVectorStats(Vector<WeatherStats> vectorStats) {
		this.vectorStats = vectorStats;
	}

	/**
	 * Ritorna il nome di una città
	 * @return nome della città di tipo <code>String</code>
	 */
	
	public String getName() {
		return Name;
	}
	
	/**
	 * Permette di settare il nome della città
	 * @param name indica il nome della città di riferimento
	 */
	
	public void setName(String name) {
		this.Name = name;
	}
	
	/**
	 * Ritorna il Paese di una città
	 * @return il Paese della città di tipo <code>String</code>
	 */
	
	public String getCountry() {
		return Country;
	}
	
	/**
	 * Permette di settare il Paese della città
	 * @param country indica il Paese della città
	 */
	
	public void setCountry(String country) {
		Country = country;
	}

	public Vector<Wind> getWindVec() {
		return windVec;
	}

	public void setWindVec(Vector<Wind> windVec) {
		this.windVec = windVec;
	}
		
}

