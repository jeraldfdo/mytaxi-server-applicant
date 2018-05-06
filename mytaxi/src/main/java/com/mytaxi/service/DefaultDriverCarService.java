package com.mytaxi.service;

import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.domain.DriverCar;
import com.mytaxi.service.DriverCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.mytaxi.repository.DriverCarDao;

/**
 * Implementation of DriverCarService Interface
 * 
 * @author jeraldfdo
 */
@Service
public class DefaultDriverCarService implements DriverCarService {

    @Autowired
    private DriverCarDao driverCarDao;

    @Override
    public void delete(DriverCar driverCar) {
        driverCarDao.delete(driverCar);
    }

    @Override
    public DriverCar save(DriverCar driverCar) {
        return driverCarDao.save(driverCar);
    }

    @Override
    public DriverCar findByDriverIdAndCarId(Long driverId, Long carId) {
        return driverCarDao.findByDriverIdAndCarId(driverId, carId);
    }

    @Override
    public List<Object[]> findDriverByCarAttributes(CarDto carDto) {
        return driverCarDao.findDriverByCarAttributes(carDto);
    }
}
