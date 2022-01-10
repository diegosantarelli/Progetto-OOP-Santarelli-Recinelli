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
	
	private double wind_speed;
	LocalDate date;
	LocalTime time;
	
	/**
	 * Costruttore della classe.
	 * 
	 * @param wind_speed rappresenta la velocità del vento
	 */
	
	public Wind(double wind_speed, LocalDate date, LocalTime time) {
		this.wind_speed = wind_speed;
		this.date = date;
		this.time = time;
	}
	
	/**
	 * Restituisce la velocità del vento.
	 * @return <code>double</code>
	 */
	
	public double getWind_speed() {
		return wind_speed;
	}
	
	/**
	 * Permette all'utente di settare la velocità del vento.
	 * @param wind_speed rappresenta la velocità del vento
	 */

	public void setWind_speed(double wind_speed) {
		this.wind_speed = wind_speed;
	}
	
	/**
	 * Restituisce la data della previsione.
	 * @return <code>LocalDate</code>
	 */
	
	public LocalDate getDate() {
		return date;
	}
	
	/**
	 * Permette all'utente di settare la data della previsione.
	 * @return <code>LocalDate</code>
	 */
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	/**
	 * Restituisce l'ora, i minuti e i secondi della previsione.
	 * @return <code>LocalTime</code>
	 */
	
	public LocalTime getTime() {
		return time;
	}
	
	/**
	 * Permette all'utente di settare l'ora, i minuti e i secondi della previsione.
	 * @param time rappresenta l'ora, i minuti e i secondi della previsione
	 */
	
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
}
