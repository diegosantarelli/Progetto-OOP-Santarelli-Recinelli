package it.univpm.ProvaSantarelliRecinelli.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;


/**
 * <p>
 * La classe permette di gestire le chiamate API
 * </p>
 * @author simonerecinelli
 * @author diegosantarelli
 *
 */


public class APICall{
	private static String appid = "e53a126fee4e06f8dedcc4ecfa92d055";
	private String url;

	/**
	 * Costruttore della classe
	 * 
	 * @param city indica la città di interesse
	 * 
	 * @throws WrongCityException città inserita sbagliata
	 */
	public APICall (String city, String country) {
		
		
		this.url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "," + country + "&units=metric&appid=" + appid;
		if (city == null || country == null) {
			this.url = "http://api.openweathermap.org/data/2.5/forecast?q=Ancona,IT&units=metric&appid=" + appid;
		}
	}
	 
	 /**
	  * Il metodo effettua la chiamata all'API
	  * @return java
	  */

	public JSONObject Call() {   

		String api = this.url;
		JSONObject ja = null;
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
			ja = (JSONObject) JSONValue.parseWithException(data_filter);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		catch (IOException e ) {
			e.printStackTrace();
		}
		
		return ja;
	}

	
}
