package com.sai.car.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sai.car.exceptions.CarNotFoundException;
import com.sai.car.model.Car;
import com.sai.car.model.CarRequest;
import com.sai.car.model.User;
import com.sai.car.model.UsrDtlsRq;
import com.sai.car.repository.CarRepository;
import com.sai.car.repository.UserRepository;

@Service
public class CarService {

	@Autowired
	CarRepository carRepository;

	@Autowired
	UserRepository userRepository;

	public Car getCarDetails(String name) {
		Optional<Car> carOption = carRepository.findByName(name);
		if (carOption.isPresent() && carOption.get().getName() != null)
			return carOption.get();
		else {
			throw new CarNotFoundException();
		}
	}

	public Car addCarDetails(String name, String type) {
		Car car = new Car(name, type);
		car.setTransmissionType("Manual");
		car.setEngineType("v8");
		car.setAirBags(false);

		car = carRepository.save(car);
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

		List<Car> carList = carRepository.saveAll(cars);
		Optional<List<Car>> carsOption = Optional.of(carList);
		if (carsOption.isPresent() && carsOption.get().size() > 0)
			return carsOption.get();
		else
			throw new CarNotAddedException();
	}

	public Object userAuth(UsrDtlsRq usrDtlrq) {

		Optional<User> user = userRepository.findByName(usrDtlrq.getUser());
		return user.isPresent() && user.get().getName().equals(usrDtlrq.getUser())
				&& user.get().getPassword().equals(usrDtlrq.getPassword());
	}

}
