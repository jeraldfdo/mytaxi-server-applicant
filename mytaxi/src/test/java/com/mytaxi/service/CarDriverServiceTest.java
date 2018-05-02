package com.mytaxi.service;

import com.mytaxi.AbstractTest;
import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.domain.DriverCar;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import com.mytaxi.repository.DriverCarDao;

/**
 * @author jeraldfdo
 */
public class CarDriverServiceTest extends AbstractTest {

    @Mock
    private DriverCarDao driverCarDao;

    @InjectMocks
    private DefaultDriverCarService driverCarService;

    @BeforeClass
    public static void setUp() {
        MockitoAnnotations.initMocks(DefaultDriverCarService.class);
    }

    @Test
    public void testDelete() {
        DriverCar driverCar = getDriverCar();
        doNothing().when(driverCarDao).delete(any(DriverCar.class));
        driverCarService.delete(driverCar);
        verify(driverCarDao, times(1)).delete(any(DriverCar.class));
    }

    @Test
    public void testSave() {
        DriverCar driverCar = getDriverCar();
        when(driverCarDao.save(any(DriverCar.class))).thenReturn(driverCar);
        driverCarService.save(driverCar);
        verify(driverCarDao, times(1)).save(any(DriverCar.class));
    }

    @Test
    public void testFindByDriverIdAndCarId() {
        DriverCar driverCar = getDriverCar();
        when(driverCarDao.findByDriverIdAndCarId(any(Long.class), any(Long.class))).thenReturn(driverCar);
        driverCarService.findByDriverIdAndCarId(1L, 1L);
        verify(driverCarDao, times(1)).findByDriverIdAndCarId(any(Long.class), any(Long.class));
    }

    @Test
    public void testFindDriverByCarAttributes() {
        CarDto carDto = getCarDto();
        List<Object[]> objects = new ArrayList<>();
        Object[] object = new Object[2];
        object[0] = getDriver();
        object[1] = getCar();
        objects.add(object);
        when(driverCarDao.findDriverByCarAttributes(any(CarDto.class))).thenReturn(objects);
        driverCarService.findDriverByCarAttributes(carDto);
        verify(driverCarDao, times(1)).findDriverByCarAttributes(any(CarDto.class));
    }
}
