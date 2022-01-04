package it.univpm.ProvaSantarelliRecinelli.exception;

import java.io.IOException;

public class WrongFileException extends IOException {

	private static final long serialVersionUID = 1L;

	public WrongFileException() {
		super();
	}
	
	public String getMex() {
		return "ERR: File non esistente";
	}

}
