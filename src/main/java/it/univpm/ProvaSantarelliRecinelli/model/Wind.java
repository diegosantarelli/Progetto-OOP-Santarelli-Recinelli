package it.univpm.ProvaSantarelliRecinelli.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe che rappresenta l'oggetto Wind
 * 
 * @author DiegoSantarelli
 * @author SimoneRecinelli
 *
 */

public class Wind {
	
	private double wind_speed;
	LocalDate date;
	LocalTime time;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param wind_speed rappresenta la velocità del vento
	 */
	
	public Wind(double wind_speed, LocalDate date, LocalTime time) {
		this.wind_speed = wind_speed;
		this.date = date;
		this.time = time;
	}
	
	/**
	 * Ritorna la velocità del vento
	 * 
	 * @return <code>double</code>
	 */
	
	public double getWind_speed() {
		return wind_speed;
	}
	
	/**
	 * Permette all'utente di settare la velocità del vento
	 * 
	 * @param wind_speed rappresenta la velocità del vento
	 */

	public void setWind_speed(double wind_speed) {
		this.wind_speed = wind_speed;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
	
}
