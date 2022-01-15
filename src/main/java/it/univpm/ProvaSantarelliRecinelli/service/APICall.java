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
 * 
 * Classe che gestisce le chiamate API prendendo in input dall'utente il nome di una città e il relativo Paese.
 * 
 * @author SimoneRecinelli
 * @author DiegoSantarelli
 * 
 */

public class APICall{
	
	private static String appid = "e53a126fee4e06f8dedcc4ecfa92d055";
	private String url;

	/**
	 * 
	 * Costruttore della classe.
	 * 
	 * @param city rappresenta la città
	 * @param country rappresenta il Paese
	 * 
	 */
	
	public APICall (String city, String country) {
		
		this.url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "," + country + "&units=metric&appid=" + appid;
		if (city == null || country == null) {
			this.url = "http://api.openweathermap.org/data/2.5/forecast?q=Ancona,IT&units=metric&appid=" + appid;
		}
	}
	 
	/**
	 * 
	 * Metodo che effettua la chiamata all'API Forecast tramite l'URL inserito nel costruttore, la città e il Paese inseriti dall'utente.
	 * Esso ritorna un JSONObject contenente tutte le informazioni derivanti dall'API Forecast.
	 * 
	 * @return <code>JSONObject</code> 
	 * 
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
