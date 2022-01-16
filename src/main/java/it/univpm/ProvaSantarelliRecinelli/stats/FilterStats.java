package it.univpm.ProvaSantarelliRecinelli.stats;

import java.io.FileNotFoundException;  
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.exception.WrongFileException;
import it.univpm.ProvaSantarelliRecinelli.model.*;

/**
 * 
 * Classe che permette di calcolare valori minimi, massimi, media e varianza di temperature reali e percepite.
 * 
 * @author DiegoSantarelli
 * @author SimoneRecinelli
 *
 */

public class FilterStats {
	
	private City city;
	private Vector <WeatherStats> weat= new Vector <WeatherStats>();
	private Vector <WeatherStats> weatStats= new Vector <WeatherStats>();
	double temp = 0, tempMin = 0, tempMax = 0, feelsLike = 0;
	double tempMinStats = 0, tempMaxStats = 0, feelsLikeStatsMin = 0, feelsLikeStatsMax = 0;
	double mediaTemp = 0, mediaFeelsLike = 0, varianzaTemp = 0, varianzaFeelsLike = 0;
	String descr, main;
	
	/**
	 * 
	 * Costruttore della classe
	 * 
	 * @param cityName rappresenta il nome della città
	 * @param country rappresenta il Paese della città
	 * 
	 * @throws WrongCityException eccezione dovuta all'inserimento di una città o Paese errati.
	 * 
	 */
	
	public FilterStats(String cityName, String country) throws WrongCityException {

		City c = new City(cityName,country);
		this.city = c;
		
		/*
		 * Ciclo if che permette di settare di default Ancona come città e IT come Paese
		 * se l'utente non passa uno dei due parametri o entrambi contemporaneamente.
		 * 
		 */
		
		if (cityName == null || country == null) {
			cityName = "Ancona";
			country = "IT";
			City c1 = new City(cityName,country);
			this.city = c1;
		} else if (!cityName.equals("Ancona") || !country.equals("IT")) throw new WrongCityException(); 
	
	}
	
	/**
	 * 
	 * Metodo che legge il file JSON "APIForecastEveryHour.txt" e inserisce tutte le informazioni in un JSONObject, restituendolo.
	 * 
	 * @return <code>JSONObject</code>
	 * 
	 * @throws WrongFileException eccezione che restituisce un messaggio di errore quando il path del file non viene trovato.
	 * 
	 */
	
	public JSONObject caricaOggettoStats() throws WrongFileException {
		
		JSONParser jsonParser = new JSONParser();
		JSONObject cityList = null;
		
		 // PATH SIMONE /Users/simonerecinelli/Desktop/ProvaSantarelliRecinelli/src/main/resources/APIForecastEveryHour
		 // PATH DIEGO C:\Users\diego\OneDrive\Desktop\ProvaSantarelliRecinelli\ProvaSantarelliRecinelli\src\main\resources\APIForecastEveryHour

		try (FileReader reader = new FileReader("C:\\Users\\diego\\OneDrive\\Desktop\\ProvaSantarelliRecinelli\\ProvaSantarelliRecinelli\\src\\main\\resources\\APIForecastEveryHour")){
			Object obj = jsonParser.parse(reader);
			cityList = new JSONObject();
			return cityList = (JSONObject) obj;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new WrongFileException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new WrongFileException();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new WrongFileException();
		}
		
	}
	
	/**
	 * 
	 * Metodo che parsifica il JSONObject ricevuto dal file salvato in locale "APIForecastEveryHour.txt".
	 * 
	 * @return <code>City</code>
	 * 
	 * @throws WrongCityException eccezione riguardante l'inserimento di una città errata.
	 * @throws WrongFileException eccezione che restituisce un messaggio di errore quando il path del file non viene trovato.
	 * 
	 */
	
	public City JSONParsingStats() throws WrongCityException, WrongFileException {
		
		WeatherStats appoggio;
		JSONObject obj = caricaOggettoStats();
		
		String date;
		String time;
		
		JSONArray weatArray = (JSONArray) obj.get("Weather");
		JSONObject objWeatArr = new JSONObject();
		
		for(int i=0; i<weatArray.size(); i++) {
			
			objWeatArr = (JSONObject) weatArray.get(i);
			
			temp=Double.parseDouble(objWeatArr.get("Temperatura reale").toString());
			tempMin=Double.parseDouble(objWeatArr.get("Temperatura minima").toString());
			tempMax=Double.parseDouble(objWeatArr.get("Temperatura massima").toString());
			feelsLike=Double.parseDouble(objWeatArr.get("Temperatura percepita").toString());
			date = objWeatArr.get("Date").toString();
			time = objWeatArr.get("Time").toString();
			
			appoggio = new WeatherStats(temp, tempMax, tempMin, feelsLike, date, time);
			this.weat.add(appoggio);
		}
		
		this.city.setVectorStats(this.weat);	
		return this.city;
		
	}
	
	/**
	 * 
	 * Metodo che permette di filtrare le statistiche salvate in locale nel file "APIForecastEveryHour.txt" relative ad un giorno scelto dall'utente.
	 * 
	 * @param day rappresenta il giorno scelto dall'utente
	 * @param cityName rappresenta il nome della città
	 * @param country rappresenta il Paese della città
	 * 
	 * @return <code>JSONObject</code>
	 * 
	 * @throws WrongCityException eccezione riguardante l'inserimento di una città errata.
	 * @throws WrongFileException eccezione che restituisce un messaggio di errore quando il path del file non viene trovato.
	 * 
	 */
		
	public JSONObject FilterDay (LocalDate day, String cityName, String country) throws WrongCityException, WrongFileException {
		
		 City c = new City(cityName,country);
		 this.city = c;
		 c = JSONParsingStats();
		 weatStats = c.getVectorStats();
		 
		 JSONObject objFilter = new JSONObject();
		 
		 int i = 0;
		 String j;
		 LocalDate d;
		 
		 for (i = 0; i < weatStats.size(); i++) {
			 
			 j = weatStats.get(i).getDataStats();
			 d = LocalDate.parse(j);
			 
			 if (day.compareTo(d) == 0) {
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
		 
		 for (i = 0; i < weatStats.size(); i++) {
			 
			 j = weatStats.get(i).getDataStats();
			 d = LocalDate.parse(j);
			 
			 if (day.compareTo(d) == 0) {
				 varianzaTemp += (weatStats.get(i).getTemp() - mediaTemp)*(weatStats.get(i).getTemp() - mediaTemp);
				 varianzaFeelsLike += (weatStats.get(i).getFeelsLike() - mediaFeelsLike)*(weatStats.get(i).getFeelsLike() - mediaFeelsLike);	 
			 }
		 }
		 
		 varianzaTemp /= i-1;
		 varianzaFeelsLike /= i-1;
		 
		 objFilter.put("Temperatura massima", tempMax);
		 objFilter.put("Temperatura minima", tempMin);
		 objFilter.put("Temperatura percepita massima", feelsLikeStatsMax);
		 objFilter.put("Temperatura percepita minima", feelsLikeStatsMin);
		 objFilter.put("Media delle temperature reali", mediaTemp);
		 objFilter.put("Media delle temperature percepite", mediaFeelsLike);
		 objFilter.put("Varianza delle temperature reali", varianzaTemp);
		 objFilter.put("Varianza delle temperature percepite", varianzaFeelsLike);
		 
		 return objFilter;
		 
	}
	
	/**
	 * 
	 * Metodo che permette di filtrare le statistiche salvate in locale nel file "APIForecastEveryHour.txt" relative 
	 * ad un'ora di un giorno scelti dall'utente.
	 * 
	 * @param date rappresenta il giorno scelto dall'utente
	 * @param time rappresenta l'ora scelta dall'utente
	 * @param cityName rappresenta il nome della città
	 * @param country rappresenta il Paese della città
	 * 
	 * @return <code>WeatherStats</code>
	 * 
	 * @throws WrongCityException eccezione riguardante l'inserimento di una città errata.
	 * @throws WrongFileException eccezione che restituisce un messaggio di errore quando il path del file non viene trovato.
	 * 
	 */
	
	public WeatherStats Filter1Hour(LocalDate date ,LocalTime time, String cityName, String country) throws WrongCityException, WrongFileException {
		 
		 City c = new City(cityName,country);
		 this.city = c;
		 c = JSONParsingStats();
		 weatStats = c.getVectorStats();
		 
		 int i = 0 , n = 0;
		 String k;
		 String j;
		 LocalDate d;
		 LocalTime t;
		 
		 for (i = 0; i < weatStats.size(); i++) {
			 
			 k = weatStats.get(i).getDataStats();
			 d = LocalDate.parse(k);
	
			 if (date.compareTo(d) == 0) {

				 for (n = 0; n < weatStats.size(); n++) {
			 
					 j = weatStats.get(n).getTimeStats();
					 k = weatStats.get(n).getDataStats();
					 d = LocalDate.parse(k);
					 t = LocalTime.parse(j);
					 
					 if ((date.compareTo(d) == 0) && (time.compareTo(t) == 0)) {
						 
						 return weatStats.get(n);
						 
					 }
				 }
			 }
		 }
		 
		 return null;
		 
	}
	
	/**
	 * 
	 * Metodo che permette di filtrare le statistiche salvate in locale nel file "APIForecastEveryHour.txt" relative ad una fascia oraria 
	 * dei giorni presenti nel file.
	 * 
	 * @param time rappresenta la fascia oraria scelta dall'utente
	 * @param cityName rappresenta il nome della città
	 * @param country rappresenta il Paese della città
	 * 
	 * @return <code>JSONObject</code>
	 * 
	 * @throws WrongCityException eccezione riguardante l'inserimento di una città errata.
	 * @throws WrongFileException eccezione che restituisce un messaggio di errore quando il path del file non viene trovato.
	 * 
	 */

	public JSONObject FilterPerHours(String time, String cityName, String country) throws WrongCityException, WrongFileException {
	 
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
		 
		 int n = 0;
		 String j;
		 LocalTime t;
	
		 for (n = 0; n < weatStats.size(); n++) {
				 
			j = weatStats.get(n).getTimeStats();
			t = LocalTime.parse(j);
				 
			if (t.isAfter(timeStart) && t.isBefore(timeEnd)) {
							 
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
			t = LocalTime.parse(j);
			
			if (t.isAfter(timeStart) && t.isBefore(timeEnd)) {
					
				varianzaTemp += (weatStats.get(n).getTemp() - mediaTemp)*(weatStats.get(n).getTemp() - mediaTemp);
				varianzaFeelsLike += (weatStats.get(n).getFeelsLike() - mediaFeelsLike)*(weatStats.get(n).getFeelsLike() - mediaFeelsLike);
				
			}
			
		 }
 
		 varianzaTemp /= n-1;
		 varianzaFeelsLike /= n-1;
		 
		 objFilter.put("Temperatura massima", tempMax);
		 objFilter.put("Temperatura minima", tempMin);
		 objFilter.put("Temperatura percepita massima", feelsLikeStatsMax);
		 objFilter.put("Temperatura percepita minima", feelsLikeStatsMin);
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
	
	/**
	 * 
	 * Metodo che permmette di filtrare le statistiche salvate in locale nel file "APIForecastEveryHour.txt" relative ad una settimana.
	 * 
	 * @param dateSt rappresenta la data d'inizio
	 * @param dateEn rappresenta la data di fine
	 * @param cityName rappresenta il nome della città
	 * @param country rappresenta il Paese della città
	 * 
	 * @return <code>JSONObject</code>
	 * 
	 * @throws WrongCityException eccezione riguardante l'inserimento di una città errata.
	 * @throws WrongFileException eccezione che restituisce un messaggio di errore quando il path del file non viene trovato.
	 * 
	 */
	
	public JSONObject FilterWeek (LocalDate dateSt, LocalDate dateEn, String cityName, String country) throws WrongCityException, WrongFileException {
		 
		 City c = new City(cityName,country);
		 this.city = c;
		 c = JSONParsingStats();
		 weatStats = c.getVectorStats();
		 
		 JSONObject objFilter = new JSONObject();
		 
		 int i = 0;
		 String j;
		 LocalDate d;
		 
		 for (i = 0; i < weatStats.size(); i++) {
			 
			 j = weatStats.get(i).getDataStats();
			 d = LocalDate.parse(j);
			 
			 if (d.isAfter(dateSt) && d.isBefore(dateEn)) {
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
			 d = LocalDate.parse(j);
			 
			 if (d.isAfter(dateSt) && d.isBefore(dateEn)) {
				 varianzaTemp += (weatStats.get(i).getTemp() - mediaTemp)*(weatStats.get(i).getTemp() - mediaTemp);
				 varianzaFeelsLike += (weatStats.get(i).getFeelsLike() - mediaFeelsLike)*(weatStats.get(i).getFeelsLike() - mediaFeelsLike);	 
			 }
			 
		 }
		 
		 
		 varianzaTemp /= i-1;
		 varianzaFeelsLike /= i-1;
		 
		 objFilter.put("Temperatura massima", tempMax);
		 objFilter.put("Temperatura minima", tempMin);
		 objFilter.put("Temperatura percepita massima", feelsLikeStatsMax);
		 objFilter.put("Temperatura percepita minima", feelsLikeStatsMin);
		 objFilter.put("Media delle temperature reali", mediaTemp);
		 objFilter.put("Media delle temperature percepite", mediaFeelsLike);
		 objFilter.put("Varianza delle temperature reali", varianzaTemp);
		 objFilter.put("Varianza delle temperature percepite", varianzaFeelsLike);
		 
		 return objFilter;
	}
}
