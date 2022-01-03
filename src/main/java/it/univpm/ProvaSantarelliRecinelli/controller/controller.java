package it.univpm.ProvaSantarelliRecinelli.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.ProvaSantarelliRecinelli.service.CityReader;

@RestController
public class controller {
	@RequestMapping({"/caricaarray", "/caricaarray/{city}"})
	public JSONObject caricaArray(@PathVariable(value="city_name" , required=false) String city) {
		CityReader c = new CityReader(city);
	}
}
