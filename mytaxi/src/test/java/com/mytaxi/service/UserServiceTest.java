package com.mytaxi.service;

import com.mytaxi.AbstractTest;
import com.mytaxi.domain.Driver;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import com.mytaxi.repository.DriverDao;
/**
 * @author jeraldfdo
 */
public class UserServiceTest extends AbstractTest
{

    @Mock
    private DriverDao driverDao;

    @InjectMocks
    private DefaultUserService userService;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DefaultUserService.class);
    }


    @Test
    public void testLoadUserByUsername()
    {
        Driver driver = getDriver();
        when(driverDao.findByUsername(any(String.class))).thenReturn(driver);
        userService.loadUserByUsername(any(String.class));
        verify(driverDao, times(1)).findByUsername(any(String.class));
    }
}
