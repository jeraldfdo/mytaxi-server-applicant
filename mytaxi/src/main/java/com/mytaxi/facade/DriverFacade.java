package com.mytaxi.facade;

import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.datatransferobject.DriverDto;
import com.mytaxi.domain.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;

/**
 * Driver Facade Interface
 * 
 * @author jeraldfdo
 */
public interface DriverFacade {

    DriverDto findByDriverId(Long driverId) throws EntityNotFoundException;

    DriverDto create(DriverDto driverDto) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDto> findByOnlineStatus(OnlineStatus onlineStatus);

    DriverDto selectCarByDriver(Long driverId, Long carId) throws EntityNotFoundException, CarAlreadyInUseException;

    void deSelectCarByDriver(Long driverId, Long carId) throws EntityNotFoundException, CarAlreadyInUseException;

    List<DriverDto> findDriverByCarAttributes(final CarDto carDto);

}
