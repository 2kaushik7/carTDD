package com.sai.car.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sai.car.exceptions.CarNotFoundException;
import com.sai.car.model.Car;
import com.sai.car.model.CarRequest;
import com.sai.car.repository.CarRepository;

@ExtendWith(MockitoExtension.class)
public class TestCarService {

	@Mock
	CarRepository carRepository;

	@InjectMocks
	CarService carService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCarservice() {
		Mockito.when(carRepository.findByName(anyString())).thenReturn(Optional.of(new Car("chr", "hb")));
		Car car = carService.getCarDetails("chr");
		assertEquals("chr", car.getName());
		assertEquals("hb", car.getType());
	}

	@Test
	public void testCarService_CarNotFoundException() {
		Mockito.when(carRepository.findByName(anyString())).thenReturn(Optional.of(new Car(null, null)));
		assertThrows(CarNotFoundException.class, () -> carService.getCarDetails("chr"));

	}

	@Test
	public void testCarService_addCarDetails() {
		Mockito.when(carRepository.save(Mockito.any(Car.class))).thenReturn(new Car("juke", "diesel"));
		Car car = carService.addCarDetails("juke", "diesel");
		assertEquals("juke", car.getName());
		assertEquals("diesel", car.getType());
	}

	@Test
	public void testCarService_addCarDetails_notAdded() {
		Mockito.when(carRepository.save(Mockito.any(Car.class))).thenReturn(new Car(null, null));
		assertThrows(CarNotAddedException.class, () -> carService.addCarDetails("juke", "diesel"));
	}

	@Test
	public void testCarService_updateCarDetails() {
		Mockito.when(carRepository.updateCarDetails(anyString(), anyString())).thenReturn(1);
		assertEquals(1, carService.updateCarDetails("juke", "hyb"));
	}

	@Test
	public void testCarService_updateCarDetails_CarNotFoundException() {
		assertThrows(CarNotFoundException.class, () -> carService.updateCarDetails("audi", "hyb"));
	}

	@Test
	public void testCarService_deleteCarDetails() {
		Mockito.when(carRepository.deleteCarDetails(anyString())).thenReturn(Optional.of(1));
		assertEquals(1, carService.deleteCarDetails("Tesla"));
	}

	@Test
	public void testCarService_deleteCarDetails_CarNotFoundException() {
		assertThrows(CarNotFoundException.class, () -> carService.deleteCarDetails("jag"));
	}

	@Test
	public void testCarService_addMultipleCarDetails() {
		List<CarRequest> carsRequest = new ArrayList<CarRequest>();
		List<Car> savedcarDetails = new ArrayList<Car>();
		savedcarDetails.add(new Car("juke", "hyb"));
		Optional<List<Car>> carOption = Optional.of(savedcarDetails);
		Mockito.when(carRepository.addMultipleCarsDetails(anyList())).thenReturn(carOption);

		List<Car> cars = carService.addCars(carsRequest);
		assertEquals(false, cars.isEmpty());
		assertEquals("juke", cars.get(0).getName());
	}

	@Test
	public void testCarService_addCarDetails_carsNotAddedException() {
		List<CarRequest> carsRequest = new ArrayList<CarRequest>();
		List<Car> cars = new ArrayList<Car>();
		Mockito.when(carRepository.addMultipleCarsDetails(anyList())).thenReturn(Optional.of(cars));
		assertThrows(CarNotAddedException.class, () -> carService.addCars(carsRequest));
	}

}
