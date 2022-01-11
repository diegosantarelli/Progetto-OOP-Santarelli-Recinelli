package it.univpm.ProvaSantarelliRecinelli.timer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


import it.univpm.ProvaSantarelliRecinelli.exception.*;
import it.univpm.ProvaSantarelliRecinelli.model.City;
import it.univpm.ProvaSantarelliRecinelli.service.APICall;
import it.univpm.ProvaSantarelliRecinelli.service.CityReader;

/**
 * Questa classe che gestisce la sovrascrizione di un file locale.
 * @author SimoneRecinelli
 * @author DiegoSantarelli
 */

public class MyTimer{
	
	/**
	 * Metodo che sovrascrive ogni ora un file locale con le informazioni che ci interessano per fare le statistiche 
	 * su una città e Paese inseriti dall'utente come parametro. Nel caso di mancato inserimento di quest'ultime,
	 * il metodo verrà effettuato con dei parametri di default (Ancona, IT).
	 * 
	 * @param city rappresenta la città
	 * @param country rappresenta il paese
	 * @return <code>String</code>
	 * @throws WrongCityException eccezione dovuta all'inserimento di una città o Paese errati.
	 * @throws WrongFileException eccezione dovuta all'inserimento di un path del file errato.
	 */
	public String WriteOnLocalFileEveryHour(String city, String country) throws WrongFileException, WrongCityException {
		
		if (city == null || country == null) {
			city = "Ancona";
			country = "IT";
		}  else if (!city.equals("Ancona") || !country.equals("IT")) throw new WrongCityException();
		
		final String city2 = city;
		final String country2 = country;
		
		// PATH SIMONE /Users/simonerecinelli/Desktop/ProvaSantarelliRecinelli/src/main/resources/APIForecastEveryHour
		// PATH DIEGO C:\Users\diego\OneDrive\Desktop\ProvaSantarelliRecinelli\ProvaSantarelliRecinelli\src\main\resources\APIForecastEveryHour
		
		String path = "C:\\Users\\diego\\OneDrive\\Desktop\\ProvaSantarelliRecinelli\\ProvaSantarelliRecinelli\\src\\main\\resources\\APIForecastEveryHour";
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
		
		@Override
		public void run() {
			
			City c = new City(city2, country2);

			File file = new File(path);
			try	{
			    if(!file.exists()) {
			       file.createNewFile();
			    }

			FileWriter fileWriter = new FileWriter(file, true);
				
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(c.convertToJSON().toString());
			bufferedWriter.write("\n");
			    
			bufferedWriter.close();
			    
			} catch(IOException e) {
			  System.out.println(e);
			}
		}
		};
		timer.scheduleAtFixedRate(task,0,3600000);
		
		return "Il file APIForecastEveryHour è stato salvato in " + path;
	}
}
