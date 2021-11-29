package com.sai.car.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sai.car.exceptions.CarNotFoundException;
import com.sai.car.model.Car;
import com.sai.car.model.CarRequest;
import com.sai.car.model.UsrDtlsRq;
import com.sai.car.service.CarService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CarController.class)
public class TestCarController {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	CarService carService;

	private ObjectMapper om = new ObjectMapper();

	@Test
	public void testCarDetails() throws Exception {
		Mockito.when(carService.getCarDetails(anyString())).thenReturn(new Car("chr", "hb"));
		mockMvc.perform(MockMvcRequestBuilders.get("/car/chr")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("name").value("chr"))
				.andExpect(MockMvcResultMatchers.jsonPath("type").value("hb"));

	}

	@Test
	public void testCarDetails_carNotFoundException() throws Exception {
		Mockito.when(carService.getCarDetails(anyString())).thenThrow(new CarNotFoundException());
		mockMvc.perform(MockMvcRequestBuilders.get("/car/chr")).andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void testCreateCarDetails() throws Exception {
		Mockito.when(carService.addCarDetails(anyString(), anyString())).thenReturn(new Car("juke", "diesel"));
		mockMvc.perform(MockMvcRequestBuilders.post("/car/add/juke/diesel"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("name").value("juke"))
				.andExpect(MockMvcResultMatchers.jsonPath("type").value("diesel"));

	}

	@Test
	public void testUpdateCarDetails() throws Exception {
		Mockito.when(carService.updateCarDetails(anyString(), anyString())).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.put("/car/update/juke/hybrid"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("count").value(1));
	}

	@Test
	public void testDeleteCarDetails() throws Exception {
		Mockito.when(carService.deleteCarDetails(anyString())).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/car/delete/jag"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("count").value(1));
	}

	@Test
	public void testSaveMultipleCarDetails() throws Exception {
		List<CarRequest> carsRequest = new ArrayList<CarRequest>();
		carsRequest.add(new CarRequest("juke", "hyb"));
		List<Car> cars = new ArrayList<Car>();
		cars.add(new Car("juke", "hyb"));
		String carsJson = om.writeValueAsString(carsRequest);
		Mockito.when(carService.addCars(anyList())).thenReturn(cars);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/car/newcars").contentType(MediaType.APPLICATION_JSON).content(carsJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("juke")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].type", is("hyb")));

	}

	@Test
	public void testLoginAuthentication() throws Exception {
		UsrDtlsRq usrDtls = new UsrDtlsRq("user", "pass");
		String usrDtlsJsn = om.writeValueAsString(usrDtls);
		mockMvc.perform(MockMvcRequestBuilders.post("/car/userlogin").contentType(MediaType.APPLICATION_JSON)
				.content(usrDtlsJsn)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status", is(false)));

	}

}
