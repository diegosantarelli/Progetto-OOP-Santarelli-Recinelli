package it.univpm.ProvaSantarelliRecinelli.model;


import java.util.Vector;

/**
 * Classe che costruisce l'oggetto WeatherStats.
 * 
 * @author DiegoSantarelli
 * @author SimoneRecinelli
 *
 */

public class WeatherStats {
	
	private double temp,tempMax,tempMin,feelsLike;
	private String dataStats, timeStats;
	private Vector<WeatherStats> weatVec = new Vector<WeatherStats>();
	
	/**
	 * 
	 * Costruttore della classe per il calcolo delle statistiche
	 * 
	 * @param temp			Temperatura
	 * @param tempMax		Temperatura massima
	 * @param tempMin		Temperatura minima
	 * @param feelsLike		Temperatura percepita
	 * @param date			Giorno a cui si riferisce la previsione
	 * @param time			Ora a cui si riferisce la previsione
	 * 
	 */
	
	public WeatherStats(double temp ,double tempMax, double tempMin, double feelsLike ,String date, String time) {
		this.temp = temp;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
		this.feelsLike = feelsLike;
		this.dataStats = date;
		this.timeStats = time;
	}
	
	/**
	 * Restituisce la temperatura reale.
	 * @return <code>double</code>
	 */
	
	public double getTemp() {
		return temp;
	}
	
	/**
	 * Permette all'utente di settare la temperatura reale.
	 * @param temp rappresenta la tempreatura
	 */
	
	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	/**
	 * Restituisce la temperatura massima.
	 * @return <code>double</code>
	 */
	
	public double getTempMax() {
		return tempMax;
	}
	
	/**
	 * Permette all'utente di settare la temperatura massima.
	 * @param tempMax rappresenta la temperatura massima
	 */
	
	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}
	
	/**
	 * Restituisce la temperatura minima.
	 * @return <code>double</code>
	 */
	
	public double getTempMin() {
		return tempMin;
	}
	
	/**
	 * Permette all'utente di settare la temperatura minima.
	 * @param tempMin rappresenta la temperatura minima
	 */
	
	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}
	
	/**
	 * Restituisce la temperatura percepita.
	 * @return <code>double</code>
	 */
	
	public double getFeelsLike() {
		return feelsLike;
	}
	
	/**
	 * Permette all'utente di settare la temperatura percepita.
	 * @param feelsLike rappresenta la temperatura percepita
	 */
	
	public void setFeelsLike(double feelsLike) {
		this.feelsLike = feelsLike;
	}
	
	/**
	 * Restituisce la data in cui ?? stata effettuata la previsione.
	 * @return <code>LocalData</code>
	 */
	
	public String getDataStats() {
		return dataStats;
	}
	
	/**
	 * Permette all'utente di settare la data in cui ?? stata effettuata la previsione.
	 * @param dataStats rappresenta la data in cui ?? stata effettuata la previsione
	 */
	
	public void setDataStats(String dataStats) {
		this.dataStats = dataStats;
	}
	
	/**
	 * Restituisce l'ora, i minuti e i secondi in cui ?? stata effettuata la previsione.
	 * @return <code>LocalTime</code>
	 */
	
	public String getTimeStats() {
		return timeStats;
	}
	
	/**
	 * Permette all'utente di settare l'ora, i minuti e i secondi in cui ?? stata effettuata la previsione.
	 * @param timeStats rappresenta l'ora in cui ?? stata effettuata la previsione
	 */
	
	public void setTimeStats(String timeStats) {
		this.timeStats = timeStats;
	}
	
	/**
	 * Restituisce il vettore della classe WeatherStats.
	 * @return <code>Vector</code>
	 */

	public Vector<WeatherStats> getWeatVec() {
		return weatVec;
	}
	
	/**
	 * Permette di settare il vettore della classe WeatherStats.
	 * @param weatVec
	 */

	public void setWeatVec(Vector<WeatherStats> weatVec) {
		this.weatVec = weatVec;
	}
	
}
