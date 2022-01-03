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
	private String city_name;
	private String city_country;
	private double speed;
	
	private double temp;
	private double temp_max;
	private double temp_min;
	private double feels_like;
	
	public CityReader(String city) {
		this.city_name = city;
		this.city_country = city;
	}
	/**
	 * Questo metodo legge il JSON file e inserisce tutto in un JSONArray
	 * @return cityList ossia la lista delle città
	 */
	
	public JSONObject caricaArray() {
		JSONParser jsonParser = new JSONParser();
		JSONObject cityList = null;
		
		try (FileReader reader = new FileReader(name)){
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
	
	public void GetWindSpeedCity (JSONArray j) throws WrongCityException {
		// Qui prendiamo l'oggetto città dalla citylist
		
		for(int i=0; i < j.size(); i++) {
			JSONObject cityObject = (JSONObject) j.get(i);
			if (cityObject.get("name").equals(this.city_name)) {
				if (cityObject.get("country").equals(this.city_country)) {
					//mi prendo il vento della city
					JSONObject WindObject = (JSONObject) cityObject.get("wind");
					//mi prendo la velocità del vento della city
					this.speed = (double) WindObject.get("speed");
				} else throw new WrongCityException();
			} else throw new WrongCityException();
		}
	}
	
	
	public double GetWindSpeed() throws WrongCityException {
		JSONArray ja = new JSONArray();
		ja = caricaArray();
		GetWindSpeedCity(ja);
		return this.speed;
	}
	
	public City getCityWeatherRistrictfromApi(JSONArray ja) throws WrongCityException {
		
		City city = new City(city_name, city_country);
		Vector<Weather> vector = new Vector<Weather>();
		Weather weather = new Weather();
			
			for(int i=0; i<ja.size(); i++) {
				JSONObject cityObject = (JSONObject) ja.get(i);
				if (cityObject.get("name").equals(this.city_name)) {
					if (cityObject.get("country").equals(this.city_country)) {
						JSONObject listObject = (JSONObject) cityObject.get("list");
						
						JSONObject MainObject = (JSONObject) listObject.get("main");
						
						this.temp = (double)MainObject.get("temp_max");
						this.temp_max = (double) MainObject.get("temp_max");
						this.temp_min = (double) MainObject.get("temp_min");
						this.feels_like = (double) MainObject.get("feels_like");
						weather.setFeels_like(this.feels_like);
						weather.setTemp(this.temp);
						weather.setTemp_max(this.temp_max);
						weather.setTemp_min(this.temp_min);
						vector.add(weather);
					} else throw new WrongCityException();
				} else throw new WrongCityException();
			}
			city.setVector(vector);
			return city;
		}
	
	
	public double GetTempMax() throws WrongCityException {
		JSONArray ja = new JSONArray();
		ja = caricaArray();
		getCityWeatherRistrictfromApi(ja);
		return this.temp_max;
	}
	
	
	public double GetTempMin() throws WrongCityException {
		JSONArray ja = new JSONArray();
		ja = caricaArray();
		getCityWeatherRistrictfromApi(ja);
		return this.temp_min;
	}
	

	public double GetTemp() throws WrongCityException {
		JSONArray ja = new JSONArray();
		ja = caricaArray();
		getCityWeatherRistrictfromApi(ja);
		return this.temp;
	}
	
	
	public double GetFeelsLike() throws WrongCityException {
		JSONArray ja = new JSONArray();
		ja = caricaArray();
		getCityWeatherRistrictfromApi(ja);
		return this.feels_like;
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

