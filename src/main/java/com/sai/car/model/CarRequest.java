package com.sai.car.model;

import java.util.Map;

public class CarRequest {

	private String name;

	private String type;

	private long manufacturedYear;

	private boolean airBags;

	private Map<String, String> specifications;

	public CarRequest(String name, String type) {
		super();
		this.name = name;
		this.type = type;
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

	public long getManufacturedYear() {
		return manufacturedYear;
	}

	public void setManufacturedYear(long manufacturedYear) {
		this.manufacturedYear = manufacturedYear;
	}

	public boolean isAirBags() {
		return airBags;
	}

	public void setAirBags(boolean airBags) {
		this.airBags = airBags;
	}

	public Map<String, String> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(Map<String, String> specifications) {
		this.specifications = specifications;
	}

}
