package it.univpm.ProvaSantarelliRecinelli.stats;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.model.City;
import it.univpm.ProvaSantarelliRecinelli.model.Weather;

public class FilterStats {
	City city;
	String day, hours, week; 
	private Vector <Weather> weatFilterStats= new Vector <Weather>();
	
	/*public FilterStats(String day, String hours, String week) {
		super();
		this.day = day;
		this.hours = hours;
		this.week = week;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}
	*/
	public String FilterDay (String day, String cityName, String country) throws WrongCityException {
		Stats objStats = new Stats();
		City c = new City(cityName,country);
		this.city = c;
		objStats.setCity(c);
		c = objStats.JSONParsingStats();
		weatFilterStats = c.getVector();
		
		for (int i=0; i < weatFilterStats.size(); i++){
			if (day.equals(weatFilterStats.get(i).getData())) {
				day = weatFilterStats.get(i).getData();
			}
		}	
		return "Il giorno è : " + day;
	}
	
}
