package com.sai.car.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sai.car.model.Car;
import com.sai.car.model.CarRequest;
import com.sai.car.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	CarService carService;

	@GetMapping("/{name}")
	public ResponseEntity<Car> getCarDetails(@PathVariable String name) throws Exception {
		Car car = carService.getCarDetails(name);
		return new ResponseEntity<Car>(car, HttpStatus.OK);
	}

	@PostMapping("/add/{name}/{type}")
	public ResponseEntity<Car> addCarDetails(@PathVariable String name, @PathVariable String type) {
		Car car = carService.addCarDetails(name, type);
		return new ResponseEntity<Car>(car, HttpStatus.OK);
	}

	@PutMapping("/update/{name}/{type}")
	public ResponseEntity<String> updateCarDetails(@PathVariable String name, @PathVariable String type) {
		int car = carService.updateCarDetails(name, type);
		JSONObject resp = new JSONObject();
		resp.put("count", car);
		return new ResponseEntity<String>(resp.toString(), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{name}")
	public ResponseEntity<String> deleteCar(@PathVariable String name) {
		int car = carService.deleteCarDetails(name);
		JSONObject json = new JSONObject();
		json.put("count", car);
		return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
	}

	@PostMapping("/newcars")
	public ResponseEntity<List<Car>> addMultipleCars(@RequestBody List<CarRequest> listOfCars) {
		List<Car> cars = null;
		cars = carService.addCars(listOfCars);
		return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
	}

}
