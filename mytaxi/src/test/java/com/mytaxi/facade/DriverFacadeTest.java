package com.mytaxi.facade;

import com.mytaxi.domain.DriverCar;
import com.mytaxi.domain.Driver;
import com.mytaxi.domain.OnlineStatus;
import com.mytaxi.domain.Car;
import com.mytaxi.AbstractTest;
import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.datatransferobject.DriverDto;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.CarService;
import com.mytaxi.service.DriverCarService;
import com.mytaxi.service.DriverService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jeraldfdo
 */
public class DriverFacadeTest extends AbstractTest
{

    @Mock
    private CarService carService;

    @Mock
    private DriverService driverService;

    @Mock
    private DriverCarService driverCarService;

    @InjectMocks
    private DefaultDriverFacade driverFacade;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DefaultDriverFacade.class);
    }


    @Test
    public void testFindByDriverId() throws EntityNotFoundException
    {
        Driver driver = getDriver();
        when(driverService.findByDriverId(any(Long.class))).thenReturn(driver);
        driverFacade.findByDriverId(any(Long.class));
        verify(driverService, times(1)).findByDriverId(any(Long.class));
    }


    @Test
    public void testCreate() throws ConstraintsViolationException
    {
        DriverDto driverDto = getDriverDto();
        Driver driver = getDriver();
        when(driverService.create(any(Driver.class))).thenReturn(driver);
        driverFacade.create(driverDto);
        verify(driverService, times(1)).create(any(Driver.class));
    }


    @Test
    public void testDelete() throws EntityNotFoundException
    {
        doNothing().when(driverService).delete(any(Long.class));
        driverFacade.delete(any(Long.class));
        verify(driverService, times(1)).delete(any(Long.class));
    }


    @Test
    public void testUpdateLocation() throws EntityNotFoundException
    {
        doNothing().when(driverService).updateLocation(any(Long.class), any(Double.class), any(Double.class));
        driverFacade.updateLocation(any(Long.class), any(Double.class), any(Double.class));
        verify(driverService, times(1)).updateLocation(any(Long.class), any(Double.class), any(Double.class));
    }


    @Test
    public void testFindByStatus()
    {
        List<Driver> drivers = Collections.singletonList(getDriver());
        when(driverService.findByOnlineStatus(any(OnlineStatus.class))).thenReturn(drivers);
        driverFacade.findByOnlineStatus(any(OnlineStatus.class));
        verify(driverService, times(1)).findByOnlineStatus(any(OnlineStatus.class));
    }


    @Test
    public void testSelectCarByDriver() throws Exception
    {
        DriverCar driverCar = getDriverCar();
        Driver driver = getDriver();
        Car car = getCar();
        when(driverService.findByDriverId(any(Long.class))).thenReturn(driver);
        when(carService.findCarById(any(Long.class))).thenReturn(car);
        when(driverCarService.save(any(DriverCar.class))).thenReturn(driverCar);
        driverFacade.selectCarByDriver(1L, 1L);
        verify(driverService, times(1)).findByDriverId(any(Long.class));
        verify(carService, times(1)).findCarById(any(Long.class));
        verify(driverCarService, times(1)).save(any(DriverCar.class));
    }


    @Test(expected = CarAlreadyInUseException.class)
    public void testSelectCarByOfflineDriver() throws Exception
    {
        DriverCar driverCar = getDriverCar();
        Driver driver = getDriver();
        driver.setOnlineStatus(OnlineStatus.OFFLINE);
        Car car = getCar();
        when(driverService.findByDriverId(any(Long.class))).thenReturn(driver);
        when(carService.findCarById(any(Long.class))).thenReturn(car);
        when(driverCarService.save(any(DriverCar.class))).thenReturn(driverCar);
        driverFacade.selectCarByDriver(1L, 1L);
    }


    @Test
    public void testDeSelectCarByDriver() throws Exception
    {
        DriverCar driverCar = getDriverCar();
        Driver driver = getDriver();
        Car car = getCar();
        when(driverService.findByDriverId(any(Long.class))).thenReturn(driver);
        when(carService.findCarById(any(Long.class))).thenReturn(car);
        when(driverCarService.findByDriverIdAndCarId(any(Long.class), any(Long.class))).thenReturn(driverCar);
        doNothing().when(driverCarService).delete(any(DriverCar.class));
        driverFacade.deSelectCarByDriver(1L, 1L);
        verify(driverService, times(1)).findByDriverId(any(Long.class));
        verify(carService, times(1)).findCarById(any(Long.class));
        verify(driverCarService, times(1)).findByDriverIdAndCarId(any(Long.class), any(Long.class));
        verify(driverCarService, times(1)).delete(any(DriverCar.class));
    }


    @Test
    public void testFindDriverByCarAttributes()
    {
        CarDto carDto = getCarDto();
        List<Object[]> objects = new ArrayList<>();
        Object[] object = new Object[2];
        object[0] = getDriver();
        object[1] = getCar();
        objects.add(object);
        when(driverCarService.findDriverByCarAttributes(any(CarDto.class))).thenReturn(objects);
        driverCarService.findDriverByCarAttributes(carDto);
        verify(driverCarService, times(1)).findDriverByCarAttributes(any(CarDto.class));
    }
}
