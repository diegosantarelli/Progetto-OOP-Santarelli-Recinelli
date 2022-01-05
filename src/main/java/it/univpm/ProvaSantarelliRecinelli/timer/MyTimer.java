package it.univpm.ProvaSantarelliRecinelli.timer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


import it.univpm.ProvaSantarelliRecinelli.exception.*;
import it.univpm.ProvaSantarelliRecinelli.service.APICall;

public class MyTimer{
	/**
	 * Questo metodo salva in un file di testo locale le informazioni attuali sul vento ogni ora
	 * @return Il metodo sovrascrive il file APIFOrecastEveryHour e avvisa l'utente con un messaggio
	 */
	
	public String WriteOnLocalFileEveryHour(String city, String country) throws WrongFileException {
		
		// PATH SIMONE /Users/simonerecinelli/Desktop/ProvaSantarelliRecinelli/src/main/resources/APIForecastEveryHour
		// PATH DIEGO C:\Users\diego\OneDrive\Desktop\ProvaSantarelliRecinelli\ProvaSantarelliRecinelli\src\main\resources\APIForecastEveryHour
		
		String path = "C:\\Users\\diego\\OneDrive\\Desktop\\ProvaSantarelliRecinelli\\ProvaSantarelliRecinelli\\src\\main\\resources\\APIForecastEveryHour";
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
		@Override
		public void run() {
			APICall api = new APICall(city,country);
			File file = new File(path);
			try	{
			    if(!file.exists()) {
			       file.createNewFile();
			    }

			FileWriter fileWriter = new FileWriter(file, true);
				
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(api.Call().toJSONString());
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
