package it.univpm.ProvaSantarelliRecinelli.stats;

import java.io.FileNotFoundException; 
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.model.City;
import it.univpm.ProvaSantarelliRecinelli.model.Weather;


/**
 * PATH SIMONE /Users/simonerecinelli/Desktop/ProvaSantarelliRecinelli/src/main/resources/APIForecastEveryHour
 * PATH DIEGO C:\Users\diego\OneDrive\Desktop\ProvaSantarelliRecinelli\ProvaSantarelliRecinelli\src\main\resources\APIForecastEveryHour
 * @author simonerecinelli
 *
 */
public class Stats {
	private City city;
	private Vector <Weather> weat= new Vector <Weather>();
	private Vector <Weather> weatStats= new Vector <Weather>();
	double temp, tempMin, tempMax, feelsLike;
	double tempMinStats, tempMaxStats, feelsLikeStatsMin, feelsLikeStatsMax;
	double mediaTemp, mediaFeelsLike, varianzaTemp, varianzaFeelsLike;
	/**
	 * 
	 * @return
	 */
	public JSONObject caricaOggettoStats() {
		JSONParser jsonParser = new JSONParser();
		JSONObject cityList = null;
		
		try (FileReader reader = new FileReader("C:\\Users\\diego\\OneDrive\\Desktop\\ProvaSantarelliRecinelli\\ProvaSantarelliRecinelli\\src\\main\\resources\\APIForecastEveryHour")){
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
	 * Questo metodo parsifica il JSONObject ricevuto dal file
	 * @return city ossia un oggetto città con le caratteristiche che ci interessano
	 * @throws WrongCityException eccezione riguardante l'inserimento di una città errata
	 */
	public City JSONParsingStats() throws WrongCityException {
		
		Weather appoggio;
		JSONObject obj = caricaOggettoStats();
		JSONArray list = (JSONArray) obj.get("list");
		
		String date;
		JSONObject objList = new JSONObject();
		JSONArray Weather;
		JSONObject objMain;
		JSONObject objWeather = new JSONObject();
		
		for(int i=0; i<list.size(); i++) {
			objList = (JSONObject) list.get(i);
			date = (String) objList.get("dt_txt");
			
			Weather = (JSONArray) objList.get("weather");
			objWeather = (JSONObject) Weather.get(0);
			
			objMain = (JSONObject) objList.get("main");
			temp=Double.parseDouble(objMain.get("temp").toString());
			tempMin=Double.parseDouble(objMain.get("temp_min").toString());
			tempMax=Double.parseDouble(objMain.get("temp_max").toString());
			feelsLike=Double.parseDouble(objMain.get("feels_like").toString());
			
			appoggio = new Weather(temp, tempMax, tempMin, feelsLike, date);
			this.weat.add(appoggio);
		}
		this.city.setVector(this.weat);	
		return this.city;
	}
	/**
	 * 
	 * @param cityName
	 * @param country
	 * @return
	 * @throws WrongCityException
	 */
	
	public double TempMin(String cityName, String country) throws WrongCityException {
		 
		 City c = new City(cityName,country);
		 this.city = c;
		 c = JSONParsingStats();
		 weatStats = c.getVector();
		 tempMinStats = weatStats.get(0).getTemp_min();
		 
		 for(int i=1 ; i<weatStats.size(); i++) {
			if (tempMinStats > weatStats.get(i).getTemp_min()) {
				tempMinStats = weatStats.get(i).getTemp_min();
			}
		 }
		 return tempMinStats;
	}	
	/**
	 * 
	 * @param cityName
	 * @param country
	 * @return
	 * @throws WrongCityException
	 */
	public double TempMax(String cityName, String country) throws WrongCityException {
		 
		 City c = new City(cityName,country);
		 this.city = c;
		 c = JSONParsingStats();
		 weatStats = c.getVector();
		 tempMaxStats = weatStats.get(0).getTemp_max();
		 
		 for(int i=1 ; i<weatStats.size(); i++) {
			if (tempMaxStats < weatStats.get(i).getTemp_max()) {
				tempMaxStats = weatStats.get(i).getTemp_max();
			}
		 }
		 return tempMaxStats;
	}
	
	/**
	 * 
	 * @param cityName
	 * @param country
	 * @return
	 * @throws WrongCityException
	 */
	public double FeelsLikeMin(String cityName, String country) throws WrongCityException {
		 
		 City c = new City(cityName,country);
		 this.city = c;
		 c = JSONParsingStats();
		 weatStats = c.getVector();
		 feelsLikeStatsMin = weatStats.get(0).getFeels_like();
		 
		 for(int i=1 ; i<weatStats.size(); i++) {
			if (feelsLikeStatsMin > weatStats.get(i).getFeels_like()) {
				feelsLikeStatsMin = weatStats.get(i).getFeels_like();
			}
		 }
		 return feelsLikeStatsMin;
	}
	
	public double FeelsLikeMax(String cityName, String country) throws WrongCityException {
		 
		 City c = new City(cityName,country);
		 this.city = c;
		 c = JSONParsingStats();
		 weatStats = c.getVector();
		 feelsLikeStatsMax = weatStats.get(0).getFeels_like();
		 
		 for(int i=1 ; i<weatStats.size(); i++) {
			if (feelsLikeStatsMax < weatStats.get(i).getFeels_like()) {
				feelsLikeStatsMax = weatStats.get(i).getFeels_like();
			}
		 }
		 return feelsLikeStatsMax;
	}
	/**
	 * 
	 * @param cityName
	 * @param country
	 * @return
	 * @throws WrongCityException
	 */
	public double MediaTemp(String cityName, String country) throws WrongCityException {
		
		 City c = new City(cityName,country);
		 this.city = c;
		 c = JSONParsingStats();
		 weatStats = c.getVector();
		 int i;
		 double tot = 0;
		 for(i=0 ; i<weatStats.size(); i++) {
			 tot =+ weatStats.get(i).getTemp();
		 }
		 double media = tot/i;
		 return media;
	}
	
	public double MediaFeelsLike(String cityName, String country) throws WrongCityException {
		
		 City c = new City(cityName,country);
		 this.city = c;
		 c = JSONParsingStats();
		 weatStats = c.getVector();
		 int i;
		 double tot = 0;
		 for(i=0 ; i<weatStats.size(); i++) {
			 tot =+ weatStats.get(i).getFeels_like();
		 }
		 mediaFeelsLike = tot/i;
		 return mediaFeelsLike;
	}
	
	public double VarianzaTemp(String cityName, String country) throws WrongCityException {
		
		Stats s = new Stats();;
		City c = new City(cityName,country);
		this.city = c;
		c = JSONParsingStats();
		weatStats = c.getVector();
		double appoggio = 0;
		int i;
		 
		for(i=0 ; i<weatStats.size(); i++) {
			appoggio += (weatStats.get(i).getTemp() - s.MediaTemp(cityName, country))*(weatStats.get(i).getTemp() - s.MediaTemp(cityName, country));
		 }
		double varianzaTemp = appoggio / (i - 1);
		return varianzaTemp;
	}
	
	public double VarianzaFeelsLike(String cityName, String country) throws WrongCityException {
		
		Stats s = new Stats();;
		City c = new City(cityName,country);
		this.city = c;
		c = JSONParsingStats();
		weatStats = c.getVector();
		double appoggio = 0;
		int i;
		 
		for(i=0 ; i<weatStats.size(); i++) {
			appoggio += (weatStats.get(i).getFeels_like() - s.MediaTemp(cityName, country))*(weatStats.get(i).getFeels_like() - s.MediaTemp(cityName, country));
		 }
		double varianzaFeelsLike = appoggio / (i - 1);
		return varianzaFeelsLike;
	}
	
	
	// ROTTE DEI METODI NEL CONTROLLER
	
	
	
	
	
}
