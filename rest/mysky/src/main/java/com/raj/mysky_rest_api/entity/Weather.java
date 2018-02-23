package com.raj.mysky_rest_api.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
	@NamedQuery(name="Weather.findByCity", query = "SELECT w FROM Weather w where w.city = :wCity"),
	@NamedQuery(name="Weather.findAll", query = "SELECT w FROM Weather w"),
	@NamedQuery(name="Weather.findLatest", query = "SELECT w FROM Weather w "
			+ "where w.timestamp = (SELECT max(timestamp) from Weather w1 where w1.city = :wCity)"),
	@NamedQuery(name="Weather.findHourly", query = "SELECT NEW Weather"
			+ "(w.city, AVG(w.humidity), AVG(w.pressure), AVG(w.temperature), AVG(w.wind.speed), AVG(w.wind.degree))"
			+ "FROM Weather w where w.city = :wCity GROUP BY HOUR(w.timestamp)"),
	@NamedQuery(name="Weather.findDaily", query = "SELECT NEW Weather"
			+ "(w.city, AVG(w.humidity), AVG(w.pressure), AVG(w.temperature), AVG(w.wind.speed), AVG(w.wind.degree))"
			+ "FROM Weather w where w.city = :wCity GROUP BY DAY(w.timestamp)")
})
public class Weather {
	
	@Id
	private String id;
	private String city;
	private String description;
	private String humidity;
	private String pressure;
	private String temperature;
	
	@OneToOne
	private Wind wind;
	
	private Timestamp timestamp;
	
	public Weather() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Weather(String city, Double humidity, Double pressure, Double temperature, Double speed, Double degree) {
		this.city = city;
		this.humidity = humidity.toString();
		this.pressure = pressure.toString();
		this.temperature = temperature.toString();
		Wind wind = new Wind();
		wind.setSpeed(speed.toString());
		wind.setDegree(degree.toString());
		this.wind = wind;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Weather [id=" + id + ", city=" + city + ", description=" + description + ", humidity=" + humidity
				+ ", pressure=" + pressure + ", temperature=" + temperature + ", wind=" + wind + ", timestamp="
				+ timestamp + "]";
	}

	
}
