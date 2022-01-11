package it.univpm.ProvaSantarelliRecinelli.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.service.CityReader;

/**
 * Classe che descrive l'oggetto City.
 * 
 * @author SimoneRecinelli
 * @author DiegoSantarelli
 */

public class City {
	
	private String name;
	private String country;
	private Vector<Weather> vector = new Vector<Weather>();
	private Vector<WeatherStats> vectorStats = new Vector<WeatherStats>();
	private Vector <Wind> windVec = new Vector <Wind>();
	
	/**
	 * Costruttore della classe.
	 * 
	 * @param name rappresenta il nome della città
	 * @param country rappresenta il nome del Paese
	 */	
	
	public City(String name, String country) {
			this.name = name;
			this.country = country;
	}
	
	public JSONObject convertToJSON() throws WrongCityException {
		
		CityReader cityR = new CityReader(name, country);
		double temp;
		double tempMin;
		double tempMax;
		double feelsLike;
		double windSpeed;
		
		String descr, main2;
		
		Weather appoggio;
		JSONObject obj = cityR.caricaOggetto();
		JSONArray list = (JSONArray) obj.get("list");
		
		LocalDateTime datetime;
		String date;
		String time;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		JSONObject objList = new JSONObject();
		JSONArray Weather;
		JSONObject objWind;
		JSONObject objWeather = new JSONObject();
		JSONObject objMain;
		
		JSONArray arr = new JSONArray();
		
		for(int i=0; i<list.size(); i++) {
			
			objList = (JSONObject) list.get(i);
			//date = (String) objList.get("dt_txt");
			
			datetime = LocalDateTime.parse(objList.get("dt_txt").toString(), formatter);
			date = datetime.toLocalDate().toString();
			time = datetime.toLocalTime().toString();
			
			Weather = (JSONArray) objList.get("weather");
			objWeather = (JSONObject) Weather.get(0);
			
			descr = (String) objWeather.get("description");
			main2 = (String) objWeather.get("main");
			
			objMain = (JSONObject) objList.get("main");
			temp=Double.parseDouble(objMain.get("temp").toString());
			tempMin=Double.parseDouble(objMain.get("temp_min").toString());
			tempMax=Double.parseDouble(objMain.get("temp_max").toString());
			feelsLike=Double.parseDouble(objMain.get("feels_like").toString());
			
			objWind = (JSONObject) objList.get("wind");
			windSpeed = (double) objWind.get("speed");
			
			appoggio = new Weather(windSpeed, temp, tempMax, tempMin, feelsLike ,date, time, descr ,main2);
			//this.weat.add(appoggio);
			
			arr.add(appoggio);
		}
		
		JSONObject finalObj = new JSONObject();
		finalObj.put("Weather:", arr);
		
		return finalObj;
	}
	
	/**
	 * Restituisce il vettore della classe Weather.
	 * @return <code>Vector<Weather></code>
	 */
	
	public Vector<Weather> getVector() {
		return vector;
	}
	
	/**
	 * Permette di settare il vettore della classe Weather.
	 * @param vector nome del vettore
	 */
	
	public void setVector(Vector<Weather> vector) {
		this.vector = vector;
	}
	
	/**
	 * Restituisce il vettore della classe WeatherStats
	 * @return <code>Vector<WeatherStats></code>
	 */
	
	public Vector<WeatherStats> getVectorStats() {
		return vectorStats;
	}
	
	/**
	 * Permette di settare il vettore della classe WeatherStats.
	 * @param vectorStats
	 */

	public void setVectorStats(Vector<WeatherStats> vectorStats) {
		this.vectorStats = vectorStats;
	}

	/**
	 * Restituisce il nome di una città.
	 * @return <code>String</code>
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * Permette di settare il nome della città.
	 * @param name rappresenta il nome della città
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Restituisce il Paese.
	 * @return <code>String</code>
	 */
	
	public String getCountry() {
		return country;
	}
	
	/**
	 * Permette di settare il Paese
	 * @param country rappresenta il Paese
	 */
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Restituisce il vettore della classe Wind
	 * @return <code>Vector<Wind></code>
	 */
	public Vector<Wind> getWindVec() {
		return windVec;
	}
	
	/**
	 * Permette di settare il vettore della classe Wind
	 * @param windVec rappresenta un vettore della classe Wind
	 */
	public void setWindVec(Vector<Wind> windVec) {
		this.windVec = windVec;
	}
		
	
}

