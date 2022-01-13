package it.univpm.ProvaSantarelliRecinelli.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.service.CityReader;

class CityTest {
	
	private City city;
	private CityReader cityR;
	private Weather weat;
	private JSONObject weatObj;
	private JSONObject finalObj;
	private JSONArray weatArr;
	private Vector<Weather> weatVec;
	
	@BeforeEach
	void setUp() throws WrongCityException {
		
		this.city = new City("Ancona", "IT");
		this.cityR = new CityReader(city.getName(), city.getCountry());
		this.weat = new Weather(8.46, 7.55, 7.55, 6.76, 3.36, "2022-01-11", "12:00", "broken clouds", "Clouds");
		this.weatObj = new JSONObject();
		this.finalObj = new JSONObject();
		this.weatArr = new JSONArray();
		this.weatVec = new Vector<Weather>();
		
	}
	
	@AfterEach
	void tearDown() {
		
	}

	@Test
	void ConvertToJSONTest() throws WrongCityException {

		weatVec.add(weat);
		city.setVector(weatVec);
		
		
		finalObj.put("name", city.getName());
		finalObj.put("country", city.getCountry());
		weatObj.put("windSpeed", 8.46);
		weatObj.put("date", "2022-01-11");
		weatObj.put("time", "12:00");
		weatObj.put("temp", 7.55);
		weatObj.put("descr", "broken clouds");
		weatObj.put("main", "Clouds");
		weatObj.put("temp_max", 7.55);
		weatObj.put("temp_min", 6.76);
		weatObj.put("feels_like", 3.36);
		weatArr.put(weatObj);
		
		finalObj.put("vector:", weatArr);
		
		assertEquals("Ciao", finalObj.toString(), city.convertToJSON().toString());
	
		
	}

}
