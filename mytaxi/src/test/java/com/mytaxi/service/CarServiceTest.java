package com.mytaxi.service;

import com.mytaxi.AbstractTest;
import com.mytaxi.domain.Car;
import com.mytaxi.domain.Manufacturer;
import com.mytaxi.exception.EntityNotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import java.util.Collections;
import com.mytaxi.repository.CarDao;
import com.mytaxi.repository.ManufacturerDao;

/**
 * @author jeraldfdo
 */
public class CarServiceTest extends AbstractTest
{

    @Mock
    private CarDao carDao;

    @Mock
    private ManufacturerDao manufacturerDao;

    @InjectMocks
    private DefaultCarService carService;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DefaultCarService.class);
    }


    @Test
    public void testFindCarById() throws EntityNotFoundException
    {
        Car car = getCar();
        when(carDao.findOne(any(Long.class))).thenReturn(car);
        carService.findCarById(any(Long.class));
        verify(carDao, times(1)).findOne(any(Long.class));
    }


    @Test
    public void testFindAllCars()
    {
        Iterable<Car> cars = Collections.singletonList(getCar());
        when(carDao.findAll()).thenReturn(cars);
        carService.findAllCars();
        verify(carDao, times(1)).findAll();
    }


    @Test
    public void testCreate() throws EntityNotFoundException
    {
        Car car = getCar();
        Manufacturer manufacturer = getManufacturer();
        when(manufacturerDao.findByName(any(String.class))).thenReturn(manufacturer);
        when(carDao.save(any(Car.class))).thenReturn(car);
        carService.create(car);
        verify(manufacturerDao, times(1)).findByName(any(String.class));
        verify(carDao, times(1)).save(car);
    }


    @Test
    public void testUpdate() throws Exception
    {
        Car car = getCar();
        Manufacturer manufacturer = getManufacturer();
        when(carDao.findOne(any(Long.class))).thenReturn(car);
        when(manufacturerDao.findByName(any(String.class))).thenReturn(manufacturer);
        carService.update(car);
        verify(carDao, times(1)).findOne(any(Long.class));
        verify(manufacturerDao, times(1)).findByName(any(String.class));
    }


    @Test
    public void testDelete() throws EntityNotFoundException
    {
        Car car = getCar();
        when(carDao.findOne(any(Long.class))).thenReturn(car);
        carService.delete(1L);
        verify(carDao, times(1)).findOne(any(Long.class));
    }
}

