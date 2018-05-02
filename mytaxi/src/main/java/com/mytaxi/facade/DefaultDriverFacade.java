package com.mytaxi.facade;

import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.datatransferobject.DriverDto;
import com.mytaxi.domain.Car;
import com.mytaxi.domain.Driver;
import com.mytaxi.domain.DriverCar;
import com.mytaxi.domain.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.filter.DriverPopulator;
import com.mytaxi.service.CarService;
import com.mytaxi.service.DriverCarService;
import com.mytaxi.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jeraldfdo
 */
@Service
public class DefaultDriverFacade implements DriverFacade
{

    @Autowired
    private CarService carService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverCarService driverCarService;


    @Override public DriverDto findByDriverId(Long driverId) throws EntityNotFoundException
    {
        return DriverPopulator.makeDriverDTO(driverService.findByDriverId(driverId));
    }


    @Override public DriverDto create(DriverDto driverDto) throws ConstraintsViolationException
    {
        Driver driver = DriverPopulator.makeDriverDO(driverDto);
        return DriverPopulator.makeDriverDTO(driverService.create(driver));
    }


    @Override public void delete(Long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @Override public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @Override public List<DriverDto> findByOnlineStatus(OnlineStatus onlineStatus)
    {
        return DriverPopulator.makeDriverDTOList(driverService.findByOnlineStatus(onlineStatus));
    }


    @Override public DriverDto selectCarByDriver(Long driverId, Long carId) throws CarAlreadyInUseException, EntityNotFoundException
    {
        Object[] object = new Object[2];
        Driver driver;
        Car car;
        try
        {
            driver = driverService.findByDriverId(driverId);
            car = carService.findCarById(carId);
            if (null != driver && null != car && OnlineStatus.ONLINE.equals(driver.getOnlineStatus()))
            {
                DriverCar driverCar = new DriverCar();
                driverCar.setDriverId(driver.getId());
                driverCar.setCarId(car.getId());
                driverCarService.save(driverCar);
                object[0] = driver;
                object[1] = car;
            }
            else if (null != driver && null != car && OnlineStatus.OFFLINE.equals(driver.getOnlineStatus()))
            {
                throw new CarAlreadyInUseException("Driver is offline");
            }
        }
        catch (EntityNotFoundException e)
        {
            throw new EntityNotFoundException("Car or Driver entity not found ");
        }
        catch (DataIntegrityViolationException e)
        {
            throw new CarAlreadyInUseException("Car is already taken by driver");
        }
        return DriverPopulator.makeDriverDTO(object);

    }


    @Override public void deSelectCarByDriver(Long driverId, Long carId) throws CarAlreadyInUseException, EntityNotFoundException
    {
        Driver driver;
        Car car;
        try
        {
            driver = driverService.findByDriverId(driverId);
            car = carService.findCarById(carId);
            if (null != driver && null != car && OnlineStatus.ONLINE.equals(driver.getOnlineStatus()))
            {
                DriverCar driverCar = driverCarService.findByDriverIdAndCarId(driver.getId(), car.getId());
                driverCarService.delete(driverCar);
            }
        }
        catch (EntityNotFoundException e)
        {
            throw new EntityNotFoundException("Car or Driver entity not found ");
        }
        catch (InvalidDataAccessApiUsageException e)
        {
            throw new CarAlreadyInUseException("Car is already deselected by the driver");
        }
    }


    @Override public List<DriverDto> findDriverByCarAttributes(CarDto carDto)
    {
        List<DriverDto> driverDtoList = new ArrayList<>();
        List<Object[]> drivers = driverCarService.findDriverByCarAttributes(carDto);
        drivers.forEach(object -> driverDtoList.add(DriverPopulator.makeDriverDTO(object)));
        return driverDtoList;
    }
}
