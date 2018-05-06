package com.mytaxi.facade;

import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;

/**
 * Car Facade Interface
 * 
 * @author jeraldfdo
 */
public interface CarFacade {

    CarDto findCarById(final Long carId) throws EntityNotFoundException;

    List<CarDto> findAllCars();

    CarDto create(final CarDto carDto) throws EntityNotFoundException;

    void update(final CarDto carDto) throws EntityNotFoundException;

    void delete(final Long carId) throws EntityNotFoundException;
}
