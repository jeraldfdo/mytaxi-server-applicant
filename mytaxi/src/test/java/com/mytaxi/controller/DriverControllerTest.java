package com.mytaxi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.AbstractTest;
import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.datatransferobject.DriverDto;
import com.mytaxi.domain.OnlineStatus;
import com.mytaxi.facade.DriverFacade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * @author jeraldfdo
 */
public class DriverControllerTest extends AbstractTest
{

    private static final ObjectMapper mapper = new ObjectMapper();

    private MockMvc mvc;

    @Mock
    private DriverFacade driverFacade;

    @InjectMocks
    private DriverController driverController;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DriverController.class);
    }


    @Before
    public void init()
    {
        mvc = MockMvcBuilders.standaloneSetup(driverController).dispatchOptions(true).build();
    }


    @Test
    public void testSelectCarByDriver() throws Exception
    {
        DriverDto driverDto = getDriverDto();
        doReturn(driverDto).when(driverFacade).selectCarByDriver(any(Long.class), any(Long.class));
        driverController.selectCarByDriver(1L, 1L);
        MvcResult result = mvc
            .perform(post("/v1/drivers/select")
                .param("driverId", "1")
                .param("carId", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("test"));

    }


    @Test
    public void testDeSelectCarByDriver() throws Exception
    {
        doNothing().when(driverFacade).deSelectCarByDriver(any(Long.class), any(Long.class));
        driverController.deSelectCarByDriver(1L, 1L);
        MvcResult result = mvc
            .perform(post("/v1/drivers/select")
                .param("driverId", "1")
                .param("carId", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }


    @Test
    public void testGetDriver() throws Exception
    {
        DriverDto driverDto = getDriverDto();
        doReturn(driverDto).when(driverFacade).findByDriverId(any(Long.class));
        driverController.getDriver(1L);
        MvcResult result = mvc
            .perform(get("/v1/drivers/{driverId}", 1))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("test"));
    }


    @Test
    public void testUpdateLocation() throws Exception
    {
        doNothing().when(driverFacade).updateLocation(any(Long.class), any(Double.class), any(Double.class));
        driverController.updateLocation(1L, 99, 99);
        MvcResult result = mvc
            .perform(put("/v1/drivers/{driverId}", 1)
                .param("longitude", "99").param("latitude", "99"))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }


    @Test
    public void testCreateDriver() throws Exception
    {
        DriverDto driverDto = getDriverDto();
        String jsonInString = mapper.writeValueAsString(driverDto);
        doReturn(driverDto).when(driverFacade).create(any(DriverDto.class));
        driverController.createDriver(driverDto);
        MvcResult result = mvc
            .perform(post("/v1/drivers")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonInString))
            .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("test"));

    }


    @Test
    public void testFindDriverByCarAttributes() throws Exception
    {
        List<DriverDto> driverDtoList = Collections.singletonList(getDriverDto());
        doReturn(driverDtoList).when(driverFacade).findDriverByCarAttributes(any(CarDto.class));
        Map<String, String> params = new HashMap<>();
        params.put("seatCount", "3");
        driverController.findDriverByCarAttributes(params);
        MvcResult result = mvc
            .perform(get("/v1/drivers/car")
                .param("seatCount", "3"))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("test"));
    }


    @Test
    public void testDeleteDriver() throws Exception
    {
        doNothing().when(driverFacade).delete(any(Long.class));
        driverController.deleteDriver(1L);
        MvcResult result = mvc
            .perform(delete("/v1/drivers/{driverId}", 1))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }


    @Test
    public void testFindByStatus() throws Exception
    {
        List<DriverDto> driverDtoList = Collections.singletonList(getDriverDto());
        doReturn(driverDtoList).when(driverFacade).findByOnlineStatus(any(OnlineStatus.class));
        driverController.findDrivers(OnlineStatus.ONLINE);
        MvcResult result = mvc
            .perform(get("/v1/drivers")
                .param("onlineStatus", OnlineStatus.ONLINE.toString()))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("test"));
    }

}
