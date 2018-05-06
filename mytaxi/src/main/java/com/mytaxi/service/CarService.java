package com.mytaxi.service;

import com.mytaxi.domain.Car;
import com.mytaxi.exception.EntityNotFoundException;

/**
 * Car Service Interface
 * 
 * @author jeraldfdo
 */
public interface CarService {

    Car findCarById(final Long carId) throws EntityNotFoundException;

    Iterable<Car> findAllCars();

    Car create(final Car car) throws EntityNotFoundException;

    void update(final Car car) throws EntityNotFoundException;

    void delete(final Long carId) throws EntityNotFoundException;

}
