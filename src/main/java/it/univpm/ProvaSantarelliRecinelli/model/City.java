package it.univpm.ProvaSantarelliRecinelli.model;

import java.util.Vector;
/**
 * <p>
 * <b>Classe</b> che costruise l'oggetto <i>città</i> che ha come parametri il <i>nome</i> e lo <i>stato</i>
 * <p>
 * 
 * @author Simone_Recinelli
 * @author Diego_Santarelli
 */

public class City {
	/**
	 * 
	 * @param name indica il nome della città
	 * @param country indica lo stato
	 */	
		private String Name;
		private String Country;
		private Vector<Weather> vector = new Vector<Weather>();
	/**
	 * Costruttore dell'oggetto City
	 */
		public City(String Name, String Country) {
			this.Name = Name;
			this.Country = Country;
		}
		public Vector<Weather> getVector() {
		return vector;
	}
	public void setVector(Vector<Weather> vector) {
		this.vector = vector;
	}
		public City() {};
	/**
	 * Ritorna il nome di una città in formato string
	 * @return nome della città di tipo <code>String<code>
	 */
	public String getName() {
		return Name;
	}
	/**
	 * Setta il nome della città
	 * @param name indica il nome della città di riferimento
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * Ritorna lo stato di una città in formato string
	 * @return lo stato della città di tipo <code>String<code>
	 */
	public String getCountry() {
		return Country;
	}
	/**
	 * Setta lo stato della città
	 * @param country indica lo stato della città
	 */
	public void setCountry(String country) {
		Country = country;
	}
		
}

