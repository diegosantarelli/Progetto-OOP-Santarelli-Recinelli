package it.univpm.ProvaSantarelliRecinelli.model;

public class Weather {
	private double temp;
	private double temp_max;
	private double temp_min;
	private double media;
	private double varianza;
	private double feels_like;
	private String data;
	
	/** Costruttore dell'oggetto
     * @param temp_max        Temperatura massima
     * @param temp_min        Temperatura minima
     * @param media 		  Media della temperatura
     * @param varianza        Varianza della temperatura
     * @param data            Giorno e ora a cui si riferisce la previsione
     */
	public Weather() {
		
	};
	public Weather(double temp ,double temp_max, double temp_min, double media, double varianza, double feels_like ,String data) {
		super();
		this.temp = temp;
		this.temp_max = temp_max;
		this.temp_min = temp_min;
		this.media = media;
		this.varianza = varianza;
		this.feels_like = feels_like;
		this.data = data;
	}

	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getFeels_like() {
		return feels_like;
	}
	public void setFeels_like(double feels_like) {
		this.feels_like = feels_like;
	}
	public double getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}

	public double getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public double getVarianza() {
		return varianza;
	}

	public void setVarianza(double varianza) {
		this.varianza = varianza;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
