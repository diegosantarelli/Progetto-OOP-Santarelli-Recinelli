package it.univpm.ProvaSantarelliRecinelli.model;

/**
 * Classe che rappresenta l'oggetto Wind
 * 
 * @author DiegoSantarelli
 * @author SimoneRecinelli
 *
 */

public class Wind {
	
	private double wind_speed;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param wind_speed rappresenta la velocità del vento
	 */
	
	public Wind(double wind_speed) {
		this.wind_speed = wind_speed;
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
	
}
