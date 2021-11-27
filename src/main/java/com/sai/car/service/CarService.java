package com.sai.car.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sai.car.exceptions.CarNotFoundException;
import com.sai.car.model.Car;
import com.sai.car.model.CarRequest;
import com.sai.car.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	CarRepository carRepository;

	public Car getCarDetails(String name) {
		Optional<Car> carOption = carRepository.findByName(name);
		if (carOption.isPresent() && carOption.get().getName() != null)
			return carOption.get();
		else {
			throw new CarNotFoundException();
		}
	}

	public Car addCarDetails(String name, String type) {
		Car car = carRepository.save(new Car(name, type));
		if (car.getName() != null)
			return car;
		else
			throw new CarNotAddedException();
	}

	public int updateCarDetails(String name, String type) {
		int car = carRepository.updateCarDetails(name, type);
		if (car > 0)
			return car;
		else
			throw new CarNotFoundException();
	}

	public int deleteCarDetails(String name) {
		Optional<Integer> count = carRepository.deleteCarDetails(name);
		if (count.isPresent())
			return count.get();
		else
			throw new CarNotFoundException();
	}

	public List<Car> addCars(List<CarRequest> carsRequest) {
		List<Car> cars = new ArrayList<Car>();

		carsRequest.stream().forEach(c -> {
			Car car = new Car();
			c.getSpecifications().forEach((k, v) -> {
				if (k.toLowerCase().equals("transmissiontype"))
					car.setTransmissionType(v);
				else if (k.toLowerCase().equals("enginetype"))
					car.setEngineType(v);

			});
			car.setName(c.getName());
			car.setType(c.getType());
			car.setAirBags(c.isAirBags());
			cars.add(car);
		});

		Optional<List<Car>> carsOption = carRepository.addMultipleCarsDetails(cars);
		if (carsOption.isPresent() && carsOption.get().size() > 0)
			return carsOption.get();
		else
			throw new CarNotAddedException();
	}

}
