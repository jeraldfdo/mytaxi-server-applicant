package com.mytaxi.repository;

import com.google.common.collect.Lists;
import com.mytaxi.domain.Car;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

/**
 * @author jeraldfdo
 */
public class CarDaoTest extends AbstractDaoTest {

    @Autowired
    private CarDao carDao;

    @Test
    public void testDriverById() {
        Car car = carDao.findOne(1L);
        Assert.assertNotNull(car);
    }

    @Test
    public void testAllCars() {
        List<Car> cars = Lists.newArrayList(carDao.findAll());
        Assert.assertThat(cars, hasSize(3));
    }

}
