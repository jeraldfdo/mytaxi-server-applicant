package com.mytaxi.service;

import com.mytaxi.domain.Driver;
import com.mytaxi.domain.Coordinate;
import com.mytaxi.domain.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;

import com.mytaxi.service.DriverService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mytaxi.repository.DriverDao;
import com.mytaxi.domain.Car;


/**
 * Implementation of DriverService Interface
 *
 * @author jeraldfdo
 */
@Slf4j
@Service
public class DefaultDriverService implements DriverService {

    @Autowired
    private DriverDao driverDao;

    @Override
    public Driver findByDriverId(Long driverId) throws EntityNotFoundException {
        return findDriverChecked(driverId);
    }

    @Override
    public Driver create(Driver driverDO) throws ConstraintsViolationException {
        Driver driver;
        try {
            driver = driverDao.save(driverDO);
        } catch (DataIntegrityViolationException e) {
            log.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }

    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException {
        Driver driver = findDriverChecked(driverId);
        driver.setDeleted(true);
    }

    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException {
        Driver driver = findDriverChecked(driverId);
        driver.setCoordinate(new Coordinate(latitude, longitude));
    }

    @Override
    public List<Driver> findByOnlineStatus(OnlineStatus onlineStatus) {
        return driverDao.findByOnlineStatus(onlineStatus);
    }

    private Driver findDriverChecked(Long driverId) throws EntityNotFoundException {
        Driver driver = driverDao.findOne(driverId);
        if (null == driver) {
            throw new EntityNotFoundException("Could not find car entity with id: " + driverId);
        }
        return driver;

    }

}
