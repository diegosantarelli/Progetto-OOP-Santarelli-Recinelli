package it.univpm.ProvaSantarelliRecinelli.exception;

import java.io.IOException;

public class WrongCityException extends IOException {
	
	private static final long serialVersionUID = 1L;
	
	public WrongCityException() {
		super();
	}
	
	public String getErr() {
		return "ERR: Città o Paese inseriti non corretti";
	}

}
