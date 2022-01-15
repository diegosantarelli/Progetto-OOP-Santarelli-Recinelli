package it.univpm.ProvaSantarelliRecinelli.exception;

import java.io.IOException;

/**
 * 
 * Classe che gestisce l'eccezione riguardante l'errato inserimento del nome di una città o Paese. 
 * L'eccezione WrongCityException estende IOException.
 * 
 * @author SimoneRecinelli
 * @author DiegoSantarelli
 * 
 */

public class WrongCityException extends IOException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * Costruttore della classe.
	 * 
	 */
	
	public WrongCityException() {
		super();
	}
	
	/**
	 * 
	 * Metodo che restituisce un messaggio di errore quando il nome della città o Paese non viene trovato.
	 * 
	 * @return <code>String</code>
	 * 
	 */
	
	public String getErr() {
		return "ERR: Città o Paese inseriti non corretti";
	}

}
