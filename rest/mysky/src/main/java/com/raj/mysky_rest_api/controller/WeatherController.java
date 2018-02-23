package com.raj.mysky_rest_api.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.raj.mysky_rest_api.constants.URI;
import com.raj.mysky_rest_api.entity.Weather;
import com.raj.mysky_rest_api.service.WeatherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = URI.WEATHER)
@Api(tags = "weather")
public class WeatherController {
	
	private WeatherService service;
	
	public WeatherController(WeatherService service) {
		this.service = service;
	}
	
	@ApiOperation(value = "create weather", notes = "create weather entry in the data")
	@RequestMapping(method = RequestMethod.POST)
	public Weather create(@RequestBody Weather weather) {
		return service.create(weather);
	}
	
	@ApiOperation(value = "Find All Cities", notes = "Returns a list of cities in the data")
	@RequestMapping(method = RequestMethod.GET)
	public Set<String> findAllCities(){
		return service.findAllCities();
	}
	
	@ApiOperation(value = "Find latest weather", notes = "Returns latest weather of given city")
	@RequestMapping(method = RequestMethod.GET, value = URI.CITY)
	public Weather findLatestWeather(@PathVariable("city") String city) {
		System.out.println("Controller");
		return service.findLatestWeather(city);
	}
	
	@ApiOperation(value = "Find latest weather property", notes = "Returns latest weather property of given city")
	@RequestMapping(method = RequestMethod.GET, value = URI.CITYWEATHER)
	public String findLatestProperty(@PathVariable("city") String city, @PathVariable("property") String property) {
		return service.findLatestProperty(city, property);
	}
	
	@ApiOperation(value = "Find Average weather", notes = "Returns Hourly Average weather at /avg/hourly/{city} and Daily Average weather at /avg/hourly/{city}")
	@RequestMapping(method = RequestMethod.GET, value = URI.AVGCITY)
	public List<Weather> findAverageWeather(@PathVariable("type") String type, @PathVariable("city") String city) {
		if(type.equals("hourly")) {
			return service.findHourlyWeather(city);
		}else if(type.equals("daily")) {
			return service.findDailyWeather(city);
		}else {
			return null;
		}
	}
}
