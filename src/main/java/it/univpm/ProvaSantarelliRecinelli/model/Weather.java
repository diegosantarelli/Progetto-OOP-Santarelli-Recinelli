package it.univpm.ProvaSantarelliRecinelli.model;

import org.json.simple.JSONObject;

/**
 * Classe che costruisce l'oggetto Weather.
 * 
 * @author DiegoSantarell
 * @author SimoneRecinelli
 *
 */

public class Weather extends Wind {
	private double temp;
	private double tempMax;
	private double tempMin;
	private double feelsLike;
	private String descr, main;
	
	
	/** 
	 * Costruttore della classe 
	 * 
	 * @param temp			Temperatura reale
     * @param temp_max		Temperatura massima
     * @param temp_min		Temperatura minima
	 * @param feelsLike		Temperatura percepita
     * @param date			Giorno a cui si riferisce la previsione
     * @param time			Ora a cui si riferisce la previsione
     * @param descr			Descrizione della previsione
     * @param main			Previsione generale			
     */
	
	public Weather(double windSpeed ,double temp ,double tempMax, double tempMin, double feelsLike , String date, String time, String descr, String main) {
		super(windSpeed, date, time);
		this.temp = temp;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
		this.feelsLike = feelsLike;
		this.descr = descr;
		this.main = main;
	}
	
	/**
	 * Ritorna la temperatura reale.
	 * 
	 * @return <code>double</code>
	 */
	
	public double getTemp() {
		return temp;
	}
	
	/**
	 * Permette all'utente di settare la temperatura reale.
	 * 
	 * @param temp rappresenta la temperatura
	 */
	
	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	/**
	 * Rtorna la temperatura percepita.
	 * 
	 * @return <code>double</code>
	 */
	
	public double getFeels_like() {
		return feelsLike;
	}
	
	/**
	 * Permette all'utente di settare la temperatura percepita.
	 * 
	 * @param feelsLike rappresenta la temperatura percepita
	 */
	
	public void setFeels_like(double feelsLike) {
		this.feelsLike = feelsLike;
	}
	
	/**
	 * Ritorna la temperatura massima.
	 * 
	 * @return <code>double</code>
	 */
	
	public double getTemp_max() {
		return tempMax;
	}
	
	/**
	 * Permette all'utente di settare la temperatura massima.
	 * 
	 * @param tempMax rappresenta la temperatura massima
	 */
	
	public void setTemp_max(double tempMax) {
		this.tempMax = tempMax;
	}
	
	/**
	 * Ritorna la temperatura minima.
	 * 
	 * @return <code>double</code>
	 */
	
	public double getTemp_min() {
		return tempMin;
	}
	
	/**
	 * Permette all'utente di settare la temperatura minima.
	 * 
	 * @param tempMin rappresenta la temperatura minima
	 */

	public void setTemp_min(double tempMin) {
		this.tempMin = tempMin;
	}
	
	/**
	 * Ritorna la descrizione della previsione.
	 * 
	 * @return <code>String</code>
	 */

	public String getDescr() {
		return descr;
	}
	
	/**
	 * Permette all'utente di settare la descrizione della previsione.
	 * 
	 * @param descr rappresenta la descrizione della previsione
	 */

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	/**
	 * Ritorna la previsione generale.
	 * 
	 * @return <code>String</code>
	 */

	public String getMain() {
		return main;
	}
	
	/**
	 * Permette all'utente di settare la previsione generale.
	 * 
	 * @param main rappresenta la previsione generale
	 */

	public void setMain(String main) {
		this.main = main;
	}
	/**
	 * Metodo che converte un JSONObject di tipo Weather in String.
	 * @return <code>String</code>
	 */
	
	public String toString() {
		JSONObject objWeather = new JSONObject();
			
		objWeather.put("Date:", date);
		objWeather.put("Time:", time);
		objWeather.put("Temperatura reale", temp);			
		objWeather.put("Temperatura percepita:", feelsLike);
		objWeather.put("Temperatura minima:", tempMin);
		objWeather.put("Temperatura massima:", tempMax);
		objWeather.put("Descrizione:", descr);
		objWeather.put("Condizione:", main);
			
		return objWeather.toJSONString();
	}
}
