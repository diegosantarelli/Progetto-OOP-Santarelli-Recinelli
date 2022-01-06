package it.univpm.ProvaSantarelliRecinelli.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class WeatherStats {
	double temp,tempMax,tempMin,feelsLike;
	LocalDate dataStats;
	LocalTime timeStats;
	public WeatherStats(double temp ,double tempMax, double tempMin, double feelsLike ,LocalDate date, LocalTime time) {
		this.temp = temp;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
		this.feelsLike = feelsLike;
		this.dataStats = date;
		this.timeStats = time;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getTempMax() {
		return tempMax;
	}
	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}
	public double getTempMin() {
		return tempMin;
	}
	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}
	public double getFeelsLike() {
		return feelsLike;
	}
	public void setFeelsLike(double feelsLike) {
		this.feelsLike = feelsLike;
	}
	public LocalDate getDataStats() {
		return dataStats;
	}
	public void setDataStats(LocalDate dataStats) {
		this.dataStats = dataStats;
	}
	public LocalTime getTimeStats() {
		return timeStats;
	}
	public void setTimeStats(LocalTime timeStats) {
		this.timeStats = timeStats;
	}
}
