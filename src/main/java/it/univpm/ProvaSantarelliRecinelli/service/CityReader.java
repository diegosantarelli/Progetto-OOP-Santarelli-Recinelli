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

public class CityReader{
	
	
	private City city;
	private Vector <Weather> weat= new Vector <Weather>();

	public CityReader(String cityName, String country) throws WrongCityException {
		City c = new City(cityName,country);
		this.city = c;
		
		if (cityName == null || country == null) {
			cityName = "Ancona";
			country = "IT";
			City c1 = new City(cityName,country);
			this.city = c1;
		}  else if (!cityName.equals("Ancona") || !country.equals("IT")) throw new WrongCityException();
	}
	/**
	 * Questo metodo legge il JSON file e inserisce tutto in un JSONObject
	 * @return cityList ossia la lista delle città
	 * PATH SIMONE /Users/simonerecinelli/Desktop/ProvaSantarelliRecinelli/src/main/resources/APIForecastANCONA
	 *  PATH DIEGO  C:\Users\diego\OneDrive\Desktop\ProvaSantarelliRecinelli\ProvaSantarelliRecinelli\src\main\resources\APIForecastANCONA
	 */
	
	public JSONObject caricaOggetto() {
		JSONParser jsonParser = new JSONParser();
		JSONObject cityList = null;
		
		try (FileReader reader = new FileReader("C:\\Users\\diego\\OneDrive\\Desktop\\ProvaSantarelliRecinelli\\ProvaSantarelliRecinelli\\src\\main\\resources\\APIForecastANCONA")){
			//A questo punto legge il JSON file
			Object obj = jsonParser.parse(reader);
			cityList = new JSONObject();
			return cityList = (JSONObject) obj;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cityList;
	}
	/**
	 * Questo metodo parsifica il JSONObject ricevuto dall'API Forecast
	 * @return city ossia un oggetto città con le caratteristiche che ci interessano
	 * @throws WrongCityException eccezione riguardante l'inserimento di una città errata
	 */
	public City JSONParsing() throws WrongCityException {
		
		Weather appoggio;
		JSONObject obj = caricaOggetto();
		JSONArray list = (JSONArray) obj.get("list");
		
		String  descr, main2;
		
		double temp;
		double tempMin;
		double tempMax;
		double feelsLike;
		
		LocalDateTime datetime;
		LocalDate date;
		LocalTime time;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		JSONObject objList = new JSONObject();
		JSONArray Weather;
		JSONObject objMain;
		JSONObject objWind;
		JSONObject objWeather = new JSONObject();
		
		for(int i=0; i<list.size(); i++) {
			objList = (JSONObject) list.get(i);
			//date = (String) objList.get("dt_txt");
			
			datetime = LocalDateTime.parse(objList.get("dt_txt").toString(), formatter);
			date = datetime.toLocalDate();
			time = datetime.toLocalTime();
			
			Weather = (JSONArray) objList.get("weather");
			objWeather = (JSONObject) Weather.get(0);
			
			descr = (String) objWeather.get("description");
			main2 = (String) objWeather.get("main");
			
			objMain = (JSONObject) objList.get("main");
			temp=Double.parseDouble(objMain.get("temp").toString());
			tempMin=Double.parseDouble(objMain.get("temp_min").toString());
			tempMax=Double.parseDouble(objMain.get("temp_max").toString());
			feelsLike=Double.parseDouble(objMain.get("feels_like").toString());
			
			objWind = (JSONObject ) objList.get("wind");
			double windSpeed = (double) objWind.get("speed");
			
			appoggio = new Weather(windSpeed, temp, tempMax, tempMin, feelsLike ,date, time, descr ,main2);
			this.weat.add(appoggio);
		}
		this.city.setVector(this.weat);	
		return this.city;
		}
	
}

