package it.univpm.ProvaSantarelliRecinelli.timer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


import it.univpm.ProvaSantarelliRecinelli.exception.*;
import it.univpm.ProvaSantarelliRecinelli.service.APICall;
import it.univpm.ProvaSantarelliRecinelli.service.CityReader;

public class MyTimer{
	/**
	 * Questo metodo salva in un file di testo locale le informazioni attuali sul vento ogni ora
	 * @return Il metodo sovrascrive il file APIFOrecastEveryHour e avvisa l'utente con un messaggio
	 * @throws WrongCityException 
	 */
	
	// PATH SIMONE /Users/simonerecinelli/Desktop/ProvaSantarelliRecinelli/src/main/resources/APIForecastEveryHour
	// PATH DIEGO C:\Users\diego\OneDrive\Desktop\ProvaSantarelliRecinelli\ProvaSantarelliRecinelli\src\main\resources\APIForecastEveryHour
	
	public String WriteOnLocalFileEveryHour(String city, String country) throws WrongFileException, WrongCityException {
		
		if (city == null || country == null) {
			city = "Ancona";
			country = "IT";
		}  else if (!city.equals("Ancona") || !country.equals("IT")) throw new WrongCityException();
		
		final String city2 = city;
		final String country2 = country;

		String path = "C:\\Users\\diego\\OneDrive\\Desktop\\ProvaSantarelliRecinelli\\ProvaSantarelliRecinelli\\src\\main\\resources\\APIForecastEveryHour";
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
		
		@Override
		public void run() {
			
			CityReader c = null;
			try {
				c = new CityReader(city2, country2);
			} catch (WrongCityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//APICall api = new APICall(city2,country2);
			File file = new File(path);
			try	{
			    if(!file.exists()) {
			       file.createNewFile();
			    }

			FileWriter fileWriter = new FileWriter(file, true);
				
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(c.JSONParsing().toString());
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
