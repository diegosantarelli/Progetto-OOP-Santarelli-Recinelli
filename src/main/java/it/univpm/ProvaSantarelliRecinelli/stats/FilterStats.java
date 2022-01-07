package it.univpm.ProvaSantarelliRecinelli.stats;

import java.io.FileNotFoundException;  
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.model.*;



/**
 * PATH SIMONE /Users/simonerecinelli/Desktop/ProvaSantarelliRecinelli/src/main/resources/APIForecastEveryHour
 * PATH DIEGO C:\Users\diego\OneDrive\Desktop\ProvaSantarelliRecinelli\ProvaSantarelliRecinelli\src\main\resources\APIForecastEveryHour
 * @author simonerecinelli
 *
 */
public class FilterStats {
	
	private City city;
	private Vector <WeatherStats> weat= new Vector <WeatherStats>();
	private Vector <WeatherStats> weatStats= new Vector <WeatherStats>();
	double temp, tempMin, tempMax, feelsLike;
	double tempMinStats, tempMaxStats, feelsLikeStatsMin, feelsLikeStatsMax;
	double mediaTemp, mediaFeelsLike, varianzaTemp, varianzaFeelsLike;
	String descr, main;
	
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
		
		WeatherStats appoggio;
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

			Weather = (JSONArray) objList.get("weather");
			objWeather = (JSONObject) Weather.get(0);
			
			objMain = (JSONObject) objList.get("main");
			temp=Double.parseDouble(objMain.get("temp").toString());
			tempMin=Double.parseDouble(objMain.get("temp_min").toString());
			tempMax=Double.parseDouble(objMain.get("temp_max").toString());
			feelsLike=Double.parseDouble(objMain.get("feels_like").toString());

			
			datetime = LocalDateTime.parse(objList.get("dt_txt").toString(), formatter);
			date = datetime.toLocalDate();
			time = datetime.toLocalTime();
			
			appoggio = new WeatherStats(temp, tempMax, tempMin, feelsLike, date, time);
			this.weat.add(appoggio);
		}
		this.city.setVectorStats(this.weat);	
		return this.city;
	}
		
	public JSONObject FilterDay (LocalDate day, String cityName, String country) throws WrongCityException {
		
		 City c = new City(cityName,country);
		 this.city = c;
		 c = JSONParsingStats();
		 weatStats = c.getVectorStats();
		 
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
		 LocalDate j = weatStats.get(i).getDataStats();
		 
		 for (i = 0; i < weatStats.size(); i++) {
			 
			 j = weatStats.get(i).getDataStats();
			 
			 if (day.equals(j)) {
				 if (tempMax < weatStats.get(i).getTempMax()) {
						tempMax = weatStats.get(i).getTempMax();
					}
				 
				 if (tempMin < weatStats.get(i).getTempMin()) {
						tempMin = weatStats.get(i).getTempMin();
					}
				 
				 if (feelsLikeStatsMin > weatStats.get(i).getFeelsLike()) {
						feelsLikeStatsMin = weatStats.get(i).getFeelsLike();
					}
				 
				 if (feelsLikeStatsMax < weatStats.get(i).getFeelsLike()) {
						feelsLikeStatsMax = weatStats.get(i).getFeelsLike();
					}
				 
				 mediaTemp += weatStats.get(i).getTemp();
				 mediaFeelsLike += weatStats.get(i).getFeelsLike(); 
			 }
		 }
		 
		 mediaTemp /= i;
		 mediaFeelsLike /= i;
		 
		 i = 0;
		 j = weatStats.get(i).getDataStats();
		 
		 for (i = 0; i < weatStats.size(); i++) {
			 
			 j = weatStats.get(i).getDataStats();
			 
			 if (day.equals(j)) {
				 varianzaTemp = (weatStats.get(i).getTemp() - mediaTemp)*(weatStats.get(i).getTemp() - mediaTemp);
				 varianzaFeelsLike = (weatStats.get(i).getFeelsLike() - mediaFeelsLike)*(weatStats.get(i).getFeelsLike() - mediaFeelsLike);	 
			 }
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
	
	public WeatherStats Filter1Hour(LocalDate date ,LocalTime time, String cityName, String country) throws WrongCityException {
		 
		 City c = new City(cityName,country);
		 this.city = c;
		 c = JSONParsingStats();
		 weatStats = c.getVectorStats();
		 
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
		 
		 int i = 0 , n = 0;
		 LocalDate k;
		 LocalTime j;
		 
		 for (i = 0; i < weatStats.size(); i++) {
			 
			 k = weatStats.get(i).getDataStats();
	
			 if (date.compareTo(k) == 0) {

				 for (n = 0; n < weatStats.size(); n++) {
			 
					 j = weatStats.get(n).getTimeStats();
					 k = weatStats.get(n).getDataStats();
					 
					 if ((date.compareTo(k) == 0) && (time.compareTo(j) == 0)) {
						 
						 return weatStats.get(n);
						 
					 }
				 }
			 }
		 } return null;
	}

	public JSONObject FilterPerHours(String time, String cityName, String country) throws WrongCityException {
	 
	 City c = new City(cityName,country);
	 this.city = c;
	 c = JSONParsingStats();
	 weatStats = c.getVectorStats();
	 
	 JSONObject objFilter = new JSONObject();
	 
	 String[] rangeTime = SplitRange(time);
	 
	 LocalTime timeStart = null, timeEnd = null;
	 
	 try {
		 timeStart = LocalTime.parse(rangeTime[0]);
		 timeEnd = LocalTime.parse(rangeTime[1]);
	 } catch(DateTimeParseException e) {
		 
	 } catch (ArrayIndexOutOfBoundsException e) {
		 
	 }
	 
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
	 
	 int n = 0;
	 LocalTime j;
	
	 for (n = 0; n < weatStats.size(); n++) {
			 
		j = weatStats.get(n).getTimeStats();
			 
		if (j.isAfter(timeStart) && j.isBefore(timeEnd)) {
					 
			if (tempMax < weatStats.get(n).getTempMax()) tempMax = weatStats.get(n).getTempMax();
						 
			if (tempMin < weatStats.get(n).getTempMin()) tempMin = weatStats.get(n).getTempMin();
						 
			if (feelsLikeStatsMin > weatStats.get(n).getFeelsLike()) feelsLikeStatsMin = weatStats.get(n).getFeelsLike();
					 
			if (feelsLikeStatsMax < weatStats.get(n).getFeelsLike()) feelsLikeStatsMax = weatStats.get(n).getFeelsLike();
				 
			mediaTemp += weatStats.get(n).getTemp();
			mediaFeelsLike += weatStats.get(n).getFeelsLike(); 
		}
	 }

	 mediaTemp /= n;
	 mediaFeelsLike /= n;
 	
	for (n = 0; n < weatStats.size(); n++) {
			
		j = weatStats.get(n).getTimeStats();
		//System.out.println(j);
		
		if (j.isAfter(timeStart) && j.isBefore(timeEnd)) {
			
			System.out.println("ciao");
				
			varianzaTemp = (weatStats.get(n).getTemp() - mediaTemp)*(weatStats.get(n).getTemp() - mediaTemp);
			varianzaFeelsLike = (weatStats.get(n).getFeelsLike() - mediaFeelsLike)*(weatStats.get(n).getFeelsLike() - mediaFeelsLike);
			
		}	
	}
 
	varianzaTemp /= n-1;
	varianzaFeelsLike /= n-1;
		 
	objFilter.put("Temperatura massima", tempMax);
	objFilter.put("Temperatura minima: ", tempMin);
	objFilter.put("Media delle temperature reali", mediaTemp);
	objFilter.put("Media delle temperature percepite", mediaFeelsLike);
	objFilter.put("Varianza delle temperature reali", varianzaTemp);
	objFilter.put("Varianza delle temperature percepite", varianzaFeelsLike);
		 
	 return objFilter;
	 
	}
	
	private String[] SplitRange(String range) {
		
		String[] hours;
		try {
			hours = range.split("-");
		} catch(PatternSyntaxException e) {
			hours = null;
		}
		return hours;
	}
}
