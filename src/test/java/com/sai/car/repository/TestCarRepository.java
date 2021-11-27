package com.sai.car.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sai.car.model.Car;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class TestCarRepository {

	@Resource
	private CarRepository carRepository;

	@Test
	public void testGetCarDetailsRepository() throws Exception {
		// Mockito.when(carRepository.findByName("chr")).thenReturn(Optional.of(new
		// Car("chr","hb")));
		Optional<Car> carOptional = carRepository.findByName("Tesla");
		assertTrue(carOptional.isPresent());
	}

	@Test
	public void testUpdateCarDetailsRepository() {
		assertEquals(1, carRepository.updateCarDetails("Tesla", "hyb"));
	}

	@Test
	public void testDeleteCarDetailsRepository() {
		assertEquals(Optional.of(1), carRepository.deleteCarDetails("Tesla"));
	}

	@Test
	public void testAddMultipleCarsRepository() {
		List<Car> cars = new ArrayList<Car>();
		assertEquals(false, carRepository.addMultipleCarsDetails(cars).isEmpty());
	}

}
