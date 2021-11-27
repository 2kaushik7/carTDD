package com.sai.car.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false, name = "transmissiontype")
	private String TransmissionType;

	@Column(nullable = false, name = "enginetype")
	private String engineType;

	@Column(nullable = false, name = "airbags")
	private boolean airBags;

	public Car() {

	}

	public Car(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTransmissionType() {
		return TransmissionType;
	}

	public void setTransmissionType(String transmissionType) {
		TransmissionType = transmissionType;
	}

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	public boolean isAirBags() {
		return airBags;
	}

	public void setAirBags(boolean airBags) {
		this.airBags = airBags;
	}

}
