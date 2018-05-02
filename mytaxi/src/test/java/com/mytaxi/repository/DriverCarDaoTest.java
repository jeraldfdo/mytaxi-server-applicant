package com.mytaxi.repository;

import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.domain.Car;
import com.mytaxi.domain.Driver;
import com.mytaxi.domain.DriverCar;
import com.mytaxi.domain.OnlineStatus;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import com.mytaxi.repository.DriverCarDao;

/**
 * @author jeraldfdo
 */

public class DriverCarDaoTest extends AbstractDaoTest
{

    @Autowired
    private DriverCarDao driverCarDao;


    @Test
    public void testFindByDriverIdAndCarId()
    {
        DriverCar driverCar = getDriverCar();
        driverCarDao.save(driverCar);
        DriverCar savedDriverCar = driverCarDao.findByDriverIdAndCarId(1L, 1L);
        Assert.assertNotNull(savedDriverCar);
    }

    @Test
    public void testFindByDriverSeatCount()
    {
        DriverCar driverCar = getDriverCar();
        driverCarDao.save(driverCar);
        CarDto carDto = CarDto.builder().seatCount(3).build();
        List<Object[]> drivers = driverCarDao.findDriverByCarAttributes(carDto);
        drivers.forEach(obj ->
        {
            Driver driver = (Driver) obj[0];
            Car car = (Car) obj[1];
            Assert.assertEquals(OnlineStatus.OFFLINE, driver.getOnlineStatus());
            Assert.assertEquals(Integer.valueOf(3), car.getSeatCount());
        });
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testAlreadyExistCarWithDriver()
    {
        DriverCar driverCar1 = getDriverCar();
        DriverCar driverCar2 = getDriverCar();
        driverCarDao.save(driverCar1);
        DriverCar savedDriverCar = driverCarDao.findByDriverIdAndCarId(1L, 1L);
        driverCarDao.save(driverCar2);
        Assert.assertNotNull(savedDriverCar);
    }

    private DriverCar getDriverCar()
    {
        DriverCar driverCar = new DriverCar();
        driverCar.setDriverId(1L);
        driverCar.setCarId(1L);
        return driverCar;
    }

}
