package it.univpm.ProvaSantarelliRecinelli.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Weather extends Wind {
	private double temp;
	private double tempMax;
	private double tempMin;
	private double feelsLike;
	private String descr, main, data2;
	LocalDate data;
	LocalTime time;
	
	
	/** Costruttore dell'oggetto
	 * @param temp 			  Temperatura reale
     * @param temp_max        Temperatura massima
     * @param temp_min        Temperatura minima
	 * @param feelsLike		  Temperatura percepita
     * @param data            Giorno e ora a cui si riferisce la previsione
     */
	public Weather(double windSpeed ,double temp ,double tempMax, double tempMin, double feelsLike , LocalDate date, LocalTime time, String descr, String main) {
		super(windSpeed);
		this.temp = temp;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
		this.feelsLike = feelsLike;
		this.descr = descr;
		this.main = main;
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

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}
	
	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
}
