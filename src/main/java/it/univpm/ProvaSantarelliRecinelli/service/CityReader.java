package it.univpm.ProvaSantarelliRecinelli.service;


import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;


import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.model.City;
import it.univpm.ProvaSantarelliRecinelli.model.Weather;
import it.univpm.ProvaSantarelliRecinelli.model.Wind;
/**
 * Questa classe gestisce la lettura del file e l'estrazione dei dati interessati.
 * @author SimoneRecinelli
 * @author DiegoSantarelli
 */

public class CityReader{
	/*
	 * PATH SIMONE /Users/simonerecinelli/Desktop/ProvaSantarelliRecinelli/src/main/resources/APIForecastANCONA
	 *  PATH DIEGO  C:\Users\diego\OneDrive\Desktop\ProvaSantarelliRecinelli\ProvaSantarelliRecinelli\src\main\resources\APIForecastANCONA
	 */
	private City city;
	private Vector <Weather> weat= new Vector <Weather>();
	private Vector <Wind> windVec = new Vector <Wind>();
	
	/**
	 * Costruttore della classe.
	 *
	 * @param cityName rappresenta la città
	 * @param country rappresenta il Paese
	 * @throws WrongCityException eccezione dovuta all'inserimento di una città o Paese errati.
	 */
	
	public CityReader(String cityName, String country) throws WrongCityException {
		City c = new City(cityName,country);
		this.city = c;
		
		/*
		 * Ciclo if che permette di settare di default Ancona come città e IT come Paese
		 * se l'utente non passa uno dei due parametri o entrambi contemporaneamente.
		 */
		
		if (cityName == null || country == null) {
			cityName = "Ancona";
			country = "IT";
			City c1 = new City(cityName,country);
			this.city = c1;
		}  else if (!cityName.equals("Ancona") || !country.equals("IT")) throw new WrongCityException();
	}
	
	/**
	 * Metodo che legge il file JSON "APIForecastANCONA.txt" e inserisce tutte le informazioni in un JSONObject, restituendolo.
	 * @return <code>JSONObject</code>
	 */
	
	public JSONObject caricaOggetto() {
		JSONParser jsonParser = new JSONParser();
		JSONObject objCity = null;
		
		try (FileReader reader = new FileReader("/Users/simonerecinelli/Desktop/ProvaSantarelliRecinelli/src/main/resources/APIForecastANCONA")){
			//A questo punto legge il JSON file
			Object obj = jsonParser.parse(reader);
			objCity = new JSONObject();
			return objCity = (JSONObject) obj;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return objCity;
	}
	
	/**
	 * Metodo che parsifica il JSONObject ricevuto dal file JSON "APIForecastANCONA.txt" (tramite la funzione caricaOggetto) 
	 * e restituisce un oggetto di tipo City con le caratteristiche che ci interessano: temperatura reale, percepita, 
	 * minima, massima, descrizione generale sulle previsioni e velocità del vento.
	 * @return <code>City</code> 
	 */
	
	public City JSONParsing(){
		
		Weather appoggio;
		JSONObject obj = caricaOggetto();
		JSONArray list = (JSONArray) obj.get("list");
		
		String descr, main2;
		
		double temp;
		double tempMin;
		double tempMax;
		double feelsLike;
		double windSpeed;
		
		LocalDateTime datetime;
		String date;
		String time;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		JSONObject objList = new JSONObject();
		JSONArray weather;
		JSONObject objMain;
		JSONObject objWind;
		JSONObject objWeather = new JSONObject();
		
		for(int i=0; i<list.size(); i++) {
			objList = (JSONObject) list.get(i);
			
			datetime = LocalDateTime.parse(objList.get("dt_txt").toString(), formatter);
			date = datetime.toLocalDate().toString();
			time = datetime.toLocalTime().toString();
			
			weather = (JSONArray) objList.get("weather");
			objWeather = (JSONObject) weather.get(0);
			
			descr = (String) objWeather.get("description");
			main2 = (String) objWeather.get("main");
			
			objMain = (JSONObject) objList.get("main");
			temp=Double.parseDouble(objMain.get("temp").toString());
			tempMin=Double.parseDouble(objMain.get("temp_min").toString());
			tempMax=Double.parseDouble(objMain.get("temp_max").toString());
			feelsLike=Double.parseDouble(objMain.get("feels_like").toString());
			
			objWind = (JSONObject ) objList.get("wind");
			windSpeed = (double) objWind.get("speed");
			
			appoggio = new Weather(windSpeed, temp, tempMax, tempMin, feelsLike ,date, time, descr ,main2);
			this.weat.add(appoggio);
		}
		this.city.setVector(this.weat);	
		return this.city;
		}
	
	/**
	 * Metodo che parsifica il JSONObject ricevuto dal file JSON "APIForecastANCONA.txt" (tramite il metodo caricaOggetto) 
	 *  e restituisce un oggetto di tipo City con la caratteristica d'interesse: la velocità del vento (wind speed).
	 * @return <code>City</code> 
	 */
	
	public City JSONParsingWind() {
		double windSpeed;
		
		Wind appoggio;
		JSONObject obj = caricaOggetto();
		JSONArray list = (JSONArray) obj.get("list");
		
		LocalDateTime datetime;
		String date;
		String time;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		JSONObject objList = new JSONObject();
		JSONArray Weather;
		JSONObject objWind;
		JSONObject objWeather = new JSONObject();
		
		for(int i=0; i<list.size(); i++) {
			objList = (JSONObject) list.get(i);
			
			datetime = LocalDateTime.parse(objList.get("dt_txt").toString(), formatter);
			date = datetime.toLocalDate().toString();
			time = datetime.toLocalTime().toString();
			
			Weather = (JSONArray) objList.get("weather");
			objWeather = (JSONObject) Weather.get(0);
			
			objWind = (JSONObject ) objList.get("wind");
			windSpeed = (double) objWind.get("speed");
			
			appoggio = new Wind(windSpeed, date, time);
			this.windVec.add(appoggio);
		}
		this.city.setWindVec(this.windVec);	
		return this.city;
	}
	
	/**
	 * Metodo che converte in JSONObject un JSONArray il quale contiene tutte le informazioni che ci interessano: temperatura 
	 * reale, percepita, minima, massima, descrizione generale sulle previsioni e velocità del vento.
	 * @return <code>JSONObject</code>
	 */
	
	
}

