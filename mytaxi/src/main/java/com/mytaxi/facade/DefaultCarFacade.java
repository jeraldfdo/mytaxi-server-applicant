package com.mytaxi.facade;

import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.domain.Car;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.facade.CarFacade;
import com.mytaxi.filter.CarPopulator;
import com.mytaxi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementation of CarFacade Interface
 * 
 * @author jeraldfdo
 */
@Service
public class DefaultCarFacade implements CarFacade {

    @Autowired
    private CarService carService;

    @Override
    public CarDto findCarById(Long carId) throws EntityNotFoundException {
        return CarPopulator.populate(carService.findCarById(carId));
    }

    @Override
    public List<CarDto> findAllCars() {
        return CarPopulator.populate(carService.findAllCars());
    }

    @Override
    public CarDto create(CarDto carDto) throws EntityNotFoundException {
        Car car = CarPopulator.convert(carDto);
        return CarPopulator.populate(carService.create(car));
    }

    @Override
    public void update(final CarDto carDto) throws EntityNotFoundException {
        Car car = CarPopulator.convert(carDto);
        car.setId(carDto.getId());
        carService.update(car);
    }

    @Override
    public void delete(Long carId) throws EntityNotFoundException {
        carService.delete(carId);
    }
}
