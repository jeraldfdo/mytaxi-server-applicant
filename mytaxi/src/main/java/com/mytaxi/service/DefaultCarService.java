package com.mytaxi.service;

import com.mytaxi.domain.Car;
import com.mytaxi.domain.Manufacturer;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.CarService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mytaxi.repository.CarDao;
import com.mytaxi.repository.ManufacturerDao;

/**
 * @author jeraldfdo
 */
@Slf4j
@Service
public class DefaultCarService implements CarService
{

    @Autowired
    private CarDao carDao;

    @Autowired
    private ManufacturerDao manufacturerDao;


    @Override
    public Car findCarById(final Long carId) throws EntityNotFoundException
    {
        return carCheck(carId);
    }


    @Override
    public Iterable<Car> findAllCars()
    {
        return carDao.findAll();
    }


    @Override
    public Car create(final Car car) throws EntityNotFoundException
    {
        car.setManufacturer(manufacturerCheck(car));
        return carDao.save(car);
    }


    @Override
    @Transactional
    public void update(final Car car) throws EntityNotFoundException
    {
        Car updateCar = carCheck(car.getId());
        updateCar.setManufacturer(manufacturerCheck(car));
        updateCar.setConvertible(car.getConvertible());
        updateCar.setEngineType(car.getEngineType());
        updateCar.setLicensePlate(car.getLicensePlate());
        updateCar.setRating(car.getRating());
        updateCar.setSeatCount(car.getSeatCount());
    }


    @Override
    @Transactional
    public void delete(final Long carId) throws EntityNotFoundException
    {
        Car car = carCheck(carId);
        car.setDeleted(Boolean.TRUE);
    }


    private Car carCheck(final Long carId) throws EntityNotFoundException
    {
       Car car = carDao.findOne(carId);
        if (null == car)
        {
            throw new EntityNotFoundException("Could not find car entity with id: " + carId);
        }
        return car;
    }


    private Manufacturer manufacturerCheck(final Car car) throws EntityNotFoundException
    {
        String manufacturerName = car.getManufacturer().getName();
        Manufacturer manufacturer = manufacturerDao.findByName(manufacturerName);
        if (null == manufacturer)
        {
            throw new EntityNotFoundException("Manufacturer not found with this name: " + manufacturerName);
        }
        return manufacturer;
    }
}
