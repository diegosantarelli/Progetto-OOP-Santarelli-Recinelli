package it.univpm.ProvaSantarelliRecinelli.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
//import java.time.ZonedDateTime;
//import java.util.Vector;

import org.json.simple.JSONArray;
//import org.json.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import it.univpm.ProvaSantarelliRecinelli.model.City;
//import org.json.JSONObject;

//import it.santarellirecinelli.model.*;
//import it.santarellirecinelli.model.Date;
//import it.santarellirecinelli.exception.WrongDateException;

/**
 * <p>
 * La classe permette di gestire le chiamate API e implementa l'interfaccia <b>APICallService</b>
 * </p>
 * @author simonerecinelli
 *
 */


public class APICall implements APICallService{
	private static String appid = "e53a126fee4e06f8dedcc4ecfa92d055";
	private String url;

	/**
	 * Costruttore della classe
	 * 
	 * @param city indica la città di interesse
	 * 
	 * @throws WrongCityException città inserita sbagliata
	 */
	public APICall (City city) {
		this.url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city.getName() + "," + city.getCountry() + "&appid=" + appid;
	}
	 public APICall () {
		 
	 };


	public JSONArray Call() {   

		String api = this.url;
		JSONArray ja = null;
		String data_filter = "";
		String line = "";

		try {
			
			URLConnection openConnection = new URL(api).openConnection();
			InputStream in = openConnection.getInputStream();

			InputStreamReader input = new InputStreamReader( in );
			BufferedReader buf = new BufferedReader( input );

			while ( ( line = buf.readLine() ) != null ) {
				data_filter += line;
			}
			
			in.close();
			ja = (JSONArray) JSONValue.parseWithException(data_filter);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		catch (IOException e ) {
			e.printStackTrace();
		}
		
		return ja;
	}

	
}
