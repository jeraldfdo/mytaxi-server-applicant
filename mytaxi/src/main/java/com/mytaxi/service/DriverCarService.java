package com.mytaxi.service;

import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.domain.DriverCar;

import java.util.List;

/**
 * Driver - Car Service Interface
 * 
 * @author jeraldfdo
 */
public interface DriverCarService {

    void delete(DriverCar driverCar);

    DriverCar save(DriverCar driverCar);

    DriverCar findByDriverIdAndCarId(final Long driverId, final Long carId);

    List<Object[]> findDriverByCarAttributes(final CarDto carDto);

}
