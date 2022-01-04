package it.univpm.ProvaSantarelliRecinelli.timer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


import it.univpm.ProvaSantarelliRecinelli.exception.*;
import it.univpm.ProvaSantarelliRecinelli.service.APICall;

public class myTimer{
	/**
	 * Questo metodo salva in un file di testo locale le informazioni attuali sul vento ogni ora
	 */
	
	public void WriteOnLocalFile1Hours(String city, String country) throws WrongFileException {
		// PATH SIMONE /Users/simonerecinelli/Desktop/ProvaSantarelliRecinelli/src/main/resources/APIForecastEveryHour
		// PATH DIEGO ...
		
		String path = "/Users/simonerecinelli/Desktop/ProvaSantarelliRecinelli/src/main/resources/APIForecastEveryHour";
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
			    
				} 	catch(IOException e) {
			    System.out.println(e);
					}

		}
	};
	timer.scheduleAtFixedRate(task,0,3600000);
		
	}
}
