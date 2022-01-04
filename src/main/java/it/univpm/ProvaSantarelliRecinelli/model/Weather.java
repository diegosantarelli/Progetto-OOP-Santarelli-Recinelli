package it.univpm.ProvaSantarelliRecinelli.model;

public class Weather extends Wind {
	private double temp;
	private double tempMax;
	private double tempMin;
	private double feelsLike;
	private String data , descr , main2;
	
	/** Costruttore dell'oggetto
	 * @param temp 			  Temperatura reale
     * @param temp_max        Temperatura massima
     * @param temp_min        Temperatura minima
	 * @param feelsLike		  Temperatura percepita
     * @param data            Giorno e ora a cui si riferisce la previsione
     */
	public Weather(double windSpeed ,double temp ,double tempMax, double tempMin, double feelsLike ,String data , String descr, String main2) {
		super(windSpeed);
		this.temp = temp;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
		this.feelsLike = feelsLike;
		this.data = data;
		this.descr = descr;
		this.main2 = main2;
	}

	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getFeels_like() {
		return feelsLike;
	}
	public void setFeels_like(double feelsLike) {
		this.feelsLike = feelsLike;
	}
	public double getTemp_max() {
		return tempMax;
	}

	public void setTemp_max(double tempMax) {
		this.tempMax = tempMax;
	}

	public double getTemp_min() {
		return tempMin;
	}

	public void setTemp_min(double tempMin) {
		this.tempMin = tempMin;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getMain2() {
		return main2;
	}

	public void setMain2(String main2) {
		this.main2 = main2;
	}
	
	
}
