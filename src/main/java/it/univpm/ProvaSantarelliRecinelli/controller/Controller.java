package it.univpm.ProvaSantarelliRecinelli.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.ProvaSantarelliRecinelli.exception.WrongCityException;
import it.univpm.ProvaSantarelliRecinelli.exception.WrongFileException;
import it.univpm.ProvaSantarelliRecinelli.model.City;
import it.univpm.ProvaSantarelliRecinelli.model.WeatherStats;
import it.univpm.ProvaSantarelliRecinelli.service.APICall;
import it.univpm.ProvaSantarelliRecinelli.service.CityReader;
import it.univpm.ProvaSantarelliRecinelli.stats.FilterStats;
import it.univpm.ProvaSantarelliRecinelli.timer.MyTimer;

@RestController
public class Controller {
	
	@RequestMapping({"/APICall", "/APICall/{city}/{country}"})
	public JSONObject Call(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country) {
		APICall ap = new APICall(city, country);
		return ap.Call();
	}
	
	@RequestMapping({"/JSONParsing", "/JSONParsing/{city}/{country}"})
	public City JSONParsing(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country) throws WrongCityException {
		CityReader city1 = new CityReader(city, country);
		return city1.JSONParsing();
	}
	
	@RequestMapping({"/MyTimer", "/MyTimer/{city}/{country}"})
	public String WriteOnLocalFile1Hour(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country) throws WrongFileException {
		MyTimer timer = new MyTimer();
		return timer.WriteOnLocalFileEveryHour(city, country);
	}
	
	
	@RequestMapping({"/JSONParsingStats", "/JSONParsingStats/{city}/{country}"})
	public City JSONParsingStats(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country) throws WrongCityException {
		FilterStats city1 = new FilterStats(city, country);
		return city1.JSONParsingStats();
	}
	
	@RequestMapping({"/FilterDay", "/FilterDay/{city}/{country}/{day}"})
	public JSONObject FilterDay(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country , @PathVariable (value="day" , required=false) String day) throws WrongCityException {
		LocalDate date = LocalDate.parse(day);
		FilterStats filter = new FilterStats(city, country);
		return filter.FilterDay(date,city,country);
	}
	
	@RequestMapping({"/Filter1Hour", "/Filter1Hour/{city}/{country}/{day}/{time}"})
	public WeatherStats Filter1Hour(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country , @PathVariable (value="day" , required=false) String day , @PathVariable (value="time" , required=false) String time) throws WrongCityException {
		LocalDate date = LocalDate.parse(day);
		LocalTime myTime = LocalTime.parse(time);
		FilterStats filter = new FilterStats(city, country);
		return filter.Filter1Hour(date, myTime,city,country);
	}
	
	@RequestMapping({"/FilterPerHours", "/FilterPerHours/{city}/{country}/{hours}"})
	public JSONObject FilterPerHours(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country , @PathVariable (value="hours" , required=false) String hours) throws WrongCityException {
		FilterStats objStats = new FilterStats(city, country);
		return objStats.FilterPerHours(hours, city, country);
	}
	
	@RequestMapping({"/Filter5Days", "/Filter5Days/{city}/{country}/{date1}/{date2}"})
	public JSONObject Filter5Days(@PathVariable(value="city" , required=false) String city, @PathVariable(value="country" , required=false) String country , @PathVariable (value="date1" , required=false) String date1, @PathVariable (value="date2" , required=false) String date2) throws WrongCityException {
		LocalDate startDate = LocalDate.parse(date1);
		LocalDate endDate = LocalDate.parse(date2);
		FilterStats objStats = new FilterStats(city, country);
		return objStats.Filter5Days(startDate, endDate, city, country);
	}
	
	@ExceptionHandler (WrongCityException.class)
	public static String WrongCity(WrongCityException e) {
		return e.getMex();
	}
}
