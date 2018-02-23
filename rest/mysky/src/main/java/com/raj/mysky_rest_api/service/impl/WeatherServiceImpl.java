package com.raj.mysky_rest_api.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raj.mysky_rest_api.entity.Weather;
import com.raj.mysky_rest_api.exception.NotFoundException;
import com.raj.mysky_rest_api.repository.WeatherRepository;
import com.raj.mysky_rest_api.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService{

	private WeatherRepository repository;
	
	public WeatherServiceImpl(WeatherRepository repository) {
		this.repository = repository;
	}
	
	@Override
	@Transactional
	public Weather create(Weather weather) {
		return repository.create(weather);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<String> findAllCities() {
		return repository.findAllCities();
	}

	@Override
	@Transactional(readOnly = true)
	public Weather findLatestWeather(String city) {
		Weather existing = repository.findCity(city);
		if(existing == null) {
			throw new NotFoundException("City " + city + " does not exists"); 
		}
		return repository.findLatestWeather(city);
	}

	@Override
	@Transactional(readOnly = true)
	public String findLatestProperty(String city, String property) {
		Weather existingProperty = repository.findCity(city);
		if(existingProperty == null) {
			throw new NotFoundException("Either city " + city + " does not exists or it does not have property " + property);
		}
		return repository.findLatestProperty(city, property);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Weather> findHourlyWeather(String city) {
		Weather existingProperty = repository.findCity(city);
		if(existingProperty == null) {
			throw new NotFoundException("City " + city + " does not exists");
		}
		return repository.findHourlyWeather(city);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Weather> findDailyWeather(String city) {
		Weather existingProperty = repository.findCity(city);
		if(existingProperty == null) {
			throw new NotFoundException("City " + city + " does not exists");
		}
		return repository.findDailyWeather(city);
	}

}
