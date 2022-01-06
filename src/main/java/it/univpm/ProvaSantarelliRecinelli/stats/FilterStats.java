package it.univpm.ProvaSantarelliRecinelli.stats;

import java.io.FileNotFoundException; 
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
public class FilterStats {
	
	private City city;
	private Vector <Weather> weat= new Vector <Weather>();
	private Vector <Weather> weatStats= new Vector <Weather>();
	double temp, tempMin, tempMax, feelsLike;
	double tempMinStats, tempMaxStats, feelsLikeStatsMin, feelsLikeStatsMax;
	double mediaTemp, mediaFeelsLike, varianzaTemp, varianzaFeelsLike;
	
	public FilterStats(String cityName, String country) {
		City c = new City(cityName,country);
		this.city = c;
		
		if (cityName == null || country == null) {
			cityName = "Ancona";
			country = "IT";
			City c1 = new City(cityName,country);
			this.city = c1;
			
		}
	}
	
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
		
		LocalDateTime datetime;
		LocalDate date;
		LocalTime time;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		JSONObject objList = new JSONObject();
		JSONArray Weather;
		JSONObject objMain;
		JSONObject objWeather = new JSONObject();
		
		for(int i=0; i<list.size(); i++) {
			
			objList = (JSONObject) list.get(i);
			
			datetime = LocalDateTime.parse(objList.get("dt_txt").toString(), formatter);
			date = datetime.toLocalDate();
			time = datetime.toLocalTime();
			
			Weather = (JSONArray) objList.get("weather");
			objWeather = (JSONObject) Weather.get(0);
			
			objMain = (JSONObject) objList.get("main");
			temp=Double.parseDouble(objMain.get("temp").toString());
			tempMin=Double.parseDouble(objMain.get("temp_min").toString());
			tempMax=Double.parseDouble(objMain.get("temp_max").toString());
			feelsLike=Double.parseDouble(objMain.get("feels_like").toString());
			
			appoggio = new Weather(temp, tempMax, tempMin, feelsLike, date, time);
			this.weat.add(appoggio);
		}
		this.city.setVector(this.weat);	
		return this.city;
	}
	
	public void setCity(City c) {
		this.city = c;
	}
	
	
	@SuppressWarnings({ "unchecked" })
	public JSONObject FilterDay (String day, String cityName, String country) throws WrongCityException {
		
		 City c = new City(cityName,country);
		 this.city = c;
		 c = JSONParsingStats();
		 weatStats = c.getVector();
		 
		 JSONObject objFilter = new JSONObject();
		 
		 temp = 0;
		 feelsLike = 0;
		 tempMin = 0;
		 tempMax = 0;
		 feelsLikeStatsMin = 0;
		 feelsLikeStatsMax = 0;
		 mediaTemp = 0;
		 mediaFeelsLike = 0;
		 varianzaTemp = 0;
		 varianzaFeelsLike = 0;
		 
		 int i = 0;
		 
		 while (day.equals(weatStats.get(i).getData())) {
			 
			 if (tempMax < weatStats.get(i).getTemp_max()) {
					tempMax = weatStats.get(i).getTemp_max();
				}
			 
			 if (tempMin < weatStats.get(i).getTemp_max()) {
					tempMin = weatStats.get(i).getTemp_max();
				}
			 
			 if (feelsLikeStatsMin > weatStats.get(i).getFeels_like()) {
					feelsLikeStatsMin = weatStats.get(i).getFeels_like();
				}
			 
			 if (feelsLikeStatsMax < weatStats.get(i).getFeels_like()) {
					feelsLikeStatsMax = weatStats.get(i).getFeels_like();
				}
			 
			 mediaTemp += weatStats.get(i).getTemp();
			 mediaFeelsLike += weatStats.get(i).getFeels_like();
		 }
		 
		 mediaTemp /= i;
		 mediaFeelsLike /= i;
		 
		 i = 0;
		 
		 while (day.equals(weatStats.get(i).getData())) {
			 
			 varianzaTemp = (weatStats.get(i).getTemp() - mediaTemp);
			 varianzaFeelsLike = (weatStats.get(i).getFeels_like());
			 
		 }
		 
		 varianzaTemp /= i-1;
		 varianzaFeelsLike /= i-1;
		 
		 objFilter.put("Temperatura massima", tempMax);
		 objFilter.put("Temperatura minima: ", tempMin);
		 objFilter.put("Media delle temperature reali", mediaTemp);
		 objFilter.put("Media delle temperature percepite", mediaFeelsLike);
		 objFilter.put("Varianza delle temperature reali", varianzaTemp);
		 objFilter.put("Varianza delle temperature percepite", varianzaFeelsLike);
		 
		 return objFilter;
		 
	}	
	
}
