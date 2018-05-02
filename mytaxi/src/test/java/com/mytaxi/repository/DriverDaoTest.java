package com.mytaxi.repository;

import com.mytaxi.domain.Driver;
import com.mytaxi.domain.OnlineStatus;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import java.util.List;
import java.util.Optional;
import com.mytaxi.repository.DriverDao;

/**
 * @author jeraldfdo
 */

public class DriverDaoTest  extends AbstractDaoTest
{

    private static final String USER_NAME = "driver02";


    @Autowired
    private DriverDao driverDao;


    @Test
    public void testDriverById()
    {
        Driver driver = driverDao.findOne(1L);
        Assert.assertNotNull(driver);
    }


    @Test
    public void testDriverByStatus()
    {
        List<Driver> onlineDrivers = driverDao.findByOnlineStatus(OnlineStatus.ONLINE);
        Assert.assertThat(onlineDrivers, hasSize(4));
    }


    @Test
    public void testDriverByOfflineStatus()
    {
        List<Driver> offlineDrivers = driverDao.findByOnlineStatus(OnlineStatus.OFFLINE);
        Assert.assertThat(offlineDrivers, hasSize(4));
    }


    @Test
    public void testDriverByUsername()
    {
        Driver driver = driverDao.findByUsername(USER_NAME);
        Assert.assertNotNull(driver);
    }
}
