package com.mytaxi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.AbstractTest;
import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.facade.CarFacade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * @author jeraldfdo
 */

public class CarControllerTest extends AbstractTest
{

    private static final ObjectMapper mapper = new ObjectMapper();

    private MockMvc mvc;

    @Mock
    private CarFacade carFacade;

    @InjectMocks
    private CarController carController;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(CarController.class);
    }


    @Before
    public void init()
    {
        mvc = MockMvcBuilders.standaloneSetup(carController).dispatchOptions(true).build();
    }


    @Test
    public void testGetCar() throws Exception
    {
        CarDto carDto = getCarDto();
        doReturn(carDto).when(carFacade).findCarById(any(Long.class));
        carController.getCar(1L);
        MvcResult result = mvc
            .perform(get("/swag/cars/{carId}", 1))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("ABV101"));
    }


    @Test
    public void getAllCars() throws Exception
    {
        List<CarDto> cars = Collections.singletonList(getCarDto());
        doReturn(cars).when(carFacade).findAllCars();
        carController.getAllCars();
        MvcResult result = mvc
            .perform(get("/swag/cars"))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("ABV101"));
    }


    @Test
    public void createCar() throws Exception
    {
        CarDto carDto = getCarDto();
        String jsonInString = mapper.writeValueAsString(carDto);
        doReturn(carDto).when(carFacade).create(any(CarDto.class));
        carController.createCar(carDto);
        MvcResult result = mvc
            .perform(post("/swag/cars")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonInString))
            .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("11.0"));
    }


    @Test
    public void updateCar() throws Exception
    {
        CarDto carDto = getCarDto();
        String jsonInString = mapper.writeValueAsString(carDto);
        doNothing().when(carFacade).update(any(CarDto.class));
        carController.updateCar(carDto);
        MvcResult result = mvc
            .perform(put("/swag/cars")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonInString))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }


    @Test
    public void deleteCar() throws Exception
    {
        doNothing().when(carFacade).delete(any(Long.class));
        carController.deleteCar(1L);
        MvcResult result = mvc
            .perform(delete("/swag/cars/{carId}", 1L))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

}
