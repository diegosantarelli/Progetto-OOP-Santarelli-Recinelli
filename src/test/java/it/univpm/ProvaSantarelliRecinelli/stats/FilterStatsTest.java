package it.univpm.ProvaSantarelliRecinelli.stats;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.model.City;
import it.univpm.ProvaSantarelliRecinelli.model.WeatherStats;

class FilterStatsTest {
	
	private FilterStats fs;
	private City city;
	private JSONObject jo;
	private String date, hour, startDate, endDate;
	private String timePerHours;
	private LocalDate day, startDay, endDay;
	//private LocalTime time;
	//private Vector<WeatherStats> weat;
	//private double temp, tempMax, tempMin, feelsLike;
	
	
	@BeforeEach
	void setUp() throws WrongCityException {
		
		this.city = new City("Ancona", "IT");
		this.fs = new FilterStats(city.getName(), city.getCountry());
		this.jo = new JSONObject();
		this.date = "2022-01-11";
		this.day = LocalDate.parse(date);
		this.hour = "21:00:00";
		//this.time = LocalTime.parse(hour);
		this.startDate = "2022-01-10";
		this.startDay = LocalDate.parse(startDate);
		this.endDate = "2022-01-17";
		this.endDay = LocalDate.parse(endDate);
		this.timePerHours = "15:00:00-21:00:00";
		//this.weat = new Vector<WeatherStats>();
		
	}
	
	@AfterEach
	void tearDown() {
		
	}

	@Test
	@DisplayName("Filtraggio avvenuto correttamente")
	void FilterDayTest() throws WrongCityException {
		
		jo.put("Temperatura massima", 7.55);
		jo.put("Temperatura minima", 6.76);
		jo.put("Varianza delle temperature reali", 2.845071314102564);
		jo.put("Varianza delle temperature percepite", 0.38986762179487183);
		jo.put("Temperatura percepita minima", -1.39);
		jo.put("Media delle temperature reali", 0.56125);
		jo.put("Media delle temperature percepite", 0.09325000000000001);
		jo.put("Temperatura percepita massima", 3.36);
		
		assertEquals(jo, fs.FilterDay(day, city.getName(), city.getCountry()));
		
	}
	
	/*
	@Test
	void Filter1Hour() throws WrongCityException {
		
		jo.put("temp", 3.57);
		jo.put("tempMax", 3.57);
		jo.put("tempMin", 3.57);
		jo.put("feelsLike", -1.39);
		jo.put("dataStats", "2022-01-11");
		jo.put("timeStats", "21:00");
		
		weat.add(jo);
		
		assertEquals(weat, fs.Filter1Hour(day, time, city.getName(), city.getCountry()));
		
	}
	*/
	
	@Test
	@DisplayName("Filtraggio avvenuto correttamente")
	void FilterPerHours() throws WrongCityException {
		
		jo.put("Temperatura massima", 6.74);
		jo.put("Varianza delle temperature reali", 2.446771033653846);
		jo.put("Varianza delle temperature percepite", 1.3691614182692307);
		jo.put("Temperatura percepita minima", -0.11);
		jo.put("Media delle temperature reali", 0.60625);
		jo.put("Media delle temperature percepite", 0.28825);
		jo.put("Temperatura minima", 6.74);
		jo.put("Temperatura percepita massima", 5.76);
		
		assertEquals(jo, fs.FilterPerHours(timePerHours, city.getName(), city.getCountry()));
		
	}
	
	@Test
	@DisplayName("Filtraggio avvenuto correttamente")
	void FilterPerWeek() throws WrongCityException {
		
		jo.put("Temperatura massima", 9.88);
		jo.put("Varianza delle temperature reali", 3.982336858974359);
		jo.put("Varianza delle temperature percepite", 10.706428141025643);
		jo.put("Temperatura percepita minima", -3.08);
		jo.put("Media delle temperature reali", 5.0962499999999995);
		jo.put("Media delle temperature percepite", 2.72775);
		jo.put("Temperatura minima", 9.88);
		jo.put("Temperatura percepita massima", 9.54);
		
		assertEquals(jo, fs.FilterWeek(startDay, endDay, city.getName(), city.getCountry()));
		
	}
}
