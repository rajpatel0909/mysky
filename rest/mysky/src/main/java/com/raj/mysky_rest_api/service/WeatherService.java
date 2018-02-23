package com.raj.mysky_rest_api.service;

import java.util.List;
import java.util.Set;

import com.raj.mysky_rest_api.entity.Weather;

public interface WeatherService {
	
	public Weather create(Weather weather);
	
	public Set<String> findAllCities();
	
	public Weather findLatestWeather(String city);
	
	public String findLatestProperty(String city, String property);
	
	public List<Weather> findHourlyWeather(String city);
	
	public List<Weather> findDailyWeather(String city);
}
