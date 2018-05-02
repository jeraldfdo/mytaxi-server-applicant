package com.mytaxi.service;

import com.mytaxi.domain.Driver;
import com.mytaxi.domain.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.List;

public interface DriverService
{

    Driver findByDriverId(Long driverId) throws EntityNotFoundException;

    Driver create(Driver driver) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<Driver> findByOnlineStatus(OnlineStatus onlineStatus);

}
