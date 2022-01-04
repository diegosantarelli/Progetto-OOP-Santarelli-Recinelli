package it.univpm.ProvaSantarelliRecinelli.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.model.City;
import it.univpm.ProvaSantarelliRecinelli.model.Weather;

public class CityReader{
	
	private static String name = "APIForecastANCONA.txt";
	private City city;
	private Vector <Weather> weat= new Vector <Weather>();

	public CityReader(String cityName, String country) {
		City c = new City(cityName,country);
		this.city = c;
		
		if (cityName == null || country == null) {
			cityName = "Ancona";
			country = "IT";
			City c1 = new City(cityName,country);
			this.city = c1;
			
		}
	}
	/**
	 * Questo metodo legge il JSON file e inserisce tutto in un JSONObject
	 * @return cityList ossia la lista delle città
	 */
	
	public JSONObject caricaOggetto() {
		JSONParser jsonParser = new JSONParser();
		JSONObject cityList = null;
		
		try (FileReader reader = new FileReader("/Users/simonerecinelli/Desktop/ProvaSantarelliRecinelli/src/main/resources/APIForecastANCONA")){
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
		double temp;
		double tempMin;
		double tempMax;
		double feelsLike;
		JSONObject objList = new JSONObject();
		JSONObject objMain;
		JSONObject objWind;
		for(int i=0; i<list.size(); i++) {
			objList = (JSONObject) list.get(i);
			String date = (String) objList.get("dt_txt");
			
			objMain = (JSONObject) objList.get("main");
			 temp = (double) objMain.get("temp");
			 tempMin = (double) objMain.get("temp_min");
			 tempMax = (double) objMain.get("temp_max");
			 feelsLike = (double) objMain.get("feels_like");
			
			objWind = (JSONObject ) objList.get("wind");
			double windSpeed = (double) objWind.get("speed");
			
			appoggio = new Weather(windSpeed, temp, tempMax, tempMin, feelsLike, date);
			this.weat.add(appoggio);
		}
		this.city.setVector(this.weat);	
		return this.city;
		}
	
	public String SaveEveryHour(City city) {
		String path = System.getProperty("user.dir") + "/" + city.getName() + "HourlyReport.txt";
		
		File file = new File(path);
		
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new Runnable() {
		    @Override
		    public void run() {
		    	
		    	//double wind_speed = GetWindSpeed();
		    	JSONArray wind_speed = new JSONArray();
		    	wind_speed = GetWindSpeed();
		    	
		    	JSONObject actualvisibility = new JSONObject();
		    	actualvisibility = visibility.getJSONObject(0);
		    	
		    	JSONObject actualwp = new JSONObject();
		    	actualwp = GetWindSpeed();

		    			try{
		    			    if(!file.exists()) {
		    			        file.createNewFile();
		    			    }

		    			    FileWriter fileWriter = new FileWriter(file, true);
		    				
		    				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		    			    bufferedWriter.write(actualwp.toString());
		    			    bufferedWriter.write("\n");
		    			    
		    			    bufferedWriter.close();
		    			    
		    			} catch(IOException e) {
		    			    System.out.println(e);
		    			}
		    	
		    }
		}, 0, 3, TimeUnit.HOURS);
		
		
		return "Il file è stato salvato in " + path;
		
	
}
}

