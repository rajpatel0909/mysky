package com.raj.mysky_rest_api.repository.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.raj.mysky_rest_api.entity.Weather;
import com.raj.mysky_rest_api.repository.WeatherRepository;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Weather create(Weather weather) {
		em.persist(weather.getWind());
		em.persist(weather);
		return weather;
	}

	@Override
	public Set<String> findAllCities() {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findAll", Weather.class);
		Set<String> cities = new HashSet<String>();
		for(Weather weather: query.getResultList()) {
			cities.add(weather.getCity());
		}
		return cities;
	}

	@Override
	public Weather findLatestWeather(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findLatest", Weather.class);
		query.setParameter("wCity", city);
		List<Weather> weathers = query.getResultList();
		if(weathers.isEmpty()) {
			return null;
		}else {
			return weathers.get(0);
		}
	}

	@Override
	public String findLatestProperty(String city, String property) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findLatest", Weather.class);
		query.setParameter("wCity", city);
		List<Weather> weathers = query.getResultList();
		if(weathers.isEmpty()) {
			return null;
		}else {
			return findProperty(weathers.get(0), property);
		}
	}

	@Override
	public List<Weather> findHourlyWeather(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findHourly", Weather.class);
		query.setParameter("wCity", city);
		List<Weather> weathers = query.getResultList();
		if(weathers.isEmpty()) {
			return null;
		}else {
			return weathers;
		}
	}

	@Override
	public List<Weather> findDailyWeather(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findDaily", Weather.class);
		query.setParameter("wCity", city);
		List<Weather> weathers = query.getResultList();
		if(weathers.isEmpty()) {
			return null;
		}else {
			return weathers;
		}
	}

	@Override
	public Weather findCity(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findByCity", Weather.class);
		query.setParameter("wCity", city);
		List<Weather> weathers = query.getResultList();
		if(weathers.isEmpty()) {
			return null;
		}else {
			return weathers.get(0);
		}
	}
	
	public String findProperty(Weather weather, String property) {
		switch (property) {
		case "humidity":
			return weather.getHumidity();
		case "pressure":
			return weather.getPressure();
		case "temperature":
			return weather.getTemperature();
		case "wind":
			return weather.getWind().toString();
		default:
			break;
		}
		return null;
	}

}
