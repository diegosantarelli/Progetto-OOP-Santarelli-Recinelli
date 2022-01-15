package it.univpm.ProvaSantarelliRecinelli.exception;

import java.io.IOException;

/**
 * 
 * Classe che gestisce l'eccezione riguardante l'errato inserimento del path del file. 
 * L'eccezione WrongFileException estende IOException.
 * 
 * @author SimoneRecinelli
 * @author DiegoSantarelli
 * 
 */

public class WrongFileException extends IOException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * Costruttore della classe.
	 * 
	 */
	
	public WrongFileException() {
		super();
	}
	
	/**
	 * 
	 * Metodo che restituisce un messaggio di errore quando il path del file non viene trovato.
	 * 
	 * @return <code>String</code>
	 * 
	 */
	
	public String getMex() {
		return "ERR: File non esistente";
	}

}
