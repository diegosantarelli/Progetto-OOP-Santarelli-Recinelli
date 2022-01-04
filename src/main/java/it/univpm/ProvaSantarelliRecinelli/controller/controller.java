package it.univpm.ProvaSantarelliRecinelli.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.model.City;
import it.univpm.ProvaSantarelliRecinelli.service.APICall;
import it.univpm.ProvaSantarelliRecinelli.service.CityReader;

@RestController
public class controller {
	
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
	
	
	@RequestMapping({"/caricaarray", "/caricaarray/{city}"})
	public JSONObject caricaArray(@PathVariable(value="city_name" , required=false) String city) {
		CityReader c = new CityReader(city);
	}
}
