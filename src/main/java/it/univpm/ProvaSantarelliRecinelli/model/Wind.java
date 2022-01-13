package it.univpm.ProvaSantarelliRecinelli.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe che costruisce l'oggetto Wind.
 * 
 * @author DiegoSantarelli
 * @author SimoneRecinelli
 */

public class Wind {
	
	private double windSpeed;
	String date;
	String time;
	
	/**
	 * Costruttore della classe.
	 * 
	 * @param wind_speed rappresenta la velocità del vento
	 */
	
	public Wind(double windSpeed, String date, String time) {
		this.windSpeed = windSpeed;
		this.date = date;
		this.time = time;
	}
	
	/**
	 * Restituisce la velocità del vento.
	 * @return <code>double</code>
	 */
	
	public double getWindSpeed() {
		return windSpeed;
	}
	
	/**
	 * Permette all'utente di settare la velocità del vento.
	 * @param wind_speed rappresenta la velocità del vento
	 */

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	/**
	 * Restituisce la data della previsione.
	 * @return <code>LocalDate</code>
	 */
	
	public String getDate() {
		return date;
	}
	
	/**
	 * Permette all'utente di settare la data della previsione.
	 * @return <code>LocalDate</code>
	 */
	
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Restituisce l'ora, i minuti e i secondi della previsione.
	 * @return <code>LocalTime</code>
	 */
	
	public String getTime() {
		return time;
	}
	
	/**
	 * Permette all'utente di settare l'ora, i minuti e i secondi della previsione.
	 * @param time rappresenta l'ora, i minuti e i secondi della previsione
	 */
	
	public void setTime(String time) {
		this.time = time;
	}
	
}
