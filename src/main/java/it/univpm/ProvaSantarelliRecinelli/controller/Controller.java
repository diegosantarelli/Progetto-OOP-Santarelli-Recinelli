package it.univpm.ProvaSantarelliRecinelli.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.exception.WrongFileException;
import it.univpm.ProvaSantarelliRecinelli.model.City;
import it.univpm.ProvaSantarelliRecinelli.service.APICall;
import it.univpm.ProvaSantarelliRecinelli.service.CityReader;
import it.univpm.ProvaSantarelliRecinelli.stats.Stats;
import it.univpm.ProvaSantarelliRecinelli.timer.MyTimer;

@RestController
public class Controller {
	
	@RequestMapping({"/APICall", "/APICall/{city}?{country}"})
	public JSONObject Call(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country) {
		APICall ap = new APICall(city, country);
		return ap.Call();
	}
	
	@RequestMapping({"/JSONParsing", "/JSONParsing/{city}?{country}"})
	public City JSONParsing(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country) throws WrongCityException {
		CityReader city1 = new CityReader(city, country);
		return city1.JSONParsing();
	}
	
	@RequestMapping({"/MyTimer", "/MyTimer/{city}?{country}"})
	public String WriteOnLocalFile1Hour(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country) throws WrongFileException {
		MyTimer timer = new MyTimer();
		return timer.WriteOnLocalFileEveryHour(city, country);
	}
	
	@RequestMapping({"/TempMin", "/TempMin/{city}?{country}"})
	public double TempMin(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country) throws WrongCityException {
		Stats TempMinima = new Stats();
		return TempMinima.TempMin(city,country);
	}
	
	@RequestMapping({"/TempMax", "/TempMax/{city}?{country}"})
	public double TempMax(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country) throws WrongCityException {
		Stats TempMassima = new Stats();
		return TempMassima.TempMax(city,country);
	}
	
	@RequestMapping({"/MediaStats", "/MediaStats/{city}?{country}"})
	public double Media(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country) throws WrongCityException {
		Stats MediaTempStats = new Stats();
		return MediaTempStats.MediaTemp(city,country);
	}
	
	/*
	@RequestMapping({"/caricaarray", "/caricaarray/{city}"})
	public JSONObject caricaArray(@PathVariable(value="city_name" , required=false) String city) {
		CityReader c = new CityReader(city);
	}
	*/
}
